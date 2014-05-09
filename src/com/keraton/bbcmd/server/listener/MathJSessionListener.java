package com.keraton.bbcmd.server.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.keraton.bbcmd.server.session.MathJSession;

public class MathJSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		MathJSession.getInstance().remove(arg0.getSession().getId());
	}

}
