package com.bbro.bbcmd.client.core;

public interface Commandable {
	
	String getKey();
	
	String getDescription();
	
	String getUsage();
	
	void doCommand(String ...args) throws CommandException;

}
