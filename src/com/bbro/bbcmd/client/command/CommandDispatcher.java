package com.bbro.bbcmd.client.command;

public interface CommandDispatcher {

	void dispatch(String command, String args);
	
	void dispatch(Commandable command, String args);

	void findCommand(String text, int counter);
}
