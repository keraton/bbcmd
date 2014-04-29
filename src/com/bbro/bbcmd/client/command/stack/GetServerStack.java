package com.bbro.bbcmd.client.command.stack;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestBuilder.Method;

public class GetServerStack extends AbstractServerStack {

	public GetServerStack(String key, String urlPath, String description,
			SimpleEventBus bus) {
		super(key, urlPath, description, bus);
	}

	@Override
	protected Method getMethod() {
		return RequestBuilder.GET;
	}

}
