package com.bbro.bbcmd.client.command.exp4j;

import java.util.HashMap;

import com.bbro.bbcmd.client.command.AbstractStack;
import com.bbro.bbcmd.client.command.HelpCommand;
import com.bbro.bbcmd.client.core.CommandException;
import com.bbro.bbcmd.client.core.CommandServerService;
import com.bbro.bbcmd.client.core.Commandable;
import com.bbro.bbcmd.client.core.Stackable;
import com.bbro.bbcmd.client.uibridge.ExecutableRegistry;
import com.google.gwt.core.shared.GWT;

public class Exp4jStack extends AbstractStack implements Stackable, Commandable {
	
	private static Exp4jStack instance = new Exp4jStack();
	
	public static String KEY = "exp4j";
	
	public static Exp4jStack getInstance() {
		return instance;
	}
	
	private HashMap<String, Commandable> mapCommands = new HashMap<String, Commandable>();
	
	private Exp4jStack() {
		
		// Initiate all the Main Command
		mapCommands.put(HelpCommand.KEY, new HelpCommand(mapCommands));
		mapCommands.put(Exp4jSetCommand.KEY, new Exp4jSetCommand());
		Exp4jEvalCommand exp4jEval = new Exp4jEvalCommand();
		exp4jEval.commandServerService = GWT.create(CommandServerService.class);
		mapCommands.put(Exp4jEvalCommand.KEY, exp4jEval);
		mapCommands.put(Exp4jExitCommand.KEY, new Exp4jExitCommand());
	}

	@Override
	public String getStackPath() {
		return "@exp4j";
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getDescription() {
		return "Exp4j cmd interface";
	}

	@Override
	public String getUsage() {
		return "exp4j";
	}

	@Override
	public void doCommand(String... args) throws CommandException {
		ExecutableRegistry.getExecutable().print("Welcome to exp4j cmd interface");
	}

	@Override
	protected Commandable getStackCommand(String command) {
		return mapCommands.get(command);
	}

}
