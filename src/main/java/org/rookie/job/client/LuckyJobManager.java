package org.rookie.job.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.rookie.job.raft.election.NodeInfo;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class LuckyJobManager {
	
	private static final String JOB_GROUP_NAME = "LUCKY_JOB_GROUP";
	
	private static final String TRIGGER_GROUP_NAME = "LUCKY_TRIGGER_GROUP";
	
	private static List<LuckyQuartzJob> taskList = new ArrayList<LuckyQuartzJob>();
	
	private static ExecutorService exec = Executors.newFixedThreadPool(1);
	
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
	/**
	 *   将job加入到任务列表中
	 */
	public static void addJob(LuckyQuartzJob job) {
		taskList.add(job);
	}
	
	/**
	 * 开始进入任务的调度
	 */
	public static void start() {
		exec.submit(new Runnable() {
			@Override
			public void run() {
				//0.获取节点阻塞循环
				List<NodeInfo> nodes = getNodes();
				//1.进入调度，根据当前节点动态分配任务
				
				try {
					if (!getScheduler().isShutdown()) {				
						getScheduler().start();
					}
				} catch (SchedulerException e) {}
			}
		});
	}
	/**
	 * 
	 * @return
	 */
	protected static List<NodeInfo> getNodes() {
		// TODO Auto-generated method stub
		return null;
	}
	private static void addTask(LuckyQuartzJob job) throws SchedulerException{
		JobDetail jobDetail = JobBuilder.newJob(LuckyQuartzJob.class).withIdentity(job.getName(), JOB_GROUP_NAME).build();
		getTriggerBuilder().withIdentity(job.getName() + "-trigger", TRIGGER_GROUP_NAME);
		getTriggerBuilder().startNow();
		getTriggerBuilder().withSchedule(CronScheduleBuilder.cronSchedule(job.getCron()));
		CronTrigger trigger = (CronTrigger) getTriggerBuilder().build();
		getScheduler().scheduleJob(jobDetail, trigger);
	}

	/**
	 * clear 所有的任务
	 * @throws SchedulerException 
	 */
	public static void clearAllTasks() {
		try {
			getScheduler().clear();
		} catch (SchedulerException e) {}
	}
}
