package com.jkapp.utils;

import java.util.Random;

public class RandomUtils {

	public static String generateDigits() {

		StringBuilder sb = new StringBuilder();
		sb.append(System.currentTimeMillis() / 10000000);
		sb.append(System.nanoTime() / 1000);
		Random random = new Random();
		sb.append(random.nextInt(10));
		sb.append(random.nextInt(10));
		
		return sb.toString();
	}
}
