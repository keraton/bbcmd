package com.bbro.bbcmd.client.servercaller;

import com.bbro.bbcmd.client.servercaller.data.RequestData;
import com.google.gwt.http.client.RequestCallback;

public interface ServerCaller {
	
	void send(RequestData request, RequestCallback callback);

}
