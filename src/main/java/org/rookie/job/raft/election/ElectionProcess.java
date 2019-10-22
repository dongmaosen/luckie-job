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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.rookie.job.cfg.LuckieConfig;
import org.rookie.job.raft.RaftBootstrap;
import org.rookie.job.raft.enums.NodeState;
import org.rookie.job.rpc.proto.LuckieProto.Luckie;


public class ElectionProcess {
	//---选举变量 S--- 涉及到这些变量在本地修改的，都要加锁
	/**
	 * 集群leader节点信息
	 */
	public static NodeInfo leader;
	
	/**
	 *  选举周期（内存递增）
	 */
	public static int term = 0;
	
    /**
	 * 选票信息
	 */
	public static Set<NodeInfo> voteSet = new HashSet<NodeInfo>();
	
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
	
	
	//---选举变量 E---
	/**
	 * 清空选举相关的变量
	 */
	private static void clearVariables() {
		voteSet.clear();
		leader = null;
		voteTerm = 0;
	}
	//---本地配置变量 S---
	/**
	 * 本地节点信息
	 */
	public static NodeInfo localnode;
	
	/**
	 * 集群所有节点信息
	 */
	public static ArrayList<NodeInfo> NODELIST = new ArrayList<NodeInfo>();
	
	private static Lock lock = new ReentrantLock();
	//---本地配置变量 E---

	
	public static String getNodeIP() {
		return LuckieConfig.IP;
	}
	
	/**
	 * 打印当前节点状态
	 * @return
	 */
	public static String getElectionData() {
		StringBuilder sb = new StringBuilder();
		sb.append("current node state --> ").append("\n")
		  .append("[").append("\n")
		  .append("term : ").append(term).append("\n")
		  .append("STATE : ").append(NodeState.getName(STATE.getState())).append("\n")
		  .append("voteTerm : ").append(voteTerm).append("\n")
		  .append("electionTimeout : ").append(electionTimeout).append("\n")
		  .append("voteMap : ").append(voteSet).append("\n");
		 if (leader != null) {
			 sb.append("leader : ").append(leader.getIp()).append(":").append(leader.getPort()).append("\n");			 
		 } else {
			 sb.append("leader : ").append("-").append("\n");
		 }
		 sb.append("localnode : ").append(localnode.getIp()).append(":").append(localnode.getPort()).append("\n")
		   .append("]");
		return "";
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
			//开始异步选举
			startElection();
		} finally {
			lock.unlock();
		}
		return STATE.getState() == NodeState.FOLLOWER.getState();
	}
	//初始化线程池
	private static ExecutorService electionExec = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
					new LinkedBlockingQueue<Runnable>());
	
	//心跳线程
	private static ExecutorService heartbeatExec = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>());
	
	/**
	 * candidate异步选举过程
	 */
	private static void startElection() {
		//周期+1
		term++;
		clearVariables();		
		//当前运行任务停止
		electionExec.shutdownNow();
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
				//当前运行任务停止
				electionExec.shutdownNow();
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
			//满足下面条件之一：
			//1）follower状态，未发生选举或者选举周期号小于请求周期号 YES 
			//2）candidate状态，选举周期号小于请求周期号 YES
			//3）leader状态，选举周期号小于请求周期号 YES
			if (STATE.getState() == NodeState.FOLLOWER.getState()) {
				//未发生选举或者周期号小于请求周期号
				if (voteSet.isEmpty() || term > voteTerm) {
					//变更本地状态
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
				//candidate状态，收到高term的投票请求
				if (term > voteTerm) {
					//当前状态转为follower，并给请求方投票
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
					//0.转为follower状态
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
					//1.停止发送心跳信息
					//TODO
					
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
			//只处理同一个周期内，给自己投票的结果
			if (term == rterm && result == 1) {
				NodeInfo node = new NodeInfo();
				node.setIp(ip);
				node.setPort(port);
				node.setVoted(true);
				if (!voteSet.contains(node)) {
					voteSet.add(node);
					//超过半数，当前节点成为leader
					if (voteSet.size() >= (NODELIST.size() + 1) / 2 && leader != null && !leader.equals(localnode)) {
						//leader开始向其他节点发送心跳请求
						heartbeatExec.shutdownNow();
						for (int i = 0; i < NODELIST.size(); i++) {
							NodeInfo nodeInfo = NODELIST.get(i);
							if (!nodeInfo.equals(localnode)) {
								heartbeatExec.submit(new HeartBeatTask(nodeInfo, term));
							}
						}
					}
				}
			}
		} finally {
			lock.unlock();
		}
	}

}
