package org.rookie.job.rpc.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.rookie.job.rpc.Constants;
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
		//
		ServerSocket server = new ServerSocket(Constants.SERVER_PORT);
		Socket client = null;
		while (true) {
			client = server.accept();
			ObjectInputStream input = null;
			ObjectOutputStream output = null;
			try {
				input = new ObjectInputStream(client.getInputStream());
				output = new ObjectOutputStream(client.getOutputStream());
				Class serviceClass = (Class) input.readObject();
				Object obj = findService(serviceClass);
				if (obj == null) {
					output.writeObject("no service available");
				} else {
					try {
						String methodName = input.readUTF();
						Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
	                    Object[] arguments = (Object[]) input.readObject();
	                    Method method = obj.getClass().getMethod(methodName, parameterTypes);  
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

	private static Object findService(Class serviceClass) {
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
