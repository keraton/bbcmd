package com.bbro.bbcmd.client.command;

import com.bbro.bbcmd.client.bridge.ExecutableRegistry;
import com.bbro.bbcmd.client.core.ExitCommandable;

public class ExitCommand implements ExitCommandable {

	public static final String KEY = "exit"; 
	
	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public void doCommand(String args) {
		ExecutableRegistry.getExecutable().print("Exit");
	}

}
