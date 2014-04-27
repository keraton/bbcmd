package com.bbro.bbcmd.client.command.sample;

import java.util.HashMap;
import java.util.Map;

import com.bbro.bbcmd.client.bridge.ExecutableRegistry;
import com.bbro.bbcmd.client.command.ExitCommand;
import com.bbro.bbcmd.client.core.Commandable;
import com.bbro.bbcmd.client.core.Stackable;

public class SampleStack implements Stackable {
	
	public static String KEY = "stack";
	
	private final Map<String, Commandable> samplesCommands = new HashMap<String, Commandable>();
	
	public SampleStack() {
		samplesCommands.put("exit", new ExitCommand());
	}

	@Override
	public String getKey() {
		return KEY;
	}
	
	@Override
	public void doCommand(String args) {
		ExecutableRegistry.getExecutable()
					.print("Example of stack use (stack is like a private environment that contains private commands)");
	}

	@Override
	public Commandable getCommands(String key) {
		return samplesCommands.get(key);
	}

	@Override
	public boolean isCallDirect() {
		return true;
	}

	@Override
	public String getPossibleCommand(String key, int counter) {
		return "";
	}

}
