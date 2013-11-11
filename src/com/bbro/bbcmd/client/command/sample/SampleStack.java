package com.bbro.bbcmd.client.command.sample;

import java.util.HashMap;
import java.util.Map;

import com.bbro.bbcmd.client.command.AbstractStack;
import com.bbro.bbcmd.client.command.HelpCommand;
import com.bbro.bbcmd.client.command.sample_server.HelloServerCommand;
import com.bbro.bbcmd.client.core.Commandable;
import com.bbro.bbcmd.client.core.Stackable;
import com.bbro.bbcmd.client.uibridge.ExecutableRegistry;

public class SampleStack extends AbstractStack implements Commandable, Stackable {
	
	public static String KEY = "stack";
	
	private Map<String, Commandable> samplesCommands = new HashMap<String, Commandable>();
	
	public SampleStack() {
		// Init sample
		samplesCommands.put(ExitSampleCommand.KEY, new ExitSampleCommand());
		samplesCommands.put(HelpCommand.KEY, new HelpCommand(samplesCommands));
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getDescription() {
		return "Example of stack use (stack is like a private environment that contains private commands)";
	}

	@Override
	public void doCommand(String... args) {
		ExecutableRegistry.getExecutable().print(getDescription());
	}

	@Override
	protected Commandable getStackCommand(String command) {
		return samplesCommands.get(command);
	}

	@Override
	public String getStackPath() {
		return "@stack";
	}

	@Override
	public String getUsage() {
		return "sample-$";
	}

}
