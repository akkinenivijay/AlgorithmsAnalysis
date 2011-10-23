package org.gsu.cs.graph.util;

import java.util.Random;

public class RandomUtil {

	public static void main(String[] args) {
		Random random = new Random();

		int ret = random.nextInt(4);
		System.out.println(ret);
		
		int ret1 = random.nextInt(4);
		System.out.println(ret1);
	}
}
