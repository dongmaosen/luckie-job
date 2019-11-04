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
	private String ip = "";
	
	/**
	 * 侦听端口
	 */
	private int port = -1;
	
	/**
	 * 是否为本地节点（true-是 false-否）
	 */
	private boolean local = false;
	
	/**
	 * 是否参与过选举（true-是，false-否）
	 */
	private boolean voted = false;
	
	private int term = 0;
	
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + port;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof NodeInfo) {
			if (((NodeInfo) obj).getIp().equals(getIp()) && ((NodeInfo) obj).getPort() == getPort()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return ip + ":" + port;
	}
}
