package com.bbro.bbcmd.client.command.exp4j;

import java.util.HashMap;
import java.util.Set;

public class Exp4jContext {

	private static Exp4jContext instance = new Exp4jContext();
	
	public static Exp4jContext getInstance() {
		return instance;
	}
	
	private HashMap<String, String> map = new HashMap<String, String>();
	
	public String get(String key){
		return map.get(key);
	}
	
	public void put(String key, String value){
		map.put(key, value);
	}
	
	public Set<String> keySet() {
		return map.keySet();
	}
	
	private Exp4jContext () {
	}

	public void clear() {
		map.clear();
	}
	
}
