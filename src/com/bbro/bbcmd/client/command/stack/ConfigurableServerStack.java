package com.bbro.bbcmd.client.command.stack;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.RequestBuilder.Method;

public class ConfigurableServerStack extends AbstractServerStack {
	
	private final Method method;

	// TODO change this into GetServerStackParameter
	public ConfigurableServerStack(String key, String urlPath,
			String description, SimpleEventBus bus, Method method) {
		super(key, urlPath, description, bus);
		this.method = method;
	}

	@Override
	protected Method getMethod() {
		return method;
	}

}
