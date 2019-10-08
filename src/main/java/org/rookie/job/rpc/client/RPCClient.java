package org.rookie.job.rpc.client;

import java.util.Map;

import org.rookie.job.cfg.LuckieConfig;
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

	private String ip;
	
	public RPCClient(String ip) {
		this.ip = ip;
	}
    
    public void commonCall(Event event, Map<String, String> data) throws Throwable {
    	EventLoopGroup group = new NioEventLoopGroup();
    	try {
			Bootstrap b = new Bootstrap();
			b.group(group)
			 .channel(NioSocketChannel.class)
			 .handler(new RPCClientInitializer());
			//新建一个连接
			Channel ch = b.connect(ip, LuckieConfig.LISTEN_PORT).sync().channel();
			
			RPCClientHandler handler = ch.pipeline().get(RPCClientHandler.class);
		
			handler.sendRequest(event, data);
			//close会导致接收不到服务端的返回信息
			ch.closeFuture().sync();
			
    	} finally {
    		group.shutdownGracefully();
		}
        
    }
    


}
