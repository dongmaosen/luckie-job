package org.rookie.job.common;

import org.junit.Test;
import org.rookie.job.rpc.server.RpcBootStrap;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class SystemTest {
	
	@Test
	public void testShutdownHook() throws Exception {
		RpcBootStrap.start();
	}
	
	public static void main(String[] args) throws Exception {
		RpcBootStrap.start();
		//Ctrl+C 看效果
	}
}
