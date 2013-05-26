package com.bbro.bbcmd.client.command.sample;

import java.util.HashMap;
import java.util.Map;

import com.bbro.bbcmd.client.command.AbstractStack;
import com.bbro.bbcmd.client.core.Commandable;
import com.bbro.bbcmd.client.core.Stackable;
import com.bbro.bbcmd.client.uibridge.ExecutableRegistry;

public class SampleStack extends AbstractStack implements Commandable, Stackable {
	
	public static String KEY = "sample";
	
	private Map<String, Commandable> samplesCommands = new HashMap<String, Commandable>();
	
	public SampleStack() {
		// Init sample
		samplesCommands.put(ExitSampleCommand.KEY, new ExitSampleCommand());
		samplesCommands.put(HelloServerCommand.KEY, new HelloServerCommand());
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getDescription() {
		return "Collections of command sample";
	}

	@Override
	public void doCommand(String... args) {
		ExecutableRegistry.getExecutable().print("Collections of command sample");
	}

	@Override
	protected Commandable getStackCommand(String command) {
		return samplesCommands.get(command);
	}

	@Override
	public String getStackPath() {
		return "@sample";
	}

	@Override
	public String getUsage() {
		return "sample-$";
	}

}
