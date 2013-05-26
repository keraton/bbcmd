package com.bbro.bbcmd.client.command;

import com.bbro.bbcmd.client.command.message.MainMessage;
import com.bbro.bbcmd.client.core.Commandable;
import com.google.gwt.core.shared.GWT;

public class NotFoundCommand extends AbstractNotFoundCommand implements Commandable {
	
	private static MainMessage MESSAGE = GWT.create(MainMessage.class);
	
	@Override
	protected String getNotfoundCommandText(String command) {
		return MESSAGE.command_not_found(command);
	}

	@Override
	public String getDescription() {
		return "";
	}
	
	@Override
	public String getUsage() {
		return "";
	}

}
