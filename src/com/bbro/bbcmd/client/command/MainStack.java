package com.bbro.bbcmd.client.command;

import java.util.HashMap;

import com.bbro.bbcmd.client.core.Commandable;
import com.bbro.bbcmd.client.core.Stackable;

public final class MainStack extends AbstractStack implements Stackable {
	
	private HashMap<String, Commandable> mapCommands = new HashMap<String, Commandable>();
	
	public MainStack() {
		
		// Initiate all the Main Command
		mapCommands.put(HelpCommand.KEY, new HelpCommand(mapCommands));
		
	}
	
	public void addCommand(Commandable command){
		mapCommands.put(command.getKey(), command);
	}
	
	@Override
	protected Commandable getStackCommand(String command) {
		return mapCommands.get(command);
	}

	@Override
	public String getStackPath() {
		return "";
	}


}
