package com.bbro.bbcmd.client.command.basic;

import com.bbro.bbcmd.client.command.exception.CommandException;
import com.bbro.bbcmd.client.command.share.Commandable;
import com.bbro.bbcmd.client.command2ui.ExecutableRegistry;

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
