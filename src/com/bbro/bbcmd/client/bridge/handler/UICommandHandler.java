package com.bbro.bbcmd.client.bridge.handler;

import com.google.gwt.event.shared.EventHandler;

public interface UICommandHandler extends EventHandler {

	void onCommand(String text);
}
