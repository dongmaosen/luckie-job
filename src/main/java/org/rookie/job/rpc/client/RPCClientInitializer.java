package org.rookie.job.rpc.client;

import org.rookie.job.rpc.proto.LuckieProto.Luckie;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class RPCClientInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline p = ch.pipeline();
		p.addLast(new ProtobufVarint32FrameDecoder())
		 .addLast(new ProtobufDecoder(Luckie.getDefaultInstance()))
		 .addLast(new ProtobufVarint32LengthFieldPrepender())
		 .addLast(new ProtobufEncoder())
		 .addLast(new RPCClientHandler());
	}

}
