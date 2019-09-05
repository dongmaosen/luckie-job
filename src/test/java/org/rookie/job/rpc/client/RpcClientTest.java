package org.rookie.job.rpc.client;

import org.junit.Test;
import org.rookie.job.cfg.LuckieConfig;
import org.rookie.job.rpc.server.service.ITestService;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class RpcClientTest {
	
	@Test
	public void rpcClientTest() {		
		ITestService ts = RpcConsumer.getService(ITestService.class, "127.0.0.1", LuckieConfig.LISTEN_PORT);
		System.out.println(ts.echo("hi"));
	}
}
