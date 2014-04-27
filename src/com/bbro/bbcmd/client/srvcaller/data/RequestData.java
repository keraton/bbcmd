package com.bbro.bbcmd.client.srvcaller.data;

import com.google.gwt.http.client.RequestBuilder.Method;

public class RequestData {

	private final Method method;
	private final String path;
	private final String request;

	public RequestData(Method method, String path, String request) {
		this.method = method;
		this.path = path;
		this.request = request;
	}

	public final Method getMethod() {
		return method;
	}

	public final String getPath() {
		return path;
	}

	public final String getRequest() {
		return request;
	}
	
	
}
