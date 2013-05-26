package com.bbro.bbcmd.server.command.sample;

import javax.servlet.http.HttpSession;

import com.bbro.bbcmd.server.core.CommandableServer;
import com.bbro.bbcmd.shared.model.Requestable;
import com.bbro.bbcmd.shared.model.Responsable;
import com.bbro.bbcmd.shared.model.SimpleTextResponse;

public class HelloServerCommand implements CommandableServer {


	@Override
	public Responsable doCommand(HttpSession session, Requestable request) {
		SimpleTextResponse response = new SimpleTextResponse();
		response.setText("Hello World");
		return response;
	}

}
