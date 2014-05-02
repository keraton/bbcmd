package com.bbro.bbcmd.client.command.basic;

import java.util.HashMap;
import java.util.Map;

import com.bbro.bbcmd.client.command.exception.CommandException;
import com.bbro.bbcmd.client.command.share.Commandable;
import com.bbro.bbcmd.client.command.share.Descriptable;
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
		for (Commandable command : commands.values()) {
			sb.append("<tr>");
			appendTD(sb, command.getKey());
			appendTD(sb, command instanceof Descriptable ? "<span class=\'desc\'> " + 
									((Descriptable)command).getDescription() + "</span>" 
									: "");
			sb.append("</tr>");
		}
		sb.append("</table>");
		ExecutableRegistry.getExecutable().print(sb.toString());
	}

	private void appendTD(StringBuilder sb, String key) {
		sb.append("<td>");
		sb.append(key);
		sb.append("</td>");
	}


}
