package org.rookie.job.common.multitask;

/**
 *
 *
 * Author: 不二
 *
 * Copyright @ 2019
 * 
 */
public class TaskVO {
	long tid;
	String tname;
	int timemilliseconds;

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public int getTimemilliseconds() {
		return timemilliseconds;
	}

	public void setTimemilliseconds(int timemilliseconds) {
		this.timemilliseconds = timemilliseconds;
	}

}
