package com.bbro.bbcmd.client.command;

import com.bbro.bbcmd.client.core.Commandable;
import com.bbro.bbcmd.client.uibridge.ExecutableRegistry;

public abstract class AbstractNotFoundCommand implements Commandable {

	public final static String KEY = "notfound";
	
	private String notCmd;
	
	public void setNotFoundCmd(String cmd){
		this.notCmd = cmd;
	}
	
	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public void doCommand(String... args) {
		ExecutableRegistry.getExecutable().print(getNotfoundCommandText(notCmd));
		
	}
	
	protected abstract String getNotfoundCommandText(String command); 


}
