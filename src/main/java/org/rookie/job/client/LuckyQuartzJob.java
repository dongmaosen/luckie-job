package org.rookie.job.client;

import org.quartz.Job;

/**
 *
 *
 * Author: 不二   
 * 
 * Copyright @ 2018
 * 
 */
public abstract class LuckyQuartzJob implements Job{
	
	/**
	 * cron表达式
	 */
	private String cron;
	
	/**
	 * 任务名称
	 */
	private String name;

	
	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
