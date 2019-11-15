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
	 *   将job加入到任务列表中(所有的任务)
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
				arrangeTasks();
			}
		});
	}
	/**
	 * 节点变更时重新
	 */
	public static void reArrangeTask() {
		exec.submit(new Runnable() {
			@Override
			public void run() {
				clearAllTasks();
				arrangeTasks();
			}
		});
	}
	
	/**
	 * 平均分配任务
	 */
	private static void arrangeTasks() {
		//0.获取节点阻塞循环
		List<NodeInfo> nodes = ElectionProcess.getClusterNodes();
		//1.进入调度，根据当前节点动态分配任务
		int index = -1;
		for (int i = 0; i < nodes.size(); i++) {
			if (ElectionProcess.localnode.equals(nodes.get(i))) {
				index = i;
				break;
			}
		}
		try {
			for (int j = 0; j < taskList.size(); j++) {
				if (j % nodes.size() == index) {
					try {
						addTask(taskList.get(j));
					} catch (SchedulerException e) {
						e.printStackTrace();
					}
				}
			}
			if (!getScheduler().isShutdown()) {				
				getScheduler().start();
			}
		} catch (SchedulerException e) {}
	}

	private static void addTask(LuckyQuartzJob job) throws SchedulerException{
		JobDetail jobDetail = JobBuilder.newJob(job.getClass()).withIdentity(job.getName(), JOB_GROUP_NAME).build();
		CronTrigger trigger = (CronTrigger)getTriggerBuilder().withIdentity(job.getName() + "-trigger", TRIGGER_GROUP_NAME)
		                   .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron()))
		                   .build();
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
