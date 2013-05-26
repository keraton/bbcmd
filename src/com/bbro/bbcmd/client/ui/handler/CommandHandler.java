package com.bbro.bbcmd.client.ui.handler;

import com.google.gwt.event.shared.EventHandler;

public interface CommandHandler extends EventHandler {

	void onCommand(String text);
}
