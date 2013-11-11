package com.bbro.bbcmd.client;

import com.bbro.bbcmd.client.core.CommandServerServiceAsync;
import com.bbro.bbcmd.shared.model.Requestable;
import com.bbro.bbcmd.shared.model.Responsable;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MockCommandServerServiceAsync implements CommandServerServiceAsync {
	
	// Config
	private boolean success;
	private Responsable responsable;
	private Throwable throwable;
	
	// Request
	private Requestable requestable;
	private String path;
	
	public MockCommandServerServiceAsync(boolean success, Responsable responsable, Throwable throwable) {
		this.success = success;
		this.responsable = responsable;
		this.throwable = throwable;
	}

	@Override
	public void execute(String path, Requestable request,
			AsyncCallback<Responsable> callback) {
		this.requestable = request;
		this.path = path;
		
		if (success){
			callback.onSuccess(responsable);
		}
		else {
			callback.onFailure(throwable);
		}
	}

	/**
	 * @return the requestable
	 */
	public final Requestable getRequestable() {
		return requestable;
	}

	/**
	 * @return the path
	 */
	public final String getPath() {
		return path;
	}

}
