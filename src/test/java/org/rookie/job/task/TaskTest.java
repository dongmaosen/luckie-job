package org.rookie.job.task;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.rookie.job.client.LuckyQuartzJob;
import org.rookie.job.client.TestTaskDemo1;
import org.rookie.job.client.TestTaskDemo2;
import org.rookie.job.client.TestTaskDemo3;

/**
 *
 *
 * Author: 不二
 *
 * Copyright @ 2019
 * 
 */
public class TaskTest {
	private static final String JOB_GROUP_NAME = "LUCKY_JOB_GROUP";
	private static final String TRIGGER_GROUP_NAME = "LUCKY_TRIGGER_GROUP";

	public static void main(String[] args) throws Exception {
		TestTaskDemo1 demo1 = new TestTaskDemo1();
		demo1.setCron("*/7 * * * * ? *");
		demo1.setName("demo1");
		addTask(demo1);
		TestTaskDemo2 demo2 = new TestTaskDemo2();
		demo2.setCron("*/15 * * * * ? *");
		demo2.setName("demo2");
		addTask(demo2);
		TestTaskDemo3 demo3 = new TestTaskDemo3();
		demo3.setCron("*/11 * * * * ? *");
		demo3.setName("demo3");
		addTask(demo3);
		if (!getScheduler().isShutdown()) {				
			getScheduler().start();
		}
	}

	private static Scheduler scheduler = null;

	private static Scheduler getScheduler() {
		try {
			if (scheduler == null) {
				scheduler = new StdSchedulerFactory().getScheduler();
			}
			return scheduler;
		} catch (SchedulerException e) {
			return scheduler;
		}
	}

	private static TriggerBuilder<Trigger> triggerBuilder = null;

	private static TriggerBuilder<Trigger> getTriggerBuilder() {
		if (triggerBuilder == null) {
			triggerBuilder = TriggerBuilder.newTrigger();
		}
		return triggerBuilder;
	}

	private static void addTask(LuckyQuartzJob job) throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(job.getClass()).withIdentity(job.getName(), JOB_GROUP_NAME).build();
		CronTrigger trigger = (CronTrigger)getTriggerBuilder().withIdentity(job.getName() + "-trigger", TRIGGER_GROUP_NAME)
															  .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron())).build();
		getScheduler().scheduleJob(jobDetail, trigger);
	}
}
