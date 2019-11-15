package org.rookie.job.rpc.message.impl;

import org.rookie.job.rpc.proto.LuckieProto;
import org.rookie.job.rpc.proto.LuckieProto.Luckie;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Builder;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Event;

import java.util.Map;

import org.rookie.job.raft.election.ElectionProcess;
import org.rookie.job.rpc.message.IMessageHandler;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class HeartBeatHandler implements IMessageHandler {

	@Override
	public Luckie handleServer(Luckie request) {
		//处理连接请求发过来的心跳
		Map<String, String> data = request.getDataMap();
		ElectionProcess.processReceiverHearbeat(data);
		//返回
		Builder builder = LuckieProto.Luckie.newBuilder();	
		builder.putData("state", ElectionProcess.STATE.getState() + "");
		builder.putData("term", ElectionProcess.term + "");
		builder.putData("source_ip", ElectionProcess.localnode.getIp());
		builder.putData("source_port", ElectionProcess.localnode.getPort() + "");
		builder.putData("type", data.get("type"));
		Luckie lk = builder.setEvent(Event.HEART_BEAT).build();
//		System.out.println(ElectionProcess.getElectionData());
		return lk;
	}

	@Override
	public void handleClient(Luckie response) {
		ElectionProcess.proccessSenderReceiverHeartbeat(response);
	}

}
