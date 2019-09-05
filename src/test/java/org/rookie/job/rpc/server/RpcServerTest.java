package org.rookie.job.rpc.server;

import org.junit.Test;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class RpcServerTest {
	
	@Test
	public void startRpcServer() throws Exception {
		RpcBootStrap.publish();		
	}
	
	public static void main(String[] args) throws Exception {
		RpcBootStrap.publish();
	}
}
