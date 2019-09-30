package org.rookie.job.rpc.client;

import java.util.Iterator;
import java.util.Map;

import org.rookie.job.rpc.message.MessageHandlerFactory;
import org.rookie.job.rpc.proto.LuckieProto;
import org.rookie.job.rpc.proto.LuckieProto.Luckie;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Builder;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Event;

import io.netty.channel.Channel;
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
public class RPCClientHandler extends SimpleChannelInboundHandler<Luckie> {

	private volatile Channel channel;

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		super.channelRegistered(ctx);
		channel = ctx.channel();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Luckie msg) throws Exception {
		MessageHandlerFactory.getHandler(msg).handleClient(msg.getDataMap());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (channel != null) {			
			channel.close();
		}
		ctx.close();
	}

	public void sendRequest(Event event, Map<String, String> data) {
		Builder luckieBuilder = LuckieProto.Luckie.newBuilder();
		if (data != null) {
			Iterator<String> dataIter = data.keySet().iterator();
			while (dataIter.hasNext()) {
				String key = dataIter.next();
				luckieBuilder.putData(key, data.get(key));
			}
		}
		LuckieProto.Luckie luckie = luckieBuilder.setEvent(event).build();
		channel.writeAndFlush(luckie);
	}

}
