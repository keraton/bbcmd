package com.bbro.bbcmd.client.utils;

public class StringUtils {

	public static String regroupArgs(String...args) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
		}
		return sb.toString();
	}
}
