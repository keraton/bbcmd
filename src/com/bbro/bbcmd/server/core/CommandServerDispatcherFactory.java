package com.bbro.bbcmd.server.core;

public final class CommandServerDispatcherFactory {
	
	private static CommandServerDispatcherFactory instance = new CommandServerDispatcherFactory();
	
	public static CommandServerDispatcherFactory getInstance(){
		return instance;
	}
	
	private CommandServerDispatcher commandServerDispatcher;
	
	private CommandServerDispatcherFactory() {
	}
	
	public void init(CommandServerDispatcher commandDispatcher) {
		if (null != commandServerDispatcher) {
			throw new IllegalStateException("Command server dispatcher is already initalized"); 
		}
		this.commandServerDispatcher = commandDispatcher;
	}
	
	public CommandServerDispatcher getCommandable() {
		return commandServerDispatcher;
	}

}
