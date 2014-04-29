package com.bbro.bbcmd.client.command;

import com.bbro.bbcmd.client.command.share.Commandable;

public interface CommandDispatcher {

	void dispatch(String command, String args);
	
	void dispatch(Commandable command, String args);

}
