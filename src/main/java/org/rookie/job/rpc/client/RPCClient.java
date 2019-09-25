package org.rookie.job.rpc.client;

import java.net.Socket;
import java.util.Iterator;
import java.util.Map;

import org.rookie.job.cfg.LuckieConfig;
import org.rookie.job.rpc.proto.LuckieProto;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Builder;
import org.rookie.job.rpc.proto.LuckieProto.Luckie.Event;

/**
 * RPC client
 *
 * Author: 不二
 *
 * Copyright @ 2019
 * 
 */
public class RPCClient {

	private static String ip;
    
    public static void init() {
    	ip = "127.0.0.1";
    }

    public static LuckieProto.Luckie callRemote(Event event, Map<String, String> data) throws Throwable {
        Socket socket = new Socket(ip, LuckieConfig.LISTEN_PORT);
        try {
        	Builder luckieBuilder = LuckieProto.Luckie.newBuilder();
        	if (data != null) {
        		//set data
        		Iterator<String> dataIter = data.keySet().iterator();
        		while (dataIter.hasNext()) {
					String key = dataIter.next();
					luckieBuilder.putData(key, data.get(key));
				}
        	}
        	LuckieProto.Luckie luckie = luckieBuilder.setEvent(event).build();
            luckie.writeDelimitedTo(socket.getOutputStream());
            socket.getOutputStream().flush();
			LuckieProto.Luckie responsedLuckie = LuckieProto.Luckie.parseDelimitedFrom(socket.getInputStream());
			
            return responsedLuckie;
        } catch(Exception e) {
        	e.printStackTrace();
        	return null;
        } finally {
        	socket.shutdownInput();
            socket.shutdownOutput();
            socket.close();
        }
    }


}
