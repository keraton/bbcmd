package com.bbro.bbcmd.client.core;



public interface Stackable extends Commandable {

	Commandable getCommands(String key);
	
	String getPossibleCommand(String key, int counter);
	
	boolean isCallDirect();
}
