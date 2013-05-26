package com.bbro.bbcmd.client.core;

import com.bbro.bbcmd.shared.model.Requestable;
import com.bbro.bbcmd.shared.model.Responsable;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CommandServerServiceAsync {

	void execute(String path, Requestable request,
			AsyncCallback<Responsable> callback);
	
	

}
