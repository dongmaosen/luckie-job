package org.rookie.job.rpc.message;

import java.util.Map;

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

	public Luckie handleServer(Map<String, String> dataMap);
	
	public Luckie handleClient(Map<String, String> dataMap);

}
