package com.keraton.bbcmd.client.common.utils;

public final class CommandDTO {
	
	public enum Source{CMD, JS, SERVER}

	private final String commandArgs;
	private final String command;
	private final String args;
	private final Source source;
	
	public CommandDTO(String command, String args, 
			String commandArgs, Source source) {
		super();
		this.command = command;
		this.args = args;
		this.commandArgs = commandArgs;
		this.source = source;
	}
	
	public final String getCommand() {
		return command;
	}
	public final String getArgs() {
		return args;
	}
	public final String getCommandArgs(){
		return commandArgs;
	}

	public final Source getSource() {
		return source;
	}
	
}
