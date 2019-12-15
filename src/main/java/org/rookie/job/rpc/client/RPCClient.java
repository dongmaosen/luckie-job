package org.rookie.job.rpc.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang.StringUtils;
import org.rookie.job.raft.election.NodeInfo;
import org.rookie.job.rpc.proto.LuckieProto;
import org.rookie.job.rpc.proto.LuckieProto.Luckie;
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

	EventLoopGroup group = new NioEventLoopGroup(12);
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
		group.shutdownGracefully();
    }
    
    /**
     * 主动关闭所有的连接
     * @throws InterruptedException 
     */
    public static void shutdownAll() throws InterruptedException {
    	Iterator<String> keys = connectionMap.keySet().iterator();
    	while (keys.hasNext()) {
    		connectionMap.get(keys.next()).close();
    	}
    }

    private BlockingQueue<Luckie> answer = new LinkedBlockingQueue<Luckie>();
    
	public List<NodeInfo> getVoteNodes() {
		List<NodeInfo> list = new ArrayList<NodeInfo>();
		try {
			//1.连接
			connect();
			//2.请求数据
			Event event = Event.REPLICATION;
			Map<String, String> data = new HashMap<String, String>(8);
			data.put("sub_event", "getVoteNodes");
			commonCall(event, data);
			//3.等待请求结果
			Luckie luckie = null;
			for (;;) {
				luckie = answer.take();
				break;
			}
			//4.结果解析&封装
			String nodeString = luckie.getDataMap().get("nodes");
			System.out.println("cluster node string " + nodeString);
			if (StringUtils.isNotBlank(nodeString)) {
				String[] nodes = nodeString.split(";");
				for (String node : nodes) {
					if (StringUtils.isNotBlank(node)) {
						NodeInfo n = new NodeInfo();
						String[] singleNode = node.split(":");
						n.setIp(singleNode[0]);
						n.setPort(Integer.parseInt(singleNode[1]));
						list.add(n);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("RPCClient getVoteNodes : " + e.getMessage());
		}
		return list;
	}
	
	public void addMessage(Luckie luckie) {
		answer.add(luckie);
	}

}
