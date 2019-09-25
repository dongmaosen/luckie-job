package org.rookie.job.common;
/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class OSService {
	public static String getOSSingalType() {
		return System.getProperties().getProperty("os.name").toLowerCase().startsWith("win") ? "INT" : "USR2";
	}
}
