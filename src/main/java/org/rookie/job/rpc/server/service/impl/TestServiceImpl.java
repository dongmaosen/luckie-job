package org.rookie.job.rpc.server.service.impl;

import org.rookie.job.rpc.server.service.ITestService;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class TestServiceImpl implements ITestService {

	public String echo(String str) {
		return str + "......";
	}

}
