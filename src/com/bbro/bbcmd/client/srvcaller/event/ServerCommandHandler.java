package com.bbro.bbcmd.client.srvcaller.event;

import com.google.gwt.event.shared.EventHandler;

public interface ServerCommandHandler extends EventHandler {

	void onCommand(ServerCommandEvent event);
}
