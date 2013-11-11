package com.bbro.bbcmd.client.command.exp4j;

import com.bbro.bbcmd.client.core.CommandException;
import com.bbro.bbcmd.client.core.ExitCommandable;
import com.bbro.bbcmd.client.uibridge.ExecutableRegistry;

public class Exp4jExitCommand implements ExitCommandable {
	
	protected static String KEY = "exit";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getDescription() {
		return "exit exp4j";
	}

	@Override
	public String getUsage() {
		return "exit";
	}

	@Override
	public void doCommand(String... args) throws CommandException {
		Exp4jContext.getInstance().clear();
		ExecutableRegistry.getExecutable().print("Ex4j context clear");
		ExecutableRegistry.getExecutable().print("Exit ex4j successfull");
	}

}
