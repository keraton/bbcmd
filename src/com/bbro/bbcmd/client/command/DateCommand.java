package com.bbro.bbcmd.client.command;

import java.util.Date;

import com.bbro.bbcmd.client.bridge.ExecutableRegistry;
import com.bbro.bbcmd.client.core.CommandException;
import com.bbro.bbcmd.client.core.Commandable;

public class DateCommand implements Commandable {
	
	public final static String KEY = "date";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public void doCommand(String args) throws CommandException {
		ExecutableRegistry.getExecutable().print("" + new Date());
	}

}
