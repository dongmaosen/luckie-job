package org.rookie.job.rpc.message.impl;

import org.rookie.job.raft.election.ElectionProcess;
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
	public Luckie handleServer(Luckie request) {
		Builder luckieBuilder = LuckieProto.Luckie.newBuilder();
		if (ElectionProcess.processRequest(request)) {
			luckieBuilder.putData("result", "1");
		} else {
			luckieBuilder.putData("result", "0");
		}
		luckieBuilder.putData("term", request.getDataMap().get("term"));
		luckieBuilder.putData("sub_event", "2");
		luckieBuilder.putData("source_ip", ElectionProcess.localnode.getIp());
		luckieBuilder.putData("source_port", ElectionProcess.localnode.getPort() + "");
		return luckieBuilder.setEvent(Event.ELECTION).build();
	}

	@Override
	public void handleClient(Luckie response) {
		ElectionProcess.processElectionClient(response);
	}

}
