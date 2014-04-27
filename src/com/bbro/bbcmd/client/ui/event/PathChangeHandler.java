package com.bbro.bbcmd.client.ui.event;

import com.google.gwt.event.shared.EventHandler;

public interface PathChangeHandler extends EventHandler {
	
	void onPathChange(String newPath);

}
