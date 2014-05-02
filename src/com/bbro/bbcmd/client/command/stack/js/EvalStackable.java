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
package  com.bbro.bbcmd.client.command.stack.js;

import com.bbro.bbcmd.client.command.basic.EmptyCommand;
import com.bbro.bbcmd.client.command.basic.ExitCommand;
import com.bbro.bbcmd.client.command.exception.CommandException;
import com.bbro.bbcmd.client.command.share.Commandable;
import com.bbro.bbcmd.client.command.share.Descriptable;
import com.bbro.bbcmd.client.command.share.Directable;
import com.bbro.bbcmd.client.command.share.Stackable;
import com.bbro.bbcmd.client.command2ui.ExecutableRegistry;
import com.google.gwt.core.shared.GWT;

public class EvalStackable implements 
	Commandable, Descriptable, Stackable, Directable {
	
	public static String KEY = "eval";
	
	private ExitCommand exitCommand = new ExitCommand();
	private EmptyCommand emptyCommand = new EmptyCommand();
	
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
	public void doCommand(String arg) throws CommandException {
		GWT.log(arg);
		try {
			eval(arg);
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
	public Commandable getCommands(String key) {
		if (ExitCommand.KEY.equals(key)) {
			return exitCommand;
		}
		if ("".equals(key) || null == key) {
			return emptyCommand;
		}
		return this;
	}

}
