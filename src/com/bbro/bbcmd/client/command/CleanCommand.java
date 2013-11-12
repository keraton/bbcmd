package com.bbro.bbcmd.client.command;

import com.bbro.bbcmd.client.core.Commandable;
import com.bbro.bbcmd.client.uibridge.ExecutableRegistry;

public class CleanCommand implements Commandable {

	public static final String KEY = "clean";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public void doCommand(String... args) {
		ExecutableRegistry.getExecutable().clean();
	}

	@Override
	public String getDescription() {
		return "Clean screen";
	}

	@Override
	public String getUsage() {
		return "clean";
	}

}
