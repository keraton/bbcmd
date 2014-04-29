package com.bbro.bbcmd.client.command;

import java.util.List;
import java.util.Stack;

import com.bbro.bbcmd.client.command.basic.NotFoundCommand;
import com.bbro.bbcmd.client.command.exception.CommandException;
import com.bbro.bbcmd.client.command.exception.IllegalOptionCommandException;
import com.bbro.bbcmd.client.command.share.Commandable;
import com.bbro.bbcmd.client.command.share.ExitCommandable;
import com.bbro.bbcmd.client.command.share.Stackable;
import com.bbro.bbcmd.client.command.stack.ClientStack;
import com.bbro.bbcmd.client.command.stack.AbstractServerStack;
import com.bbro.bbcmd.client.command2ui.ExecutableRegistry;
import com.bbro.bbcmd.client.common.utils.StringUtils;
import com.bbro.bbcmd.client.init.InitializerFactory;

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
		}
		
		if (command instanceof ExitCommandable) {
			if (stacks.size() > 0 ) {
				mainStack = stacks.pop();
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

}
