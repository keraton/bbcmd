package com.bbro.bbcmd.client.command;

import com.bbro.bbcmd.client.core.Commandable;
import com.bbro.bbcmd.client.uibridge.ExecutableRegistry;

public class EmptyCommand implements Commandable {

	public static final String KEY = "";

	@Override
	public String getKey() {
		return "";
	}

	@Override
	public void doCommand(String... args) {
		ExecutableRegistry.getExecutable().print("");
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public String getUsage() {
		return "";
	}

}
