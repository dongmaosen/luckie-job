package org.rookie.job.rpc.client;

import java.util.Iterator;
import java.util.Map;

import org.rookie.job.rpc.proto.LuckieProto;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Builder;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Event;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * RPC client
 *
 * Author: 不二
 *
 * Copyright @ 2019
 * 
 */
public class RPCClient {

	EventLoopGroup group = new NioEventLoopGroup();
	Bootstrap b = new Bootstrap();

	private String ip;
	private int port;
	public RPCClient(String ip, int port) throws Exception {
		this.ip = ip;
		this.port = port;
	}
	public void connect() throws Exception {
		b.group(group)
		 .channel(NioSocketChannel.class)
	     .handler(new RPCClientInitializer());
		Channel ch = b.connect(ip, port).sync().channel();
	}
    public void commonCall(Event event, Map<String, String> data) throws Throwable {
    	try {
			
			//新建一个连接
					
			Builder luckieBuilder = LuckieProto.Luckie.newBuilder();
			if (data != null) {
				Iterator<String> dataIter = data.keySet().iterator();
				while (dataIter.hasNext()) {
					String key = dataIter.next();
					luckieBuilder.putData(key, data.get(key));
				}
			}
			LuckieProto.Luckie luckie = luckieBuilder.setEvent(event).build();
//			ch.writeAndFlush(luckie);
//			
//			ch.closeFuture().sync();
			
    	} finally {
    		group.shutdownGracefully();
		}
        
    }

	public void sendRequest(Event event, Map<String, String> data) {
		
		
	}


}
