package com.bbro.bbcmd.client.command.stack;

import com.bbro.bbcmd.client.servercaller.data.RequestData;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestBuilder.Method;
import com.google.gwt.http.client.URL;

public class GetServerStack extends AbstractServerStack {

	public GetServerStack(GetServerStackParameter parameterObject) {
		super(parameterObject.key, parameterObject.urlPath, parameterObject.description, parameterObject.bus);
	}

	@Override
	protected Method getMethod() {
		return RequestBuilder.GET;
	}

	@Override
	protected RequestData createRequest(String args) {
		GWT.log("args : " + args);
		return new RequestData(getMethod(), urlPath + URL.encodePathSegment(args) , args);
	}

}
