package org.rookie.job.rpc.client;

import java.lang.reflect.Proxy;

/**
 *
 *
 * Author: 不二
 *
 * Copyright @ 2019
 * 
 */
public class RpcConsumer {
	@SuppressWarnings("unchecked")
	public static <T> T getService(Class<T> clazz, String ip, int port) {
		ProxyHandler proxyHandler = new ProxyHandler(ip, port);
		return (T) Proxy.newProxyInstance(RpcConsumer.class.getClassLoader(), new Class<?>[] { clazz }, proxyHandler);
	}
}
