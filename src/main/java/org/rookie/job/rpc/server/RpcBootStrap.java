package org.rookie.job.rpc.server;

import org.rookie.job.cfg.LuckieConfig;
import org.rookie.job.common.LuckieShutdownHandler;
import org.rookie.job.common.OSService;
import org.rookie.job.raft.election.ElectionProcess;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
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
	 * 服务端启动
	 * 
	 * @throws Exception
	 */
	public static void start() throws Exception {
		// 1.初始化signal 2.根据系统类型，获取对应的信号
		Signal signal = new Signal(OSService.getOSSingalType());
		// 3.实现并注册SignalHandler实例到signal
		// 4.在SignalHandler的handle回调接口中，初始化ShutdownHook线程，并注册到Runtime的ShutdownHook中
		Signal.handle(signal, new LuckieShutdownHandler());
		// 6.启动服务端主程序
		publish();
	}

	static EventLoopGroup bossGroup = new NioEventLoopGroup(1);
	static EventLoopGroup workerGroup = new NioEventLoopGroup();

	/**
	 * rpc服务端发布可提供服务的接口供调用
	 * 
	 * @throws Exception
	 */
	private static void publish() throws Exception {
		ServerBootstrap bootStrap = new ServerBootstrap();
		bootStrap.group(bossGroup, workerGroup)
				 .channel(NioServerSocketChannel.class)
				 .handler(new LoggingHandler(LogLevel.INFO))
				 .childHandler(new RPCServerMessageInitializer());
		ChannelFuture f = bootStrap.bind(LuckieConfig.LISTEN_PORT).sync();
		System.out.println(ElectionProcess.localnode.getPort() + " 通道侦听中！");
		f.channel().closeFuture().sync();
		System.out.println(ElectionProcess.localnode.getPort() + " 通道关闭了！");
	}

	public static void shutdown() {
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}

}
