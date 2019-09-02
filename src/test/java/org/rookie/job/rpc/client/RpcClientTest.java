package org.rookie.job.rpc.client;

import org.rookie.job.rpc.Constants;
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
	public static void main(String[] args) {
        ITestService ts = RpcConsumer.getService(ITestService.class, "127.0.0.1", Constants.SERVER_PORT);
        System.out.println(ts.echo("hi"));
    }
}
