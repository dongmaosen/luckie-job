package org.rookie.job.client;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class TestTaskDemo2 extends LuckyQuartzJob{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		System.out.println("TestTaskDemo2 running at " + new Date());
	}

}
