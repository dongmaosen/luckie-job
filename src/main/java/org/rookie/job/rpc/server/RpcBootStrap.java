package org.rookie.job.rpc.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.rookie.job.cfg.LuckieConfig;
import org.rookie.job.rpc.proto.LuckieProto;

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
//				input = new ObjectInputStream(client.getInputStream());
				output = new ObjectOutputStream(client.getOutputStream());
				
				LuckieProto.Luckie luckie = LuckieProto.Luckie.parseDelimitedFrom(client.getInputStream());
				System.out.println(luckie.getEvent());
				System.out.print(luckie.getDataMap());
				output.writeObject("ok");
				output.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				client.close();
				output.close();
			}
		}
	}

}
