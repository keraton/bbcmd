package com.bbro.bbcmd.client.command.dispatcher;

import com.bbro.bbcmd.client.command.Commandable;

public interface CommandDispatcher {

	void dispatch(String command, String args);
	
	void dispatch(Commandable command, String args);

	void findCommand(String text, int counter);
}
