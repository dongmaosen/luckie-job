package org.rookie.job.rpc.client;

import java.util.HashMap;
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
	
	public static Map<String, RPCClient> connectionMap = new HashMap<String, RPCClient>();
	
	public static RPCClient getRPCClient(String ip, int port) {
		if (connectionMap.get(ip + "-" + port) == null) {
			RPCClient client = new RPCClient(ip, port);
			connectionMap.put(ip + "-" + port, client);
		}
		return connectionMap.get(ip + "-" + port);
	}
	
	private RPCClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
		b.group(group)
		 .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
		 .channel(NioSocketChannel.class)
		 .handler(new RPCClientInitializer());
	}
	/**
	 * 使用时，首先调用connect方法，超时时间后抛出异常
	 * @throws Exception
	 */
	public void connect() throws Exception{
		if (ch == null || !ch.isActive()) {			
			ch = b.connect(ip, port).sync().channel();
		}
	}
	/**
	 * 普通请求
	 * @param event
	 * @param data
	 * @throws Exception
	 */
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
     * 判断当前通道是否存活
     * @return
     */
    public boolean isAlive() {
    	return ch !=null && ch.isActive();
    }
    
    /**
     * 主动调用关闭连接的方法
     * @throws InterruptedException
     */
    public void close() throws InterruptedException {
		ch.closeFuture().sync();
    }

}
