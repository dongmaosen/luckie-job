package org.rookie.job.raft;

import java.util.Timer;
import java.util.TimerTask;

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
public class RaftBootstrap extends TimerTask{
	/**
	 * 定时器，设定超时时间的
	 */
	private static Timer timer = new Timer();
	
	private static RaftBootstrap instance = new RaftBootstrap(TimeoutUtil.getElectionTimeoutMilliseconds());
	
	private RaftBootstrap(long electionTimeoutMilliseconds) {
		ElectionProcess.electionTimeout = electionTimeoutMilliseconds;
	}
	
	private static volatile boolean running = false; 
	
	/**
	 * 外部调用的初始化方法
	 */
	public static void init() {
		if (!running) {
			running = true;
			//选举启动，选举超时时间后开始进入选举状态
			System.out.println("选举启动，选举超时时间后开始进入选举状态: " + ElectionProcess.electionTimeout + " ms 后开始调度");
			timer.schedule(instance, ElectionProcess.electionTimeout);
		}
	}

	/**
	 * 选举超时时间后，执行的方法
	 */
	@Override
	public void run() {
		if (ElectionProcess.STATE.getState() == NodeState.FOLLOWER.getState()) {
			//1.在follower状态超时，进入到candidate状态
			System.out.println("在follower状态超时，进入到candidate状态");
			ElectionProcess.followerToCandidate();
			//2.进入新一轮的等待
			timer.schedule(instance, ElectionProcess.electionTimeout);
		} else if (ElectionProcess.STATE.getState() == NodeState.CANDIDATE.getState()) {
			//2.原来就是candidate，超时后进入新一轮的选举
			ElectionProcess.candidateToNextRound();
			timer.schedule(instance, ElectionProcess.electionTimeout);
		} else if (ElectionProcess.STATE.getState() == NodeState.LEADER.getState()) {
			//TODO 当前为leader，暂时不做什么
		}
		
	}
	
}
