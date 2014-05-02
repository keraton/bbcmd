package com.bbro.bbcmd.client.servercaller;

import com.bbro.bbcmd.client.servercaller.data.RequestData;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;

public class HTTPServerCallerImpl implements ServerCaller {

	@Override
	public void send(RequestData requestData, final RequestCallback callback) {
		
		RequestBuilder builder = new RequestBuilder(requestData.getMethod(), 
				URL.encode("/" + requestData.getPath()));

		try {
			GWT.log(requestData.getRequest());
			builder.sendRequest(requestData.getRequest(), new RequestCallback() {

				@Override
				public void onResponseReceived(Request request,
						Response response) {
					callback.onResponseReceived(request, response);
				}

				@Override
				public void onError(Request request, Throwable exception) {
					callback.onError(request, exception);
				}
			  });
		} catch (com.google.gwt.http.client.RequestException e) {
			callback.onError(null, e);
		}

	}

}
