package org.rookie.job.common.multitask;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class Task implements Callable<TaskVO> {

	@Override
	public TaskVO call() throws Exception {
		TaskVO vo = new TaskVO();
		int random = new Random().nextInt(10000);
		vo.setTid(Thread.currentThread().getId());
		vo.setTname(Thread.currentThread().getName());
		vo.setTimemilliseconds(random);
		Thread.sleep(random);
		System.out.println(Thread.currentThread().getName() + " sleep " + random + " ms");
		return vo;
	}

}
