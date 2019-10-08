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
		RPCClient client = new RPCClient("127.0.0.1"); 
		Event e = Event.HEART_BEAT;
		Map<String, String> s = new HashMap<String, String>();
		s.put("k1", "v1");
		s.put("k2", "v2");
		s.put("k3", "v3");
		try {
			client.commonCall(e, s);			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	public static void main(String[] args) throws Throwable {
		RpcClientTest test = new RpcClientTest();
		test.rpcClientTest();
	}
}
