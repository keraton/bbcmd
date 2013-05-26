package com.bbro.bbcmd.client.core;

import java.util.Stack;

import com.bbro.bbcmd.client.uibridge.ExecutableRegistry;

public final class CommandDispatcherImpl implements CommandDispatcher {
		
	private Stackable mainStack;
	
	private Stack<Stackable> stacks = new Stack<Stackable>();
	
	public CommandDispatcherImpl(Stackable mainStack) {
		this.mainStack = mainStack;
	}
	
	public void dispatch(String command, String... args) {
		Commandable realCommand = mainStack.getCommands(command);
		
		if (null != realCommand) {
		
			if (realCommand instanceof Stackable) {
				stacks.push(mainStack);
				mainStack = (Stackable) realCommand;
				
				// add new stack
				ExecutableRegistry.getExecutable().printPath(mainStack.getStackPath());
			}
			
			if (realCommand instanceof ExitCommandable) {	
				mainStack = stacks.pop();
				
				// change new stack
				ExecutableRegistry.getExecutable().printPath(mainStack.getStackPath());
			}
			
			try {
				realCommand.doCommand(args);
			}
			catch (IllegalOptionCommandException e) {
				StringBuilder sb = new StringBuilder();
				sb.append(realCommand.getKey());
				sb.append(" : illegal option");
				sb.append("<br/>");
				sb.append(realCommand.getUsage());
				ExecutableRegistry.getExecutable().print(sb.toString());
			}
			catch(CommandException e) {
				ExecutableRegistry.getExecutable().print("Command error : " + e.getMessage());
			}
		}
	}
}
