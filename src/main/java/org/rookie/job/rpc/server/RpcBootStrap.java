package org.rookie.job.rpc.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.rookie.job.cfg.LuckieConfig;
import org.rookie.job.rpc.server.service.impl.TestServiceImpl;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class RpcBootStrap {
	
	/**
	 * rpc服务端发布可提供服务的接口供调用
	 * @throws Exception
	 */
	public static void publish() throws Exception {
		//网络编程部分，接收字节流
		ServerSocket server = new ServerSocket(LuckieConfig.LISTEN_PORT);
		Socket client = null;
		while (true) {
			client = server.accept();
			ObjectInputStream input = null;
			ObjectOutputStream output = null;
			try {
				//序列化部分，定义一个高效的序列化协议
				input = new ObjectInputStream(client.getInputStream());
				output = new ObjectOutputStream(client.getOutputStream());
				Class<?> serviceClass = (Class<?>) input.readObject();
				//协议解析部分，根据客户端的请求，自定义
				Object obj = findService(serviceClass);
				if (obj == null) {
					output.writeObject("no service available");
				} else {
					try {
						//读取方法名
						String methodName = input.readUTF();
						//参数类型
						Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
	                    //参数值
						Object[] arguments = (Object[]) input.readObject();
						//获得要调用的方法
	                    Method method = obj.getClass().getMethod(methodName, parameterTypes);
	                    //调用方法返回结果
	                    Object result = method.invoke(obj, arguments);  
	                    output.writeObject(result); 
					} catch (Throwable t) {
						t.printStackTrace();
						output.writeObject(t);
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				client.close();
				input.close();
				output.close();
			}
		}
	}

	private static Object findService(Class<?> serviceClass) {
		List<Object> serviceList = new ArrayList<Object>();
		serviceList.add(new TestServiceImpl());
		for (Object object : serviceList) {
			boolean isFather = serviceClass.isAssignableFrom(object.getClass());
			if (isFather) {
				return object;
			}
		}
		return null;
	}
}
