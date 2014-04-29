package com.bbro.bbcmd.client.init;

import java.util.Arrays;
import java.util.List;

import com.bbro.bbcmd.client.command.stack.ClientStack;
import com.bbro.bbcmd.client.command.stack.AbstractServerStack;
import com.bbro.bbcmd.client.command.stack.GetServerStack;
import com.google.gwt.event.shared.SimpleEventBus;

public class JSInitializer implements Initializer {
	
	private final SimpleEventBus bus;

	JSInitializer(SimpleEventBus bus) {
		this.bus = bus;
	}
	
	public void buildServerStack() {
		List<GetServerStack> serverStacks = 
				Arrays.asList(new GetServerStack("test", "test", "this is test", bus));
		
		for (AbstractServerStack genericServerStack : serverStacks) {
			ClientStack.getINSTANCE().addCommand(genericServerStack);
		}
	}
}
