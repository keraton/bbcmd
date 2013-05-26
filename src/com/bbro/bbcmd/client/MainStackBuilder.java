package com.bbro.bbcmd.client;

import com.bbro.bbcmd.client.command.MainStack;
import com.bbro.bbcmd.client.command.sample.SampleStack;
import com.bbro.bbcmd.client.command.env.EnvCommand;

public class MainStackBuilder {

	private static final MainStack MAIN_STACK = new MainStack();
	
	static MainStack buildMainStack() {
		MAIN_STACK.addCommand(new SampleStack());
		MAIN_STACK.addCommand(new EnvCommand());
		
		return MAIN_STACK;
	}
}
