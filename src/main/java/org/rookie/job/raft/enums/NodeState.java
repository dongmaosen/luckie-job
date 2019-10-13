package org.rookie.job.raft.enums;

/**
 *  
 *
 * Author: 不二
 *
 * Copyright @ 2019
 * 
 */
public enum NodeState {
	//init state --> follower
	FOLLOWER(0), CANDIDATE(1), LEADER(2);
	
	private int state;
	
	NodeState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
