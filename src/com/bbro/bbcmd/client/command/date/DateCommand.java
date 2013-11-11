package com.bbro.bbcmd.client.command.date;

import java.util.Date;

import com.bbro.bbcmd.client.core.CommandException;
import com.bbro.bbcmd.client.core.Commandable;
import com.bbro.bbcmd.client.uibridge.ExecutableRegistry;
import com.google.gwt.user.client.Window;

public class DateCommand implements Commandable {
	
	private String KEY = "date";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getDescription() {
		return "Show date";
	}

	@Override
	public String getUsage() {
		return "date";
	}

	@Override
	public void doCommand(String... args) throws CommandException {
		Date date = new Date();
		ExecutableRegistry.getExecutable().print("" + date);
	}

}
