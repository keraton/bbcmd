package com.bbro.bbcmd.shared.model;

import java.util.ArrayList;

public class CommandResponsable implements Responsable {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<CommandArgs> commandArgs;

	public final ArrayList<CommandArgs> getCommandArgs() {
		return commandArgs;
	}

	public final void setCommandArgs(ArrayList<CommandArgs> commandArgs) {
		this.commandArgs = commandArgs;
	}
	

}
