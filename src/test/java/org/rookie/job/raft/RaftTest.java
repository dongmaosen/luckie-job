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
public class RaftTest {
	@Test
	public void electionTestNode1() throws Exception {
		BootStrap.start(null, -1);
//		BootStrap.start("", 10087);
//		BootStrap.start("", 10088);
		Thread.sleep(Integer.MAX_VALUE);
	}
	
	public static void main(String[] args) throws Exception {
		BootStrap.start("127.0.0.1", 10087);
		Thread.sleep(Integer.MAX_VALUE);
	}
}
