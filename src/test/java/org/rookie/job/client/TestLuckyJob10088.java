package org.rookie.job.client;

import org.junit.Test;
import org.rookie.job.BootStrap;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class TestLuckyJob10088 {
	@Test
	public void testLuckyJob() throws Exception {
		BootStrap.start(null, 10088);
		TestTaskDemo1 demo1 = new TestTaskDemo1();
		demo1.setCron("*/7 * * * * ? *");
		demo1.setName("demo1");
		TestTaskDemo2 demo2 = new TestTaskDemo2();
		demo2.setCron("*/15 * * * * ? *");
		demo2.setName("demo2");
		TestTaskDemo3 demo3 = new TestTaskDemo3();
		demo3.setCron("*/11 * * * * ? *");
		demo3.setName("demo3");
		LuckyJobManager.addJob(demo1);
		LuckyJobManager.addJob(demo2);
		LuckyJobManager.addJob(demo3);
		LuckyJobManager.start();
		Thread.sleep(Integer.MAX_VALUE);
	}
}
