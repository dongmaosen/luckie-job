package org.rookie.job.rpc.server;

import org.rookie.job.rpc.proto.LuckieProto;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class RPCServerMessageInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new ProtobufVarint32FrameDecoder())
		        .addLast(new ProtobufDecoder(LuckieProto.Luckie.getDefaultInstance()))
		        .addLast(new ProtobufVarint32LengthFieldPrepender())
		        .addLast(new ProtobufEncoder())
		        .addLast(new IdleStateHandler(6, 0, 0))
		        .addLast(new RPCServerHandler());
	}
	
}
