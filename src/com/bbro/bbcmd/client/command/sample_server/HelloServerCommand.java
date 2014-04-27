package com.bbro.bbcmd.client.command.sample_server;

import com.bbro.bbcmd.client.bridge.ExecutableRegistry;
import com.bbro.bbcmd.client.core.CommandException;
import com.bbro.bbcmd.client.core.CommandServerService;
import com.bbro.bbcmd.client.core.CommandServerServiceAsync;
import com.bbro.bbcmd.client.core.Commandable;
import com.bbro.bbcmd.shared.model.Responsable;
import com.bbro.bbcmd.shared.model.SimpleTextResponse;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class HelloServerCommand implements Commandable {
	
	public final static String KEY = "helloserver";
	
	private CommandServerServiceAsync commandServerService = GWT.create(CommandServerService.class);

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public void doCommand(String args) throws CommandException {
		commandServerService.execute(KEY, null, new AsyncCallback<Responsable>() {
			
			@Override
			public void onSuccess(Responsable result) {
				SimpleTextResponse resp = (SimpleTextResponse) result;
				ExecutableRegistry.getExecutable().print(resp.getText());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				ExecutableRegistry.getExecutable().printErr("Error : "  + caught.getMessage());
			}
		});
	}

}
