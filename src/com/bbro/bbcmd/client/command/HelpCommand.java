package com.bbro.bbcmd.client.command;

import java.util.Map;

import com.bbro.bbcmd.client.command.message.MainMessage;
import com.bbro.bbcmd.client.core.Commandable;
import com.google.gwt.core.shared.GWT;

public class HelpCommand extends AbstractHelpCommand implements Commandable {
	
	private static MainMessage MESSAGE = GWT.create(MainMessage.class);

	public HelpCommand(Map<String, Commandable> commands) {
		super(commands);
	}

	@Override
	protected String getHelpText() {
		return MESSAGE.command_help_header();
	}

	@Override
	public String getDescription() {
		return MESSAGE.command_help_description();
	}

	@Override
	public String getUsage() {
		return "help command";
	}
	

}
