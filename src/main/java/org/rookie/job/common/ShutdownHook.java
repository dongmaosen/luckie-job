package org.rookie.job.common;

import org.rookie.job.rpc.client.RPCClient;
import org.rookie.job.rpc.server.RpcBootStrap;

/**
 *
 *
 * Author: 不二
 *
 * Copyright @ 2019
 * 
 */
public class ShutdownHook implements Runnable {

	@Override
	public void run() {
		//1.清理连接资源RPCClient
		try {
			RPCClient.shutdownAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//2.关闭服务端
		RpcBootStrap.shutdown();
	}

}
