package org.rookie.job.common.multitask;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class CompletionServiceTest {
	
	@Test
	public void completionTest() {
		//初始化线程池
		ExecutorService exec = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
		//阻塞队列
		BlockingQueue<Future<TaskVO>> queue = new LinkedBlockingDeque<Future<TaskVO>>(10);
		//多任务协调服务，用的是阻塞队列来实现的
		CompletionService<TaskVO> completionService = new ExecutorCompletionService<TaskVO>(exec, queue);
		//开始时间，计时用
		long start = System.currentTimeMillis();
		//提交10次任务
		for (int i = 0; i < 10; i++) {
		    completionService.submit(new Task());
		}
		//返回结果统计用
		long totalTime = 0;
		//提取10次任务结果
		for (int i = 0; i < 10; i++) {
			try {
				Future<TaskVO> future = completionService.take();
				System.out.println(future.get().getTname() + " : " + future.get().getTimemilliseconds());
				totalTime += future.get().getTimemilliseconds();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("total sleep time："+totalTime);
		long end = System.currentTimeMillis();
		//所有任务实际消耗时间
		System.out.println("tasks coust ms：" + (end - start));
	}
}
