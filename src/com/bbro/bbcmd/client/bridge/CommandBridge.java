package com.bbro.bbcmd.client.bridge;

import com.bbro.bbcmd.client.bridge.handler.CommandEvent;
import com.bbro.bbcmd.client.bridge.handler.CommandHandler;
import com.bbro.bbcmd.client.bridge.handler.UICommandEvent;
import com.bbro.bbcmd.client.bridge.handler.UICommandHandler;
import com.bbro.bbcmd.client.bridge.handler.UIFindCommandEvent;
import com.bbro.bbcmd.client.bridge.handler.UIFindCommandHandler;
import com.bbro.bbcmd.client.core.CommandDispatcher;
import com.bbro.bbcmd.client.ui.handler.CleanEvent;
import com.bbro.bbcmd.client.ui.handler.CommandErrReturnEvent;
import com.bbro.bbcmd.client.ui.handler.CommandReturnEvent;
import com.bbro.bbcmd.client.ui.handler.PathChangeEvent;
import com.bbro.bbcmd.client.utils.StringUtils;
import com.google.gwt.event.shared.SimpleEventBus;

public class CommandBridge implements Executable {
	
	protected SimpleEventBus bus;

	public CommandBridge(final CommandDispatcher dispatcher, 
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
		
		// UI find command
		bus.addHandler(UIFindCommandEvent.TYPE, new UIFindCommandHandler() {
			
			@Override
			public void onCommand(UIFindCommandEvent event) {
				dispatcher.findCommand(event.getText(), event.getCounter());
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
