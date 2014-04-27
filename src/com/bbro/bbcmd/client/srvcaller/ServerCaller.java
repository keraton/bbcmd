package com.bbro.bbcmd.client.srvcaller;

import com.bbro.bbcmd.client.srvcaller.data.RequestData;
import com.google.gwt.http.client.RequestCallback;

public interface ServerCaller {
	
	void send(RequestData request, RequestCallback callback);

}
