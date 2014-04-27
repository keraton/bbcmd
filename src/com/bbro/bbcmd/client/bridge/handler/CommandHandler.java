package com.bbro.bbcmd.client.bridge.handler;

import com.google.gwt.event.shared.EventHandler;

public interface CommandHandler extends EventHandler {

	void onCommand(CommandEvent event);
}
