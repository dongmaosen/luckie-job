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
	private static Timer timer = new Timer();
	
	private static RaftBootstrap instance = new RaftBootstrap(TimeoutUtil.getElectionTimeoutMilliseconds());
	
	private RaftBootstrap(long electionTimeoutMilliseconds) {
		ElectionProcess.electionTimeout = electionTimeoutMilliseconds;
	}
	/**
	 * 外部调用的初始化方法
	 */
	public static void init() {
		//等待ElectionTime的时间
		timer.schedule(instance, ElectionProcess.electionTimeout);
	}

	/**
	 * 主执行方法
	 */
	@Override
	public void run() {
		// 然后判断是否要进入candidate状态
		while(ElectionProcess.toCandidate()) {
			if (ElectionProcess.followerToCandidate()) {
				//进入候选人状态，开始进行选举，如果在electionTimeout时间内，没有选出leader，则清除本地信息，重新进行选举
				//TODO
				System.currentTimeMillis();
			}
		}
	}
	
}
