package org.rookie.job.raft;

import org.junit.Test;
import org.rookie.job.BootStrap;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class RaftTest10088 {
	@Test
	public void electionTestNode() throws Exception {
		BootStrap.start(null, 10088);
		Thread.sleep(Integer.MAX_VALUE);
	}
	
	public static void main(String[] args) throws Exception {
		BootStrap.start("127.0.0.1", 10088);
		Thread.sleep(Integer.MAX_VALUE);
	}
}
