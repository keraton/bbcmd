package com.bbro.bbcmd.client.command.stack;

import com.google.gwt.event.shared.SimpleEventBus;

public class GetServerStackParameter {
	public String key;
	public String urlPath;
	public String description;
	public SimpleEventBus bus;

	public final String getKey() {
		return key;
	}

	public final void setKey(String key) {
		this.key = key;
	}

	public final String getUrlPath() {
		return urlPath;
	}

	public final void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public final SimpleEventBus getBus() {
		return bus;
	}

	public final void setBus(SimpleEventBus bus) {
		this.bus = bus;
	}
}