package org.rookie.job.rpc.server;

import java.util.Date;

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

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Client is active ......");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Client is inactive ......");
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Luckie request) throws Exception {
		heartbeatCounter = 0;
		LuckieProto.Luckie response = MessageHandlerFactory.getHandler(request).handleServer(request);
		ctx.writeAndFlush(response);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			//空闲6s之后触发（心跳包丢失）
			if (heartbeatCounter >= 3) {
				ctx.channel().close().sync();
				System.out.println("disconnect from client");
			} else {
				heartbeatCounter++;
				System.out.println("lost heartbeat packet of (" + heartbeatCounter + "), time :" + new Date());
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}
