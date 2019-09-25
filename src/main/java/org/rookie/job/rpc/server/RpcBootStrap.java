package org.rookie.job.rpc.server;


import java.net.ServerSocket;
import java.net.Socket;

import org.rookie.job.cfg.LuckieConfig;
import org.rookie.job.common.LuckieShutdownHandler;
import org.rookie.job.common.OSService;
import org.rookie.job.rpc.proto.LuckieProto;

import org.rookie.job.rpc.server.message.MessageHandlerFactory;

import sun.misc.Signal;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class RpcBootStrap {
	
	/**
	 * 标识是否对外提供服务
	 */
	private static Boolean Running = true;
	
	/**
	 * 服务端启动
	 * @throws Exception 
	 */
	public static void start() throws Exception {
		//1.初始化signal 2.根据系统类型，获取对应的信号
		Signal signal = new Signal(OSService.getOSSingalType());
		//3.实现并注册SignalHandler实例到signal 4.在SignalHandler的handle回调接口中，初始化ShutdownHook线程，并注册到Runtime的ShutdownHook中
		Signal.handle(signal, new LuckieShutdownHandler());
		//6.启动服务端主程序
		publish();
	}
	
	/**
	 * rpc服务端发布可提供服务的接口供调用
	 * @throws Exception
	 */
	private static void publish() throws Exception {
		//TODO 后续引入netty
		ServerSocket server = new ServerSocket(LuckieConfig.LISTEN_PORT);
		Socket client = null;
		while (Running) {
			client = server.accept();
			//TODO 线程池
			try {
				LuckieProto.Luckie request = LuckieProto.Luckie.parseDelimitedFrom(client.getInputStream());
				LuckieProto.Luckie response= MessageHandlerFactory.getHandler(request).handle(request.getDataMap());
	        	response.writeDelimitedTo(client.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				client.close();
			}
		}
		server.close();
	}
	
	public static void shutdown() {
		Running = false;
	}

}
