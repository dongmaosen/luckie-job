package org.rookie.job.rpc.message.impl;

import org.rookie.job.rpc.proto.LuckieProto;
import org.rookie.job.rpc.proto.LuckieProto.Luckie;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Builder;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Event;

import io.netty.util.ReferenceCountUtil;

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
		ReferenceCountUtil.release(request);
		Builder builder = LuckieProto.Luckie.newBuilder();
		builder.putData("pong", "ok");
		Luckie lk = builder.setEvent(Event.HEART_BEAT).build();
		return lk;
	}

	@Override
	public void handleClient(Luckie response) {
		ReferenceCountUtil.release(response);;
	}

}
