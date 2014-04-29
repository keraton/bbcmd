package com.bbro.bbcmd.client.command2ui;

import com.bbro.bbcmd.client.command.CommandDispatcher;
import com.bbro.bbcmd.client.command2ui.event.CommandEvent;
import com.bbro.bbcmd.client.command2ui.event.CommandHandler;
import com.bbro.bbcmd.client.command2ui.event.UICommandEvent;
import com.bbro.bbcmd.client.command2ui.event.UICommandHandler;
import com.bbro.bbcmd.client.common.utils.StringUtils;
import com.bbro.bbcmd.client.ui.event.CleanEvent;
import com.bbro.bbcmd.client.ui.event.CommandErrReturnEvent;
import com.bbro.bbcmd.client.ui.event.CommandReturnEvent;
import com.bbro.bbcmd.client.ui.event.PathChangeEvent;
import com.google.gwt.event.shared.SimpleEventBus;

public class Command2UI implements Executable {
	
	protected SimpleEventBus bus;

	public Command2UI(final CommandDispatcher dispatcher, 
				final SimpleEventBus bus) {
		this.bus = bus;
		
		// UI -> Command
		bus.addHandler(UICommandEvent.TYPE, new UICommandHandler() {
			
			@Override
			public void onCommand(String text) {
				
				// Do some => Translation
				String[] args = text.split(" ");
				String[] argsCmd = null;
				if(args.length > 0) {
					argsCmd = new String[args.length-1];
					for (int i = 1; i < args.length; i++) {
						argsCmd[i-1] = args[i];
					}
				}
				// Bridge
				dispatcher.dispatch(args[0], StringUtils.reconstructArgs(argsCmd));
			}
		});
		
		// Generic -> Command
		bus.addHandler(CommandEvent.TYPE, new CommandHandler() {
			
			@Override
			public void onCommand(CommandEvent event) {
				dispatcher.dispatch(event.getCommandable(), event.getText());
			}
		});
		
	}
	
	@Override
	public void print(String in) {
		bus.fireEvent(new CommandReturnEvent(in));
	}

	@Override
	public void printPath(String path) {
		bus.fireEvent(new PathChangeEvent(path));
	}

	@Override
	public void printErr(String err) {
		bus.fireEvent(new CommandErrReturnEvent(err));
	}

	@Override
	public void clean() {
		bus.fireEvent(new CleanEvent());
	}
	
}
