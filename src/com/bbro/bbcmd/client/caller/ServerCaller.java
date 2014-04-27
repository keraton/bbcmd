package com.bbro.bbcmd.client.caller;

import com.google.gwt.http.client.RequestCallback;

public interface ServerCaller {
	
	void send(RequestData request, RequestCallback callback);

}
