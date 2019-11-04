package org.rookie.job.common;
/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.rookie.job.raft.election.NodeInfo;

public class SetTest {
	@Test
	public void testRemove() {
		NodeInfo n1 = new NodeInfo();
		n1.setIp("1");
		n1.setPort(1);
		
		NodeInfo n2 = new NodeInfo();
		n2.setIp("2");
		n2.setPort(1);
		
		Set<NodeInfo> set = new HashSet<NodeInfo>();
		set.add(n1);
		set.add(n2);
		
		System.out.println(set.size());
		
		set.remove(n1);
		System.out.println(set.size());
		
		NodeInfo removedNode = new NodeInfo();
		removedNode.setIp("2");
		removedNode.setPort(1);
		set.remove(removedNode);
		
		System.out.println(set.size());
	}
}
