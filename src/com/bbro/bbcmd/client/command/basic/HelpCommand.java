package com.bbro.bbcmd.client.command.basic;

import java.util.HashMap;
import java.util.Map;

import com.bbro.bbcmd.client.command.exception.CommandException;
import com.bbro.bbcmd.client.command.exception.IllegalOptionCommandException;
import com.bbro.bbcmd.client.command.share.Commandable;
import com.bbro.bbcmd.client.command2ui.ExecutableRegistry;

public class HelpCommand implements Commandable {
	
	public static final String KEY = "help";
	private Map<String, Commandable> commands = new HashMap<String, Commandable>(); 

	
	public HelpCommand(Map<String, Commandable> commands) {
		this.commands = commands;
	}
	
	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public void doCommand(String args) throws CommandException {
		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		for (String key : commands.keySet()) {
			sb.append("<tr><td>");
			sb.append(key);
			sb.append("</td></tr>");
		}
		sb.append("</table>");
		ExecutableRegistry.getExecutable().print(sb.toString());
	}


}
