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
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.rookie.job.cfg.LuckieConfig;
import org.rookie.job.raft.enums.NodeState;

public class ElectionProcess {
	//---选举变量 S---
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
	public static Set<NodeInfo> voteMap = new HashSet<NodeInfo>();

	/**
	 * init state
	 */
	public static NodeState STATE = NodeState.FOLLOWER;
	
	/**
	 * 选举超时时间
	 */
	public static long electionTimeout = -1;
	
	/**
	 * 已经等待进入选举流程的时间
	 */
	public static long waitTimeMilliseconds = 0;
	
	//---选举变量 E---
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
	
	public static String getElectionData() {
		//TODO
		return "";
	}


	
	/**
	 * become a candidate before election
	 */
	public static boolean followerToCandidate() {
		try {
			lock.lock();
			if (STATE.getState() == NodeState.FOLLOWER.getState()) {			
				STATE = NodeState.CANDIDATE;
			}
		} finally {
			lock.unlock();
		}
		return STATE.getState() == NodeState.FOLLOWER.getState();
	}
	
	private static void voteForSelf() {
		voteMap.add(localnode);
	}
	
	/**
	 * 
	 * @return
	 */
	public static boolean toCandidate() {
		//没有参与选举并且没有leader可以进入候选（candidate）状态
		return voteMap.isEmpty() && leader == null;
	}

}
