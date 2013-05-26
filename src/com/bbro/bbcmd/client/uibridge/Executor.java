package com.bbro.bbcmd.client.uibridge;

import com.bbro.bbcmd.client.core.CommandDispatcher;
import com.bbro.bbcmd.client.ui.handler.CommandReturnEvent;
import com.bbro.bbcmd.client.ui.handler.PathChangeEvent;
import com.google.gwt.event.shared.SimpleEventBus;

public class Executor extends CommandUiBridge implements Executable {
	
	
	public Executor(CommandDispatcher dispatcher, SimpleEventBus bus) {
		super(dispatcher, bus);
	}

	@Override
	public void print(String in) {
		bus.fireEvent(new CommandReturnEvent(in));
	}

	@Override
	public void printPath(String path) {
		bus.fireEvent(new PathChangeEvent(path));
	}


}
