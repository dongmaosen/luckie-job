package org.rookie.job.rpc.client;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.rookie.job.rpc.message.MessageHandlerFactory;
import org.rookie.job.rpc.proto.LuckieProto;
import org.rookie.job.rpc.proto.LuckieProto.Luckie;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Builder;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Event;

import io.netty.channel.Channel;
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
public class RPCClientHandler extends SimpleChannelInboundHandler<Luckie> {

	private volatile Channel channel;

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		channel = ctx.channel();
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		// 断开重连机制
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Luckie msg) throws Exception {
		MessageHandlerFactory.getHandler(msg).handleClient(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (channel != null) {
			channel.close();
		}
		ctx.close();
		ctx.fireExceptionCaught(cause);
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		//TODO 断开重连机制
		
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			//发送心跳包
			sendHeartbeatPacket(ctx);
		}
	}
	
	private void sendHeartbeatPacket(ChannelHandlerContext ctx) {
		Builder luckieBuilder = LuckieProto.Luckie.newBuilder();
		Event event = Event.HEART_BEAT;
		luckieBuilder.putData("ping", "ok");
		LuckieProto.Luckie luckie = luckieBuilder.setEvent(event).build();
		ctx.writeAndFlush(luckie);
	}
	
	/**
	 * 每读取一次，都会有回调该方法
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelReadComplete : " + ctx.name());
	}

}
