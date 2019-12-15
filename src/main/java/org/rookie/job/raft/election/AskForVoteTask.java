package org.rookie.job.raft.election;

import java.util.HashMap;
import java.util.Map;

import org.rookie.job.rpc.client.RPCClient;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Event;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class AskForVoteTask implements Runnable {

	private NodeInfo node;
	
	private int term;
	
	public AskForVoteTask(NodeInfo node, int term) {
		this.node = node;
		this.term = term;
	}

	@Override
	public void run() {
		//周期term内(防止跨周期还在访问)，请求node节点投票给自己
		RPCClient rpc = RPCClient.getRPCClient(node.getIp(), node.getPort());
		while (term == ElectionProcess.term) {
			try {
				//首先连接
				rpc.connect();
				//连接时间太长，导致周期变了，本次就不处理了，进入下个周期
				if (term == ElectionProcess.term) {
					Event event = Event.ELECTION;
					Map<String, String> data = new HashMap<String, String>();
					data.put("sub_event", "1");
					data.put("term", term + "");
					data.put("source_ip", ElectionProcess.localnode.getIp());
					data.put("source_port", ElectionProcess.localnode.getPort() + "");
					rpc.commonCall(event, data);
				} 
				break;
			} catch (Exception e) {
//				System.err.println(Thread.currentThread().getName() + " term " + term + ", connect to " + node.getPort() + " error : " + e.getMessage());
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					
				}
			}
		}
	}

}
