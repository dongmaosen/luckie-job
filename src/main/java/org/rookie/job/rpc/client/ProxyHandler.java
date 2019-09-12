package org.rookie.job.rpc.client;

import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

import org.rookie.job.rpc.proto.LuckieJobProtos;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class ProxyHandler implements InvocationHandler {

	private String ip;
    private int port;

    public ProxyHandler(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Socket socket = new Socket(this.ip, this.port);
        try {
//            output.writeObject(proxy.getClass().getInterfaces()[0]);
//            output.writeUTF(method.getName());
//            output.writeObject(method.getParameterTypes());
//            output.writeObject(args);
        	LuckieJobProtos.LuckieJob luckieJob = LuckieJobProtos.LuckieJob.newBuilder().
        			setEventId(1).setArgs("aa").build();
        	ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        	
            luckieJob.writeDelimitedTo(socket.getOutputStream());
            socket.getOutputStream().flush();
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            Object result = input.readObject();
            if(result instanceof Throwable) {
                throw (Throwable) result;
            }
                return result;
        } finally {
            socket.shutdownOutput();
        }
    }


}
