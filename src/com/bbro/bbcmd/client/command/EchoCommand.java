package com.bbro.bbcmd.client.command;

import com.bbro.bbcmd.client.bridge.ExecutableRegistry;
import com.bbro.bbcmd.client.core.CommandException;
import com.bbro.bbcmd.client.core.Commandable;

public class EchoCommand implements Commandable {
	
	public static final String KEY = "echo";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public void doCommand(String args) throws CommandException {
		ExecutableRegistry.getExecutable().print(args);
	}

}
