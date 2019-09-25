package org.rookie.job.rpc.server.message.impl;

import java.util.Map;

import org.rookie.job.rpc.proto.LuckieProto;
import org.rookie.job.rpc.proto.LuckieProto.Luckie;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Builder;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Event;
import org.rookie.job.rpc.server.message.IMessageHandler;

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
	public Luckie handle(Map<String, String> dataMap) {
		Builder luckieBuilder = LuckieProto.Luckie.newBuilder();
		//TODO 处理选举逻辑，返回选举结果
		luckieBuilder.putData("test-k1", "test-v1");
		return luckieBuilder.setEvent(Event.ELECTION).build();
	}

}
