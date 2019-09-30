package org.rookie.job.rpc.client;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
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
public class RpcClientTest {
	
	@Test
	public void rpcClientTest() throws Throwable {		
		RPCClient client = new RPCClient("127.0.0.1"); 
		Event e = Event.ELECTION;
		Map<String, String> s = new HashMap<String, String>();
		s.put("k1", "v1");
		s.put("k2", "v2");
		client.callRemote(e, s);
		Thread.sleep(100000);
	}
}
