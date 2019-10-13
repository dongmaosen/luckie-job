package org.rookie.job.raft.election;
/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class NodeInfo {
	/**
	 * IP地址
	 */
	private String ip;
	
	/**
	 * 侦听端口
	 */
	private int port;
	
	/**
	 * 是否为本地节点（true-是 false-否）
	 */
	private boolean local = false;
	
	/**
	 * 是否参与过选举（true-是，false-否）
	 */
	private boolean voted = false;
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public boolean isLocal() {
		return local;
	}
	public void setLocal(boolean local) {
		this.local = local;
	}
	public boolean isVoted() {
		return voted;
	}
	public void setVoted(boolean voted) {
		this.voted = voted;
	}
	
}
