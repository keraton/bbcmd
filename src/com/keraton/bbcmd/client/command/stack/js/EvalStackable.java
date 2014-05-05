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
package  com.keraton.bbcmd.client.command.stack.js;

import com.keraton.bbcmd.client.command.basic.EmptyCommand;
import com.keraton.bbcmd.client.command.basic.ExitCommand;
import com.keraton.bbcmd.client.command.exception.CommandException;
import com.keraton.bbcmd.client.command.share.Commandable;
import com.keraton.bbcmd.client.command.share.Descriptable;
import com.keraton.bbcmd.client.command.share.Directable;
import com.keraton.bbcmd.client.command.share.Stackable;
import com.keraton.bbcmd.client.command.stack.ClientStack;
import com.keraton.bbcmd.client.command2ui.ExecutableRegistry;
import com.keraton.bbcmd.client.common.utils.CommandDTO;
import com.keraton.bbcmd.client.common.utils.CommandDTO.Source;

public class EvalStackable implements 
	Commandable, Descriptable, Stackable, Directable {
	
	public static String KEY = "eval";
	private ExitCommand exitCommand = new ExitCommand();
	
	public EvalStackable() {
		JSUtility.exportStaticMethod();
	}

	@Override
	public String getDescription() {
		return "Javascript eval call";
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public void doCommand(CommandDTO commandDTO) throws CommandException {
		try {
			eval(commandDTO.getCommandArgs());
		}
		catch(Exception e) {
			ExecutableRegistry.getExecutable().printErr(e.getMessage());
		}
	}
	
	private final native void eval(String args) /*-{
		println = function(param) {
			$wnd.printGWT('' + param);
		};
	  	eval(args);
	}-*/;

	@Override
	public Commandable getCommands(CommandDTO commandDTO) {
		if (commandDTO.getSource() == Source.JS) {
			return ClientStack.getINSTANCE().getCommands(commandDTO.getCommand());
		}
		
		String key = commandDTO.getCommand();
		if (ExitCommand.KEY.equals(key)) {
			return exitCommand;
		}
		else if ("".equals(key) || null == key) {
			return ClientStack.getINSTANCE().getCommands(EmptyCommand.KEY);
		}
		return this;
	}

}
