package com.bbro.bbcmd.client.command.dispatcher;

import java.util.Stack;

import com.bbro.bbcmd.client.command.CommandException;
import com.bbro.bbcmd.client.command.Commandable;
import com.bbro.bbcmd.client.command.ExitCommandable;
import com.bbro.bbcmd.client.command.IllegalOptionCommandException;
import com.bbro.bbcmd.client.command.Stackable;
import com.bbro.bbcmd.client.command.basic.NotFoundCommand;
import com.bbro.bbcmd.client.command2ui.ExecutableRegistry;
import com.bbro.bbcmd.client.common.utils.StringUtils;
import com.google.gwt.user.client.History;

public final class CommandDispatcherImpl implements CommandDispatcher {
		
	private Stackable mainStack;
	
	private Stack<Stackable> stacks = new Stack<Stackable>();
	private NotFoundCommand notFound = new NotFoundCommand();
	
	
	public CommandDispatcherImpl(Stackable mainStack) {
		this.mainStack = mainStack;
	}
	
	public void dispatch(String command, String args) {
		
		Commandable realCommand = null;
		
		if (mainStack.isCallDirect()) {
			realCommand = mainStack.getCommands(StringUtils.regroupCommandAndArgs(command, args));
		}
		else {
			realCommand = mainStack.getCommands(command);
		}
			
		if (realCommand == null) {
			notFound.setNotFoundCmd(command);
			realCommand = notFound;
		} 
		
		dispatch(realCommand, args);
		
	}

	@Override
	public void dispatch(Commandable command, String args) {
			
		if (command instanceof Stackable) {
			stacks.push(mainStack);
			mainStack = (Stackable) command;
			
			// TODO this is a SRP violation
			History.newItem(command.getKey(), false);
		}
		
		if (command instanceof ExitCommandable) {
			if (stacks.size() > 0 ) {
				mainStack = stacks.pop();
				History.newItem(mainStack.getKey());
			}
		}
		
		ExecutableRegistry.getExecutable().printPath(mainStack.getKey());
			
		try {
			command.doCommand(args);
		}
		catch (IllegalOptionCommandException e) {
			StringBuilder sb = new StringBuilder();
			sb.append(command.getKey());
			sb.append(" : illegal option");
			sb.append("<br/>");
			ExecutableRegistry.getExecutable().printErr(sb.toString());
		}
		catch(CommandException e) {
			ExecutableRegistry.getExecutable().printErr("Command error : " + e.getMessage());
		}
	}

	@Override
	public void findCommand(String text, int counter) {
		mainStack.getPossibleCommand(text, counter);
	}
}
