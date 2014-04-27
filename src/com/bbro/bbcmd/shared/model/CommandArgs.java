package com.bbro.bbcmd.shared.model;

import java.io.Serializable;

public class CommandArgs implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String args;
	
	private String key;

	public final String getArgs() {
		return args;
	}

	public final void setArgs(String args) {
		this.args = args;
	}

	public final String getKey() {
		return key;
	}

	public final void setKey(String key) {
		this.key = key;
	}
	
}
