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
package  com.keraton.bbcmd.client.command.stack;

import java.util.HashMap;

import com.keraton.bbcmd.client.command.basic.CleanCommand;
import com.keraton.bbcmd.client.command.basic.EchoCommand;
import com.keraton.bbcmd.client.command.basic.EmptyCommand;
import com.keraton.bbcmd.client.command.basic.EnvCommand;
import com.keraton.bbcmd.client.command.basic.ErrCommand;
import com.keraton.bbcmd.client.command.basic.HelpCommand;
import com.keraton.bbcmd.client.command.exception.CommandException;
import com.keraton.bbcmd.client.command.share.Commandable;
import com.keraton.bbcmd.client.command.share.Stackable;
import com.keraton.bbcmd.client.command.stack.js.EvalStackable;
import com.keraton.bbcmd.client.common.utils.CommandDTO;

public final class ClientStack implements Stackable {
	
	private static ClientStack INSTANCE = new ClientStack();
	
	public static final ClientStack getINSTANCE() {
		return INSTANCE;
	}

	private HashMap<String, Commandable> mapCommands = new HashMap<String, Commandable>();
	
	private ClientStack() {
		addCommand(new CleanCommand());
		addCommand(new HelpCommand(mapCommands));
		addCommand(new EnvCommand());
		addCommand(new EmptyCommand());
		addCommand(new EchoCommand());
		addCommand(new EvalStackable());
		addCommand(new ErrCommand());
	}

	public void addCommand(Commandable command){
		mapCommands.put(command.getKey(), command);
	}
	
	@Override
	public Commandable getCommands(CommandDTO commandDTO) {
		return mapCommands.get(commandDTO.getCommand());
	}
	
	public Commandable getCommands(String key) {
		return mapCommands.get(key);
	}

	@Override
	public String getKey() {
		return "";
	}

	@Override
	public void doCommand(CommandDTO command) throws CommandException {
		throw new CommandException("Main command should not be called");
	}

}
