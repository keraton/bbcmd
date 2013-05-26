package com.bbro.bbcmd.server.core;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.bbro.bbcmd.shared.model.Requestable;
import com.bbro.bbcmd.shared.model.Responsable;

public class CommandServerDispatcherImpl implements CommandServerDispatcher {
	
	private Map<String, CommandableServer> mapCommand;
	
	public CommandServerDispatcherImpl() {
		this.mapCommand = MapCommand.INSTANCE.getMap();
	}

	@Override
	public Responsable dispatch(HttpSession session, String path, Requestable request) {
		
		if (null == path ) {
			throw new IllegalArgumentException("Path is missing");
		}
		
		CommandableServer command = mapCommand.get(path);
		
		if (null == command) {
			throw new IllegalStateException("No command found for " + path);
		}
		
		return command.doCommand(session, request);
	}

}
