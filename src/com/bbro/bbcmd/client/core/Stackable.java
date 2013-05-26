package com.bbro.bbcmd.client.core;


public interface Stackable {

	Commandable getCommands(String command);
	
	String getStackPath();
	
}
