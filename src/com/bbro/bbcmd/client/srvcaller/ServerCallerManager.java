package com.bbro.bbcmd.client.srvcaller;

import com.bbro.bbcmd.client.srvcaller.event.ServerCommandEvent;
import com.bbro.bbcmd.client.srvcaller.event.ServerCommandHandler;
import com.google.gwt.event.shared.SimpleEventBus;

public class ServerCallerManager {
	
	private final HTTPServerCallerImpl serverCallerImpl;
	
	public ServerCallerManager(SimpleEventBus bus) {
		
		serverCallerImpl = new HTTPServerCallerImpl();
		
		bus.addHandler(ServerCommandEvent.TYPE, new ServerCommandHandler() {
			
			@Override
			public void onCommand(ServerCommandEvent event) {
				serverCallerImpl.send(event.getData(), event.getCallback());
			}
		});
	}

}
