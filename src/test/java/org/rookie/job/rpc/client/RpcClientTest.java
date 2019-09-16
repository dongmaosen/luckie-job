package org.rookie.job.rpc.client;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Event;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class RpcClientTest {
	
	@Test
	public void rpcClientTest() throws Throwable {		
		RPCClient.init();
		Event e = Event.ELECTION;
		Map<String, String> s = new HashMap<String, String>();
		s.put("k1", "v1");
		s.put("k2", "v2");
		RPCClient.invoke(e, s);
	}
}
