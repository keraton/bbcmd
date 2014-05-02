package com.bbro.bbcmd.client.command.stack.js;

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
