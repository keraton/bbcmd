package com.bbro.bbcmd.client.utils;

public class StringUtils {

	public static String regroupArgs(String...args) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
		}
		return sb.toString();
	}
	
	public static String reconstructArgs(String...args) {
		String result = "";
		for (String string : args) {
			result += string + " ";
		}
		return result;
	}
	
	public static String regroupCommandAndArgs(String cmd, String args){
		return (cmd + (args == null || args.isEmpty() ? "" : " " + args)).trim();
	}
}
