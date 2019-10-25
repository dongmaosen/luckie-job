package org.rookie.job.rpc.client;

import org.rookie.job.raft.election.ElectionProcess;
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
	protected void channelRead0(ChannelHandlerContext ctx, Luckie msg) throws Exception {
		MessageHandlerFactory.getHandler(msg).handleClient(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("RPCClientHandler exceptionCaught");
		if (channel != null) {
			channel.close();
		}
		ctx.close();
		ctx.fireExceptionCaught(cause);
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		//TODO 连接断开，可能时机（leader心跳发方要处理的，投票发起方要处理的）
		System.out.println("RPCClientHandler channelInactive");
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
		luckieBuilder.putData("sub_event", "1");
		luckieBuilder.putData("state", ElectionProcess.STATE.getState() + "");
		luckieBuilder.putData("term", ElectionProcess.term + "");
		luckieBuilder.putData("source_ip", ElectionProcess.localnode.getIp());
		luckieBuilder.putData("source_port", ElectionProcess.localnode.getPort() + "");
		LuckieProto.Luckie luckie = luckieBuilder.setEvent(event).build();
		ctx.writeAndFlush(luckie);
	}

}
