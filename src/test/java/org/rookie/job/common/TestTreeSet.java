package org.rookie.job.common;

import java.util.Set;
import java.util.TreeSet;

import org.rookie.job.raft.election.NodeInfo;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class TestTreeSet {
	public static void main(String[] args) {
		Set<NodeInfo> voteSet = new TreeSet<NodeInfo>();
		NodeInfo ni = new NodeInfo();
		ni.setIp("1");
		ni.setPort(1);
		voteSet.add(ni);
	}
}
