package com.bbro.bbcmd.client.command;

import java.util.Map;

import com.bbro.bbcmd.client.core.Commandable;
import com.bbro.bbcmd.client.core.IllegalOptionCommandException;
import com.bbro.bbcmd.client.uibridge.ExecutableRegistry;

public abstract class AbstractHelpCommand implements Commandable {
	
	public static String KEY = "help";
	
	private Map<String, Commandable> commands;
	
	public AbstractHelpCommand(Map<String, Commandable> commands){
		if (null == commands) {
			throw new IllegalArgumentException("Command cannot be null");
		}
		
		this.commands = commands;
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public void doCommand(String... args) throws IllegalOptionCommandException {
		if (args == null || args.length == 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(getHelpText());
			sb.append("<table>");
			for (String key : commands.keySet()) {
				Commandable cmd = commands.get(key);
				sb.append("<tr><td>");
				sb.append(key);
				sb.append("</td><td>");
				sb.append(":");
				sb.append("</td><td>");
				sb.append(cmd.getDescription());
				sb.append("</td></tr>");
			}
			sb.append("</table>");
			ExecutableRegistry.getExecutable().print(sb.toString());
		}
		else if (args.length == 1){
			String commandText = args[0];
			StringBuilder sb = new StringBuilder();
			if (commands.containsKey(commandText)) {
				Commandable command = commands.get(commandText);
				sb.append(command);
				sb.append(command.getKey());
				sb.append(command.getDescription());
				sb.append("<br/>");
				sb.append(command.getUsage());
			}
			else {
				sb.append("no help topic match");
				sb.append(commandText);
			}
			ExecutableRegistry.getExecutable().print(sb.toString());
		}
		else {
			throw new IllegalOptionCommandException();
		}
	}
	

	protected abstract String getHelpText();

}
