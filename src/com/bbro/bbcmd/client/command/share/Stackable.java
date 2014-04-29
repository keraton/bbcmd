package com.bbro.bbcmd.client.command.share;




public interface Stackable extends Commandable {

	Commandable getCommands(String key);
	
	boolean isCallDirect();
}
