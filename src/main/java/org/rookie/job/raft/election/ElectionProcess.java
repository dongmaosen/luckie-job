package org.rookie.job.raft.election;
/**
 * 选举处理类
 * 
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.rookie.job.cfg.LuckieConfig;
import org.rookie.job.client.LuckyJobManager;
import org.rookie.job.raft.RaftBootstrap;
import org.rookie.job.raft.enums.NodeState;
import org.rookie.job.rpc.client.RPCClient;
import org.rookie.job.rpc.proto.LuckieProto.Luckie;

public class ElectionProcess {
	// ---选举变量 S--- 涉及到这些变量在本地修改的，都要加锁
	/**
	 * 集群leader节点信息
	 */
	public static NodeInfo leader;

	/**
	 * 选举周期（内存递增）
	 */
	public static int term = 0;

	/**
	 * 获得的选票信息
	 */
	public static Set<NodeInfo> voteSet = new TreeSet<NodeInfo>();

	/**
	 * 选举时用到的TERM
	 */
	public static int voteTerm = 0;

	/**
	 * init state
	 */
	public static NodeState STATE = NodeState.FOLLOWER;

	/**
	 * 选举超时时间
	 */
	public static long electionTimeout = -1;

	// ---选举变量 E---
	/**
	 * 清空选举相关的变量
	 */
	private static void clearVariables() {
		voteSet.clear();
		leader = new NodeInfo();
		voteTerm = 0;
	}

	// ---本地配置变量 S---
	/**
	 * 本地节点信息
	 */
	public static NodeInfo localnode;

	/**
	 * 集群所有节点信息
	 */
	public static ArrayList<NodeInfo> NODELIST = new ArrayList<NodeInfo>();

	private static Lock lock = new ReentrantLock();
	// ---本地配置变量 E---

	public static String getNodeIP() {
		return LuckieConfig.IP;
	}

	/**
	 * 打印当前节点状态
	 * 
	 * @return
	 */
	public static String getElectionData() {
		StringBuilder sb = new StringBuilder();
		sb.append(localnode != null ? localnode.getIp() : "null").append("[").append(localnode != null ? localnode.getPort() : "null").append("]:{").append("\n")
		  .append("term : ").append(term).append("\n")
		  .append("STATE : ").append(NodeState.getName(STATE.getState())).append("\n")
		  .append("voteTerm : ").append(voteTerm).append("\n")
		  .append("electionTimeout : ").append(electionTimeout).append("\n")
		  .append("voteSet : ").append(voteSet).append("\n");
		if (leader != null) {
			sb.append("leader : ").append(leader.getIp()).append(":").append(leader.getPort()).append("\n");
		} else {
			sb.append("leader : ").append("-").append("\n");
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 从follower状态到candidate状态
	 */
	public static boolean followerToCandidate() {
		try {
			lock.lock();
			if (STATE.getState() == NodeState.FOLLOWER.getState()) {
				STATE = NodeState.CANDIDATE;
			}
			// 开始异步选举
			startElection();
		} finally {
			lock.unlock();
		}
		return STATE.getState() == NodeState.FOLLOWER.getState();
	}

	// 选举线程池
	private static ExecutorService electionExec = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>());

	// 心跳线程
	private static ExecutorService heartbeatExec = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>());
	

	/**
	 * candidate异步选举过程
	 */
	private static void startElection() {
		// 周期+1
		term++;
		clearVariables();
		for (int i = 0; i < NODELIST.size(); i++) {
			NodeInfo nodeInfo = NODELIST.get(i);
			if (nodeInfo.equals(localnode)) {
				voteSet.add(localnode);
			} else {
				electionExec.submit(new AskForVoteTask(nodeInfo, term));
			}
		}

	}

	/**
	 * candidate状态选举超时，进入下一轮的选举
	 */
	public static void candidateToNextRound() {
		try {
			lock.lock();
			if (STATE.getState() == NodeState.CANDIDATE.getState()) {
				term++;
				STATE = NodeState.CANDIDATE;
				clearVariables();
				for (int i = 0; i < NODELIST.size(); i++) {
					NodeInfo nodeInfo = NODELIST.get(i);
					if (nodeInfo.equals(localnode)) {
						voteSet.add(localnode);
					} else {
						electionExec.submit(new AskForVoteTask(nodeInfo, term));
					}
				}
			}
		} finally {
			lock.unlock();
		}

	}

	/**
	 * 处理投票请求
	 * 
	 * @param luckieBuilder
	 * @param request
	 */
	public static boolean processRequest(Luckie request) {
		try {
			lock.lock();
			Map<String, String> data = request.getDataMap();
			int term = Integer.parseInt(data.get("term"));
			String ip = data.get("source_ip");
			int port = Integer.parseInt(data.get("source_port"));
			// 满足下面条件之一：
			// 1）follower状态，未发生选举或者选举周期号小于请求周期号 YES
			// 2）candidate状态，选举周期号小于请求周期号 YES
			// 3）leader状态，选举周期号小于请求周期号 YES
			if (STATE.getState() == NodeState.FOLLOWER.getState()) {
				// 未发生选举或者周期号小于请求周期号
				if (voteSet.isEmpty() || term > voteTerm) {
					// 变更本地状态
					voteTerm = term;
					NodeInfo node = new NodeInfo();
					node.setIp(ip);
					node.setPort(port);
					node.setLocal(false);
					node.setTerm(term);
					voteSet.clear();
					voteSet.add(node);
					RaftBootstrap.reSchedule();
					return true;
				}
			} else if (STATE.getState() == NodeState.CANDIDATE.getState()) {
				// candidate状态，收到高term的投票请求
				if (term > voteTerm) {
					// 当前状态转为follower，并给请求方投票
					STATE = NodeState.FOLLOWER;
					voteTerm = term;
					NodeInfo node = new NodeInfo();
					node.setIp(ip);
					node.setPort(port);
					node.setLocal(false);
					node.setTerm(term);
					voteSet.clear();
					voteSet.add(node);
					RaftBootstrap.reSchedule();
					return true;
				}
			} else if (STATE.getState() == NodeState.LEADER.getState()) {
				if (term > voteTerm) {
					// 0.转为follower状态
					STATE = NodeState.FOLLOWER;
					voteTerm = term;
					NodeInfo node = new NodeInfo();
					node.setIp(ip);
					node.setPort(port);
					node.setLocal(false);
					node.setTerm(term);
					voteSet.clear();
					voteSet.add(node);
					RaftBootstrap.reSchedule();
					return true;
				}
			}
		} finally {
			lock.unlock();
		}
		return false;
	}

	/**
	 * 请求端处理投票返回的结果
	 * 
	 * @param response
	 */
	public static void processElectionClient(Luckie response) {
		try {
			lock.lock();
			Map<String, String> data = response.getDataMap();
			String ip = data.get("source_ip");
			int port = Integer.parseInt(data.get("source_port"));
			int rterm = Integer.parseInt(data.get("term"));
			int result = Integer.parseInt(data.get("result"));
			// 只处理同一个周期内，给自己投票的结果
			if (term == rterm && result == 1) {
				NodeInfo node = new NodeInfo();
				node.setIp(ip);
				node.setPort(port);
				node.setVoted(true);
				if (!voteSet.contains(node)) {
					voteSet.add(node);
					if (localnode.equals(leader)) {
						//leader节点感知集合变化（新增），通知各个节点
						nodeChanged();
					}
					// 超过半数，当前节点成为leader
					if (voteSet.size() >= (NODELIST.size() + 1) / 2 && (leader == null || !leader.equals(localnode))) {
						// 将自己标识为leader（leader变量，节点状态）
						voteTerm = term;
						leader = localnode;
						STATE = NodeState.LEADER;
						// leader开始向其他节点发送心跳请求
						for (int i = 0; i < NODELIST.size(); i++) {
							NodeInfo nodeInfo = NODELIST.get(i);
							if (!nodeInfo.equals(localnode)) {
								heartbeatExec.submit(new HeartBeatTask(nodeInfo, term, 0));
							}
						}
					}
				}
			}
		} finally {
			lock.unlock();
		}
	}

	public static void nodeChanged() {
		for (int i = 0; i < NODELIST.size(); i++) {
			NodeInfo nodeInfo = NODELIST.get(i);
			if (!nodeInfo.equals(localnode)) {
				heartbeatExec.submit(new HeartBeatTask(nodeInfo, term, 1));
			} else {
				LuckyJobManager.reArrangeTask();
			}
		}
	}

	/**
	 * 心跳接收端处理心跳信息
	 * 
	 * @param data 参数信息
	 */
	public static void processReceiverHearbeat(Map<String, String> data) {
		int state = Integer.parseInt(data.get("state"));
		int term = Integer.parseInt(data.get("term"));
		String fromIP = data.get("source_ip");
		int fromPort = Integer.parseInt(data.get("source_port"));
		int type = Integer.parseInt(data.get("type"));
		// 仅仅leader的请求，其他请求作为超时心跳包
		try {
			lock.lock();
			if (state == NodeState.LEADER.getState() && term >= ElectionProcess.term) {
				// 更新leader信息，当前节点状态转为follower
				clearVariables();
				voteTerm = term;
				leader.setIp(fromIP);
				leader.setPort(fromPort);
				leader.setTerm(term);
				ElectionProcess.term = term;
				STATE = NodeState.FOLLOWER;
				// 重新设置leader选举
				RaftBootstrap.reSchedule();
				if (type == 1) {
					LuckyJobManager.reArrangeTask();
				}
			}
		} finally {
			lock.unlock();
		}
	}
	/**
	 * 心跳发送端处理逻辑
	 * @param response
	 */
	public static void proccessSenderReceiverHeartbeat(Luckie response) {
		//仅leader处理心跳
		if (NodeState.LEADER.getState() == STATE.getState()) {
			Map<String, String> data = response.getDataMap();
			NodeInfo responseNode = new NodeInfo();
			responseNode.setIp(data.get("source_ip"));
			responseNode.setPort(Integer.parseInt(data.get("source_port")));
			if (!voteSet.contains(responseNode)) {
				try {
					lock.lock();
					voteSet.add(responseNode);
				} finally {
					lock.unlock();
				}
				System.out.println("leader " + localnode + " add node " + responseNode);
				nodeChanged();
			}
		}

	}
	/**
	 * 当前节点为leader，有client与之断开，
	 * 
	 * @param ip
	 * @param port
	 */
	public static void processFollowerDisconnectByLeaderClient(String ip, int port) {
		try {
			lock.lock();
			// 从当前选举节点中移除
			NodeInfo disconnectNode = new NodeInfo();
			disconnectNode.setIp(ip);
			disconnectNode.setPort(port);
			voteSet.remove(disconnectNode);
			// 判断剩余节点是否过半
			if (voteSet.size() >= (NODELIST.size() + 1) / 2) {
				// 仍然是leader，重新发起心跳，因为目前的心跳在连接中实现，必须触发一下
				heartbeatExec.submit(new HeartBeatTask(disconnectNode, term, 0));
			} else {
				// 少于半数节点，当前节点转为follower状态，重新进行选举
				clearVariables();
				STATE = NodeState.FOLLOWER;
			}
		} finally {
			lock.unlock();
		}

	}

	/**
	 * server端是leader，处理客户端断开的异常情况
	 * 
	 * @param ip
	 * @param port
	 */
	public static void processFollowerDisconnectByLeaderServer(String ip, int port) {
		// 判断当前节点是否可以继续成为leader
		try {
			lock.lock();
			// 从当前选举节点中移除
			NodeInfo disconnectNode = new NodeInfo();
			disconnectNode.setIp(ip);
			disconnectNode.setPort(port);
			voteSet.remove(disconnectNode);
			// 判断剩余节点是否过半
			if (voteSet.size() < (NODELIST.size() + 1) / 2) {
				// 少于半数节点，当前节点转为follower状态，重新进行选举
				clearVariables();
				STATE = NodeState.FOLLOWER;
			}
		} finally {
			lock.unlock();
		}
	}
	/**
	 * 单节点模式下，直接成为leader
	 */
	public static void followerToLeader() {
		voteSet.add(localnode);
		leader = localnode;
	}
	
	/**
	 * 获取当前集群的所有的有效节点，直到获取到节点为止，否则阻塞
	 * @return
	 */
	public static List<NodeInfo> getClusterNodes() {
		List<NodeInfo> nodes = new ArrayList<NodeInfo>();
		int dida = 0;
		do {
			try {
				Thread.sleep(1000L * dida++);
				//如果当前节点是leader，直接返回当前节点即可
				if (localnode.equals(leader)) {
					Iterator<NodeInfo> iter = voteSet.iterator();
					while (iter.hasNext()) {
						nodes.add(iter.next());
					}
				} else if (leader != null && leader.getPort() != -1){//从leader处获取节点集合
					RPCClient rpcClient = RPCClient.getRPCClient(leader.getIp(), leader.getPort());
					nodes = rpcClient.getVoteNodes();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (nodes == null || nodes.size() == 0);
		return nodes;
	}
	/**
	 * 字符串形式返回当前生效集群的节点字符串
	 * @return
	 */
	public static String getClusterNodeString() {
		StringBuilder sb = new StringBuilder("");
		if (leader != null) {
			for (NodeInfo node : voteSet) {
				sb.append(node.toString()).append(";");
			}
		}
		return sb.toString();
	}

}
