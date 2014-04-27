package com.bbro.bbcmd.client.command;

public interface Commandable {
	
	String getKey();
	
	void doCommand(String arg) throws CommandException;

}
