package org.rookie.job;

import org.omg.PortableServer.THREAD_POLICY_ID;
import org.rookie.job.cfg.LuckieConfig;
import org.rookie.job.raft.RaftBootstrap;
import org.rookie.job.rpc.server.RpcBootStrap;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class BootStrap {
	/**
	 * 整个服务端初始化类
	 * @param ip set null as default
	 * @param port set -1 as default
	 * @throws Exception 
	 */
	public static void start(String ip, int port) throws Exception {
		if (ip != null) {
			LuckieConfig.IP = ip;
		}
		if (port != -1) {
			LuckieConfig.LISTEN_PORT = port;
		}
		//0.初始化配置信息
		LuckieConfig.init();
		//1.初始化RPC服务，接收来自其他节点的信息
		RpcBootStrap.start();
		//2.初始化本地选举流程
		RaftBootstrap.init();
	}
}
