package org.rookie.job.rpc.server;

import java.net.InetSocketAddress;
import java.util.Date;

import org.rookie.job.raft.election.ElectionProcess;
import org.rookie.job.raft.enums.NodeState;
import org.rookie.job.rpc.message.MessageHandlerFactory;
import org.rookie.job.rpc.proto.LuckieProto;
import org.rookie.job.rpc.proto.LuckieProto.Luckie;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

/**
 *
 *
 * Author: 不二
 *
 * Copyright @ 2019
 * 
 */
public class RPCServerHandler extends SimpleChannelInboundHandler<Luckie> {

	private int heartbeatCounter = 0;
	
	/**
	 * 服务端的IP
	 */
	private String ip;
	
	/**
	 * 服务端的PORT
	 */
	private int port;
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		InetSocketAddress socket = (InetSocketAddress) ctx.channel().remoteAddress();
		ip = socket.getAddress().getHostAddress();
		port = socket.getPort();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		//连接的接收端（服务端）关闭处理：分状态处理
		System.out.println("RPCServerHandler channelInactive");
		if (ElectionProcess.STATE.getState() == NodeState.LEADER.getState()) {
			//当前状态是leader，要检查leader现存节点是否满足继续成为leader的可能性
			ElectionProcess.processFollowerDisconnectByLeaderServer(ip, port);
		}
		//非leader状态暂时不需要处理
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Luckie request) throws Exception {
		System.out.println("RPCServerHandler channelRead0 : " + request.getEventValue() + "-" + request.getDataMap());
		//接到消息，计数器清零
		heartbeatCounter = 0;
		LuckieProto.Luckie response = MessageHandlerFactory.getHandler(request).handleServer(request);
		ctx.writeAndFlush(response);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			System.out.println("recving heartbeat packet " + evt);
			//空闲6s之后触发（心跳包丢失）
			if (heartbeatCounter >= 10) {
				//连续10次没有收到心跳请求（1分钟），接收端则认为失联了，目
				//前是leader发心跳，所以此处接收端非leader，异常由程序主逻辑处理，此处不处理
				System.out.println("disconnect from client : heartbeatCounter >= 10 " + ip + " , " + port);
			} else {
				heartbeatCounter++;
				System.out.println("lost heartbeat packet of (" + heartbeatCounter + "), time :" + new Date());
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
