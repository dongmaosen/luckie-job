package org.rookie.job.rpc.message;

import org.rookie.job.rpc.proto.LuckieProto.Luckie;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public interface IMessageHandler {

	public Luckie handleServer(Luckie request);
	
	public void handleClient(Luckie response);

}
