package com.bbro.bbcmd.client.command.basic;

import java.util.Date;

import com.bbro.bbcmd.client.command.CommandException;
import com.bbro.bbcmd.client.command.Commandable;
import com.bbro.bbcmd.client.command2ui.ExecutableRegistry;

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
