package org.rookie.job.raft;

import org.rookie.job.raft.election.ElectionProcess;
import org.rookie.job.raft.enums.NodeState;
import org.rookie.job.raft.util.TimeoutUtil;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class RaftBootstrap {
	/**
	 * 定时器，设定超时时间的
	 */
//	private static Timer timer = new Timer();
		
	private RaftBootstrap() {
		
	}
	
	private static volatile long startTime = -1;
	
	private static volatile boolean running = false; 
		
	/**
	 * 外部调用的初始化方法
	 */
	public static void init() {
		if (!running) {
			ElectionProcess.electionTimeout = TimeoutUtil.getElectionTimeoutMilliseconds();
			running = true;
			//选举启动，选举超时时间后开始进入选举状态
			System.out.println("选举启动，选举超时时间后开始进入选举状态: " + ElectionProcess.electionTimeout + " ms 后开始调度");
			startTime = System.currentTimeMillis();
			start();
		}
	}

	private static void start() {
		//一个节点，单机模式
		if (ElectionProcess.NODELIST.size() == 1) {
			ElectionProcess.followerToLeader();
			return;
		}
		while (true && running) {
			if (System.currentTimeMillis() - startTime > ElectionProcess.electionTimeout) {
				if (ElectionProcess.STATE.getState() == NodeState.FOLLOWER.getState()) {
					//1.在follower状态超时，进入到candidate状态
					System.out.println(ElectionProcess.localnode.getPort()+ " 在follower状态超时，进入到candidate状态");
					ElectionProcess.followerToCandidate();
					//2.进入新一轮的等待
				} else if (ElectionProcess.STATE.getState() == NodeState.CANDIDATE.getState()) {
					//2.原来就是candidate，超时后进入新一轮的选举
					ElectionProcess.candidateToNextRound();
				}
				//一次循环后，不管当前节点为什么状态，都进入下一个周期
				startTime = System.currentTimeMillis();
			} else {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void reSchedule() {
		startTime = System.currentTimeMillis();
	}
	
}
