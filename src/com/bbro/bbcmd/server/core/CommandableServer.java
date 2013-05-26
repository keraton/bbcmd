package com.bbro.bbcmd.server.core;

import javax.servlet.http.HttpSession;

import com.bbro.bbcmd.shared.model.Requestable;
import com.bbro.bbcmd.shared.model.Responsable;

public interface CommandableServer {
	
	Responsable doCommand(HttpSession session, Requestable request);

}
