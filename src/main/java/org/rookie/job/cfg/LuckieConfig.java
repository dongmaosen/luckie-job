package org.rookie.job.cfg;

import java.util.ResourceBundle;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class LuckieConfig {
	
	/**
	 * local rpc listener port
	 */
	public static int LISTEN_PORT;
	/**
	 * cluster ip&port array
	 */
	public static String[] CLUSTER_IP_PORT;
	
	static {
		ResourceBundle rb = ResourceBundle.getBundle("luckie");
		//init port
		LISTEN_PORT = Integer.parseInt(rb.getString("listen_port"));
		
		//init cluster
		CLUSTER_IP_PORT = rb.getString("cluster").split(";");
		
	}
	
}
