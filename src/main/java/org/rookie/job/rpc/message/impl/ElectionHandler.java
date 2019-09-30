package org.rookie.job.rpc.message.impl;

import java.util.Map;

import org.rookie.job.rpc.message.IMessageHandler;
import org.rookie.job.rpc.proto.LuckieProto;
import org.rookie.job.rpc.proto.LuckieProto.Luckie;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Builder;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Event;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class ElectionHandler implements IMessageHandler {

	@Override
	public Luckie handleServer(Map<String, String> dataMap) {
		Builder luckieBuilder = LuckieProto.Luckie.newBuilder();
		//TODO 处理选举逻辑，返回选举结果
		luckieBuilder.putData("test-k1", "test-v1");
		return luckieBuilder.setEvent(Event.ELECTION).build();
	}

	@Override
	public Luckie handleClient(Map<String, String> dataMap) {
		// TODO Auto-generated method stub
		return null;
	}

}
