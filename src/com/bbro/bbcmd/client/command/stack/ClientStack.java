package com.bbro.bbcmd.client.command.stack;

import java.util.HashMap;

import com.bbro.bbcmd.client.command.basic.CleanCommand;
import com.bbro.bbcmd.client.command.basic.EchoCommand;
import com.bbro.bbcmd.client.command.basic.EmptyCommand;
import com.bbro.bbcmd.client.command.basic.EnvCommand;
import com.bbro.bbcmd.client.command.basic.HelpCommand;
import com.bbro.bbcmd.client.command.exception.CommandException;
import com.bbro.bbcmd.client.command.share.Commandable;
import com.bbro.bbcmd.client.command.share.Stackable;
import com.bbro.bbcmd.client.command.stack.js.EvalStackable;

public final class ClientStack implements Stackable {
	
	private static ClientStack INSTANCE = new ClientStack();
	
	public static final ClientStack getINSTANCE() {
		return INSTANCE;
	}

	private HashMap<String, Commandable> mapCommands = new HashMap<String, Commandable>();
	
	private ClientStack() {
		addCommand(new CleanCommand());
		addCommand(new HelpCommand(mapCommands));
		addCommand(new EnvCommand());
		addCommand(new EmptyCommand());
		addCommand(new EchoCommand());
		addCommand(new EvalStackable());
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

}
