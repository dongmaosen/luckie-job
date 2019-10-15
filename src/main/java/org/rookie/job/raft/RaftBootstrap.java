package org.rookie.job.raft;

import java.util.Timer;
import java.util.TimerTask;

import org.rookie.job.raft.election.ElectionProcess;
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
	 * 调用用的定时器
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
			//等待ElectionTime的时间
			System.out.println("等待 " + ElectionProcess.electionTimeout + " ms 后开始调度");
			timer.schedule(instance, ElectionProcess.electionTimeout);
		}
	}

	/**
	 * 主执行方法
	 */
	@Override
	public void run() {
		// 然后判断是否要进入candidate状态，可以则进入下一个阶段，不可以，则保持follower状态
		
	}
	
}
