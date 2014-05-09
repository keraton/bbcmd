package com.keraton.bbcmd.server.session;

import java.util.HashMap;
import java.util.Map;

import org.keraton.mathj.context.Context;

public class MathJSession {

	private Map<String, Context> map = new HashMap<>();
	
	private static MathJSession instance = new MathJSession();
	
	private MathJSession() {
	}
	
	public static MathJSession getInstance(){
		return instance;
	}
	
	public void put(String key, Context context) {
		map.put(key, context);
	}
	
	public Context get(String key) {
		return map.get(key);
	}
	
	public void remove(String key){
		map.remove(key);
	}
	
}
