package com.bbro.bbcmd.client.core;

public interface Commandable {
	
	String getKey();
	
	void doCommand(String arg) throws CommandException;

}
