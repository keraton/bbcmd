package com.bbro.bbcmd.server.core;

import java.util.HashMap;
import java.util.Map;

public class MapCommand {

	public static final MapCommand INSTANCE = new MapCommand(); 

	private Map<String, CommandableServer> map = new HashMap<String, CommandableServer>();
	
	private MapCommand() {
	}
	
	Map<String, CommandableServer> getMap() {
		return map;
	}
	
	public void putCommand(String path, CommandableServer command) {
		if (path == null) {
			throw new IllegalArgumentException("Path is missing");
		}
		
		if (command == null) {
			throw new IllegalArgumentException("Command is missing");
		}
		
		map.put(path, command);
	}

}
