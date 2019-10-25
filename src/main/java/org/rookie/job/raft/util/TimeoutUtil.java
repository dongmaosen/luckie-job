package org.rookie.job.raft.util;
/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class TimeoutUtil {
	/**
	 * amount of time a follower waits until becoming a candidate (1500ms, 3000ms)
	 * @return
	 */
	public static long getElectionTimeoutMilliseconds() {
		return (long) (5000 * (1 + Math.random()));
	}
	
	private static long heartbeatTimemilliseconds = 500;
	
	public static long getHearbeatTime() {
		return heartbeatTimemilliseconds;
	}
	
	
	public static void main(String[] args) {
		System.out.println(getElectionTimeoutMilliseconds());
	}
}
