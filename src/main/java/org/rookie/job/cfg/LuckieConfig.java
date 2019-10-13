package org.rookie.job.cfg;

import java.util.ResourceBundle;

import org.rookie.job.raft.election.ElectionProcess;
import org.rookie.job.raft.election.NodeInfo;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class LuckieConfig {
	
	public static String IP;
	
	/**
	 * local rpc listener port
	 */
	public static int LISTEN_PORT = -1;
	
	/**
	 * cluster ip&port array
	 */
	
	public static void init() {
		
		ResourceBundle rb = ResourceBundle.getBundle("luckie");
		
		if (IP == null) {			
			IP = rb.getString("local_ip");
		}
		if (LISTEN_PORT == -1) {			
			LISTEN_PORT = Integer.parseInt(rb.getString("listen_port"));
		}
		
		String[] nodes = rb.getString("cluster").split(";");
		for (int i = 0; i < nodes.length; i++) {
			String ip = nodes[i].split(":")[0];
			int port = Integer.parseInt(nodes[i].split(":")[1]);
			NodeInfo ni = new NodeInfo();
			ni.setIp(ip);
			ni.setPort(port);
			if (IP.equals(ip) && LISTEN_PORT == port) {
				ni.setLocal(true);
				ElectionProcess.localnode = ni;
			}
			ElectionProcess.NODELIST.add(ni);
		}
	}
	
}
