package org.rookie.job.common;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class LuckieShutdownHandler implements SignalHandler {

	@Override
	public void handle(Signal signal) {
		//4.在SignalHandler的handle回调接口中，初始化ShutdownHook线程，并注册到Runtime的ShutdownHook中
		invokeShutdownHook();
		//5.Java进程接收到退出信号以后，Runtime调用exit方法使虚拟机退出，并触发ShutdownHook线程执行
		Runtime.getRuntime().exit(0);
	}

	private void invokeShutdownHook() {
		Thread t = new Thread(new ShutdownHook(), "ShutdownHook-Thread");
		Runtime.getRuntime().addShutdownHook(t);
	}

}
