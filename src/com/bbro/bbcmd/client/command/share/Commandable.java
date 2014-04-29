package com.bbro.bbcmd.client.command.share;

import com.bbro.bbcmd.client.command.exception.CommandException;

public interface Commandable {
	
	String getKey();
	
	void doCommand(String arg) throws CommandException;

}
