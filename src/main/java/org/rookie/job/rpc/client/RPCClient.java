package org.rookie.job.rpc.client;

import java.util.Iterator;
import java.util.Map;

import org.rookie.job.rpc.proto.LuckieProto;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Builder;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Event;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
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
	Channel ch;
	
	public RPCClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	/**
	 * 使用时，首先调用connect方法，超时时间后抛出异常
	 * @throws Exception
	 */
	public void connect() throws Exception{
		b.group(group)
		 .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
		 .channel(NioSocketChannel.class)
	     .handler(new RPCClientInitializer());
	    ch = b.connect(ip, port).sync().channel();
	}
    public void commonCall(Event event, Map<String, String> data) throws Exception {
    	try {
			Builder luckieBuilder = LuckieProto.Luckie.newBuilder();
			if (data != null) {
				Iterator<String> dataIter = data.keySet().iterator();
				while (dataIter.hasNext()) {
					String key = dataIter.next();
					luckieBuilder.putData(key, data.get(key));
				}
			}
			LuckieProto.Luckie luckie = luckieBuilder.setEvent(event).build();
			ch.writeAndFlush(luckie);			
    	} finally {
    		group.shutdownGracefully();
		}
        
    }
    
    /**
     * 主动调用关闭连接的方法
     * @throws InterruptedException
     */
    public void close() throws InterruptedException {
		ch.closeFuture().sync();
    }

}
