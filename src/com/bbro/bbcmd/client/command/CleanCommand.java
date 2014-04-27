package com.bbro.bbcmd.client.command;

import com.bbro.bbcmd.client.bridge.ExecutableRegistry;
import com.bbro.bbcmd.client.core.Commandable;

public class CleanCommand implements Commandable {

	public static final String KEY = "clean";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public void doCommand(String args) {
		ExecutableRegistry.getExecutable().clean();
	}

}
