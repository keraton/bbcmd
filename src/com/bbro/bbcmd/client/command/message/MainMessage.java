package com.bbro.bbcmd.client.command.message;

import com.google.gwt.i18n.client.Messages;

public interface MainMessage extends Messages {
	
	@Key("command.not.found")
	String command_not_found(String command);
	
	@Key("command.help.header")
	String command_help_header();
	
	@Key("command.help.description")
	String command_help_description();

}
