package com.bbro.bbcmd.server.core;

import javax.servlet.http.HttpSession;

import com.bbro.bbcmd.shared.model.Requestable;
import com.bbro.bbcmd.shared.model.Responsable;

public interface CommandServerDispatcher {

	Responsable dispatch(HttpSession session, String path, Requestable request);
}
