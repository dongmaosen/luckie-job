package org.rookie.job.rpc.server;

import org.rookie.job.rpc.proto.LuckieProto;
import org.rookie.job.rpc.proto.LuckieProto.Luckie;
import org.rookie.job.rpc.message.MessageHandlerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class RPCServerHandler extends SimpleChannelInboundHandler<Luckie> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Luckie request) throws Exception {
		LuckieProto.Luckie response= MessageHandlerFactory.getHandler(request).handleServer(request.getDataMap());
		ctx.writeAndFlush(response);
	}

}
