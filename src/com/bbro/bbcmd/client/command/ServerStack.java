package com.bbro.bbcmd.client.command;

import com.bbro.bbcmd.client.bridge.ExecutableRegistry;
import com.bbro.bbcmd.client.bridge.handler.CommandEvent;
import com.bbro.bbcmd.client.core.CommandServerService;
import com.bbro.bbcmd.client.core.CommandServerServiceAsync;
import com.bbro.bbcmd.client.core.Commandable;
import com.bbro.bbcmd.client.core.Stackable;
import com.bbro.bbcmd.shared.model.CommandArgs;
import com.bbro.bbcmd.shared.model.CommandResponsable;
import com.bbro.bbcmd.shared.model.Responsable;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ServerStack implements Stackable {
	
	private final String KEY;
	private final String description; 
	private final Stackable stackable;
	private final EmptyCommand emptyCommand = new EmptyCommand();
	private final SimpleEventBus bus;
	
	private CommandServerServiceAsync commandServerService = GWT.create(CommandServerService.class);
	
	public ServerStack(String key, String description, 
			Stackable stackable, SimpleEventBus bus) {
		this.KEY = key;
		this.description = description;
		this.stackable = stackable;
		this.bus = bus;
	}

	@Override
	public String getKey() {
		return KEY;
	}
	
	@Override
	public void doCommand(String args) {
		ExecutableRegistry.getExecutable()
					.print(description);
	}

	@Override
	public Commandable getCommands(String args) {
		
		commandServerService.execute(KEY, null, new AsyncCallback<Responsable>() {
			
			@Override
			public void onSuccess(Responsable result) {
				CommandResponsable resp = (CommandResponsable) result;
				for (CommandArgs commandArgs : resp.getCommandArgs()) {
					bus.fireEvent(new CommandEvent(commandArgs.getArgs(), stackable.getCommands(commandArgs.getKey())));
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				ExecutableRegistry.getExecutable().printErr("Error : "  + caught.getMessage());
			}
		});
		
		return emptyCommand;
		
	}

	@Override
	public boolean isCallDirect() {
		return true;
	}

	@Override
	public String getPossibleCommand(String key, int counter) {
		return "";
	}

}
