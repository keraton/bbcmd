package com.bbro.bbcmd.client.command2ui.event;

import com.google.gwt.event.shared.EventHandler;

public interface CommandHandler extends EventHandler {

	void onCommand(CommandEvent event);
}