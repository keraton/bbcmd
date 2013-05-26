package com.bbro.bbcmd.client.command;

import com.bbro.bbcmd.client.core.Commandable;
import com.bbro.bbcmd.client.core.Stackable;

public abstract class AbstractStack implements Stackable {

	private NotFoundCommand notFound = new NotFoundCommand();
	
	private EmptyCommand empty = new EmptyCommand();
	
	@Override
	public Commandable getCommands(String command) {
		
		Commandable cmd = null;
		if ("".equals(command)) {
			cmd = empty;
		}
		else if ((cmd = getStackCommand(command)) == null) {
			notFound.setNotFoundCmd(command);
			cmd = notFound;
		}
		
		return cmd;
	}
	
	protected abstract Commandable getStackCommand(String command);

}
