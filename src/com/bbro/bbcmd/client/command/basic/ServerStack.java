package com.bbro.bbcmd.client.command.basic;

import com.bbro.bbcmd.client.command.Commandable;
import com.bbro.bbcmd.client.command.Stackable;
import com.bbro.bbcmd.client.command2ui.ExecutableRegistry;
import com.google.gwt.event.shared.SimpleEventBus;

public class ServerStack implements Stackable {
	
	private final String KEY;
	private final String description; 
	private final Stackable stackable;
	private final EmptyCommand emptyCommand = new EmptyCommand();
	private final SimpleEventBus bus;
	
	public ServerStack(String key, String description, 
			Stackable stackable, SimpleEventBus bus) {
		this.KEY = key;
		this.description = description;
		this.stackable = stackable;
		this.bus = bus;
	}

	@Override
	public String getKey() {
		return KEY;
	}
	
	@Override
	public void doCommand(String args) {
		ExecutableRegistry.getExecutable()
					.print(description);
	}

	@Override
	public Commandable getCommands(String args) {
		

		
		return emptyCommand;
		
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
