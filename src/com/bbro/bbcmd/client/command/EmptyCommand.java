package com.bbro.bbcmd.client.command;

import com.bbro.bbcmd.client.core.Commandable;

public class EmptyCommand implements Commandable {

	public static final String KEY = "";

	@Override
	public String getKey() {
		return "";
	}

	@Override
	public void doCommand(String args) {
	}

}
