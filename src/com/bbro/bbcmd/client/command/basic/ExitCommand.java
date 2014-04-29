package com.bbro.bbcmd.client.command.basic;

import com.bbro.bbcmd.client.command.share.ExitCommandable;
import com.bbro.bbcmd.client.command2ui.ExecutableRegistry;

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
