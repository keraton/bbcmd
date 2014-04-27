package com.bbro.bbcmd.client.command.basic;

import com.bbro.bbcmd.client.command.Commandable;
import com.bbro.bbcmd.client.command2ui.ExecutableRegistry;
import com.bbro.bbcmd.client.common.message.MainMessage;
import com.google.gwt.core.shared.GWT;

public class NotFoundCommand implements Commandable {
	
	public final static String KEY = "notfound";
	
	private static MainMessage MESSAGE = GWT.create(MainMessage.class);
	private String notCmd;
	
	public void setNotFoundCmd(String cmd){
		this.notCmd = cmd;
	}
	
	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public void doCommand(String args) {
		ExecutableRegistry.getExecutable().printErr(getNotfoundCommandText(notCmd));
		
	}

	protected String getNotfoundCommandText(String command) {
		return MESSAGE.command_not_found(command);
	}

}
