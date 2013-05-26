package com.bbro.bbcmd.client.uibridge;

import com.bbro.bbcmd.client.core.CommandDispatcher;
import com.bbro.bbcmd.client.ui.handler.CommandEvent;
import com.bbro.bbcmd.client.ui.handler.CommandHandler;
import com.google.gwt.event.shared.SimpleEventBus;

public abstract class CommandUiBridge implements Executable {

	
	protected SimpleEventBus bus;

	public CommandUiBridge(final CommandDispatcher dispatcher, 
				final SimpleEventBus bus) {
		this.bus = bus;
		
		// UI -> Command
		bus.addHandler(CommandEvent.TYPE, new CommandHandler() {
			
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
				dispatcher.dispatch(args[0], argsCmd);
			}
		});
		
	}
	
	
	
}
