package org.rookie.job.rpc.server.message;

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

	public Luckie handle(Map<String, String> dataMap);

}
