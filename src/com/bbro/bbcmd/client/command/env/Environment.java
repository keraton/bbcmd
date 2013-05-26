package com.bbro.bbcmd.client.command.env;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Environment {

	private static Environment instance = new Environment();

	public static Environment getInstance(){
		return instance;
	}
	
	private Environment(){
		envMaps = new HashMap<String, String>();
		envMaps.put("USER_AGENT", getUserAgent());
	}
	
	private Map<String, String> envMaps;
	
	public void putEnv(String key, String value){
		envMaps.put(key, value);
	}
	
	public String getEnv(String key){
		return envMaps.get(key);
	}
	
	public Set<String> getKeyEnv(){
		return envMaps.keySet();
	}
	
	public static native String getUserAgent() /*-{
	return navigator.userAgent.toLowerCase();
	}-*/;
}
