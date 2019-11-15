package org.rookie.job.rpc.message.impl;

import org.rookie.job.rpc.proto.LuckieProto;
import org.rookie.job.rpc.proto.LuckieProto.Luckie;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Builder;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Event;

import java.util.Map;

import org.rookie.job.raft.election.ElectionProcess;
import org.rookie.job.rpc.client.RPCClient;
import org.rookie.job.rpc.message.IMessageHandler;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class ReplicationHandler implements IMessageHandler {

	@Override
	public Luckie handleServer(Luckie request) {
		Builder builder = LuckieProto.Luckie.newBuilder();	
		builder.putData("source_ip", ElectionProcess.localnode.getIp());
		builder.putData("source_port", ElectionProcess.localnode.getPort() + "");
		builder.putData("term", ElectionProcess.term + "");
		Map<String, String> data = request.getDataMap();
		if ("getVoteNodes".equals(data.get("sub_event"))) {
			builder.putData("sub_event", "getVoteNodes");
			//客户端对leader请求当前节点集合的处理
			//如果当前节点是leader给予回复
			if (ElectionProcess.localnode.equals(ElectionProcess.leader)) {
				builder.putData("nodes", ElectionProcess.getClusterNodeString());
			}
		}
		Luckie lk = builder.setEvent(Event.REPLICATION).build();
		return lk;
	}

	@Override
	public void handleClient(Luckie response) {
		Map<String, String> data = response.getDataMap();
		if ("getVoteNodes".equals(data.get("sub_event"))) {
			RPCClient.getRPCClient(data.get("source_ip"), Integer.parseInt(data.get("source_port"))).addMessage(response);;
		}

	}

}
