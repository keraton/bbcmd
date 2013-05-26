package com.bbro.bbcmd.server;

import javax.servlet.ServletException;

import com.bbro.bbcmd.client.core.CommandServerService;
import com.bbro.bbcmd.server.core.CommandServerDispatcher;
import com.bbro.bbcmd.server.core.CommandServerDispatcherFactory;
import com.bbro.bbcmd.shared.model.Requestable;
import com.bbro.bbcmd.shared.model.Responsable;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CommandServiceImpl extends RemoteServiceServlet implements
		CommandServerService {

	@Override
	public void init() throws ServletException {
		super.init();
		commandServerDispatcher = CommandServerDispatcherFactory.getInstance().getCommandable();
	}
	
	private CommandServerDispatcher commandServerDispatcher;

	@Override
	public Responsable execute(String path, Requestable request) {
		return commandServerDispatcher.dispatch(getThreadLocalRequest().getSession(), path, request);
	}

}
