package com.bbro.bbcmd.client.command;

import java.util.HashMap;
import java.util.Set;

import com.bbro.bbcmd.client.command.env.EnvCommand;
import com.bbro.bbcmd.client.core.CommandException;
import com.bbro.bbcmd.client.core.Commandable;
import com.bbro.bbcmd.client.core.Stackable;

public final class ClientStack implements Stackable {
	
	private static ClientStack INSTANCE = new ClientStack();
	
	public static final ClientStack getINSTANCE() {
		return INSTANCE;
	}

	private HashMap<String, Commandable> mapCommands = new HashMap<String, Commandable>();
	
	private ClientStack() {
		mapCommands.put(CleanCommand.KEY, new CleanCommand());
		mapCommands.put(EnvCommand.KEY, new EnvCommand());
		mapCommands.put(EmptyCommand.KEY, new EmptyCommand());
		mapCommands.put(HelpCommand.KEY, new HelpCommand(mapCommands));
		mapCommands.put(EchoCommand.KEY, new EchoCommand());
	}

	public void addCommand(Commandable command){
		mapCommands.put(command.getKey(), command);
	}
	
	@Override
	public Commandable getCommands(String key) {
		return mapCommands.get(key);
	}

	@Override
	public String getKey() {
		return "";
	}

	@Override
	public void doCommand(String args) throws CommandException {
		throw new CommandException("Main command should not be called");
	}

	@Override
	public boolean isCallDirect() {
		return false;
	}

	@Override
	public String getPossibleCommand(String key, int counter) {
		Set<String> keys = mapCommands.keySet();
		int innerCounter = 0;
		for (String commandKey : keys) {
			if (commandKey.startsWith(key)) {
				if (counter ==innerCounter ) {
					return commandKey;
				}
				innerCounter ++;
			}
		}
		return "";
	}


}
