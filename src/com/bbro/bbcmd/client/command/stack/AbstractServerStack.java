package com.bbro.bbcmd.client.command.stack;

import com.bbro.bbcmd.client.command.basic.EmptyCommand;
import com.bbro.bbcmd.client.command.share.Commandable;
import com.bbro.bbcmd.client.command.share.Stackable;
import com.bbro.bbcmd.client.command2ui.ExecutableRegistry;
import com.bbro.bbcmd.client.srvcaller.data.RequestData;
import com.bbro.bbcmd.client.srvcaller.event.ServerCommandEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.RequestBuilder.Method;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;

public abstract class AbstractServerStack implements Stackable {
	
	private final String KEY;
	private final String urlPath;
	private final String description; 
	private final EmptyCommand emptyCommand = new EmptyCommand();
	private final SimpleEventBus bus;
	
	public AbstractServerStack(String key, String urlPath, String description, SimpleEventBus bus) {
		this.KEY = key;
		this.urlPath = urlPath;
		this.description = description;
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
		RequestData request = new RequestData(getMethod(), urlPath, args);
		RequestCallback callback = new RequestCallback() {
			
			@Override
			public void onResponseReceived(Request request, Response response) {
				response.getText();
			}
			
			@Override
			public void onError(Request request, Throwable exception) {
				
			}
		};
		ServerCommandEvent srvCmd = new ServerCommandEvent(request, callback);
		bus.fireEvent(srvCmd);
		
		return emptyCommand;
	}

	@Override
	public boolean isCallDirect() {
		return true;
	}
	
	protected abstract Method getMethod();

}
