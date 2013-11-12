package com.bbro.bbcmd.client.command;

import java.util.HashMap;

import com.bbro.bbcmd.client.command.date.DateCommand;
import com.bbro.bbcmd.client.command.env.EnvCommand;
import com.bbro.bbcmd.client.command.exp4j.Exp4jStack;
import com.bbro.bbcmd.client.command.sample.SampleStack;
import com.bbro.bbcmd.client.command.sample_server.HelloServerCommand;
import com.bbro.bbcmd.client.core.Commandable;
import com.bbro.bbcmd.client.core.Stackable;

public final class MainStack extends AbstractStack implements Stackable {
	
	private static MainStack INSTANCE = new MainStack();
	
	/**
	 * @return the iNSTANCE
	 */
	public static final MainStack getINSTANCE() {
		return INSTANCE;
	}

	private HashMap<String, Commandable> mapCommands = new HashMap<String, Commandable>();
	
	private MainStack() {
		
		// Initiate all the Main Command
		mapCommands.put(HelpCommand.KEY, new HelpCommand(mapCommands));
		addCommand(new SampleStack());
		addCommand(new EnvCommand());
		addCommand(new HelloServerCommand());
		addCommand(new DateCommand());
		addCommand(Exp4jStack.getInstance());
		addCommand(new CleanCommand());
	}
	
	/**
	 * @return the mapCommands
	 */
	public final HashMap<String, Commandable> getMapCommands() {
		return mapCommands;
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
