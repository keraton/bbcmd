package com.bbro.bbcmd.server.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bbro.bbcmd.server.command.sample.HelloServerCommand;
import com.bbro.bbcmd.server.core.CommandServerDispatcherImpl;
import com.bbro.bbcmd.server.core.CommandServerDispatcherFactory;
import com.bbro.bbcmd.server.core.MapCommand;

public class InitApplication implements ServletContextListener {


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		CommandServerDispatcherFactory.getInstance().init(new CommandServerDispatcherImpl());
		MapCommand.INSTANCE.putCommand("helloserver", new HelloServerCommand());
	}

}
