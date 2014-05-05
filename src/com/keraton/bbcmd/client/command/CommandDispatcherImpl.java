/*
 *	 The MIT License (MIT)
 *	
 *	Copyright (c) [2014] [bowie.brotosumpeno]
 *	
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *	
 *	The above copyright notice and this permission notice shall be included in all
 *	copies or substantial portions of the Software.
 *	
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *	SOFTWARE.
 */
package  com.keraton.bbcmd.client.command;

import java.util.Stack;

import com.keraton.bbcmd.client.command.basic.EmptyCommand;
import com.keraton.bbcmd.client.command.basic.NotFoundCommand;
import com.keraton.bbcmd.client.command.exception.CommandException;
import com.keraton.bbcmd.client.command.exception.IllegalOptionCommandException;
import com.keraton.bbcmd.client.command.share.Commandable;
import com.keraton.bbcmd.client.command.share.ExitCommandable;
import com.keraton.bbcmd.client.command.share.Stackable;
import com.keraton.bbcmd.client.command2ui.ExecutableRegistry;
import com.keraton.bbcmd.client.common.utils.CommandDTO;

public final class CommandDispatcherImpl implements CommandDispatcher {
		
	private Stackable mainStack;
	
	private Stack<Stackable> stacks = new Stack<Stackable>();
	private NotFoundCommand notFound = new NotFoundCommand();
	private EmptyCommand emptyCommand = new EmptyCommand();
	
	
	public CommandDispatcherImpl(Stackable mainStack) {
		this.mainStack = mainStack;
	}
	
	public void dispatch(CommandDTO commandDTO) {
		
		Commandable realCommand = null;
		
		realCommand = mainStack.getCommands(commandDTO);
			
		if (realCommand == null) {
			realCommand = notFound;
		} 
		
		dispatch(realCommand, commandDTO);
		
	}

	@Override
	public void dispatch(Commandable command, CommandDTO commandDTO) {
		Commandable executeCommand = command;
			
		if (executeCommand instanceof Stackable
				&& mainStack != executeCommand) {
			if (commandDTO.getArgs() == null || commandDTO.getArgs().isEmpty()) {
				stacks.push(mainStack);
				mainStack = (Stackable) command;
				ExecutableRegistry.getExecutable().printPath(mainStack.getKey());
				executeCommand = emptyCommand;
			}
			else {
				commandDTO = new CommandDTO(commandDTO.getCommand(), 
						commandDTO.getArgs(), commandDTO.getArgs(), commandDTO.getSource());
			}
		}
		
		if (executeCommand instanceof ExitCommandable) {
			if (stacks.size() > 0 ) {
				mainStack = stacks.pop();
				ExecutableRegistry.getExecutable().printPath(mainStack.getKey());
			}
		}
			
		try {
			executeCommand.doCommand(commandDTO);
		}
		catch (IllegalOptionCommandException e) {
			StringBuilder sb = new StringBuilder();
			sb.append(executeCommand.getKey());
			sb.append(" : illegal option");
			sb.append("<br/>");
			ExecutableRegistry.getExecutable().printErr(sb.toString());
		}
		catch(CommandException e) {
			ExecutableRegistry.getExecutable().printErr("Command error : " + e.getMessage());
		}
	}

}
