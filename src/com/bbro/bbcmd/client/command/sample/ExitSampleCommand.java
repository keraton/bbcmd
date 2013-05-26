package com.bbro.bbcmd.client.command.sample;

import com.bbro.bbcmd.client.core.ExitCommandable;
import com.bbro.bbcmd.client.uibridge.ExecutableRegistry;

public class ExitSampleCommand implements ExitCommandable {

	public static final String KEY = "exit"; 
	
	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getDescription() {
		return "Exit sample stack";
	}

	@Override
	public void doCommand(String... args) {
		ExecutableRegistry.getExecutable().print("Exit sample");
	}
	
	@Override
	public String getUsage() {
		return "";
	}

}
