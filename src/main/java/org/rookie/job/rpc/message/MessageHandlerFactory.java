package org.rookie.job.rpc.message;

import org.rookie.job.rpc.message.impl.ElectionHandler;
import org.rookie.job.rpc.message.impl.HeartBeatHandler;
import org.rookie.job.rpc.message.impl.ReplicationHandler;
import org.rookie.job.rpc.proto.LuckieProto.Luckie;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Event;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class MessageHandlerFactory {

	public static IMessageHandler getHandler(Luckie request) {
		IMessageHandler handler = null;
		switch (request.getEvent().getNumber()) {
		case Event.ELECTION_VALUE:
			handler = new ElectionHandler();
			break;
		case Event.HEART_BEAT_VALUE:
			handler = new HeartBeatHandler();
			break;
		case Event.REPLICATION_VALUE:
			handler = new ReplicationHandler();
			break;
		default:
			break;
		}
		return handler;
	}

}
