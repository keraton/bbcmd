package com.bbro.bbcmd.client.command.basic;

import com.bbro.bbcmd.client.command.share.Commandable;
import com.bbro.bbcmd.client.command2ui.ExecutableRegistry;

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
