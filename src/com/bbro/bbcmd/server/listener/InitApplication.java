package com.bbro.bbcmd.server.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bbro.bbcmd.client.command.exp4j.Exp4jEvalCommand;
import com.bbro.bbcmd.server.command.exp4j.Exp4jCommand;
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
		MapCommand.INSTANCE.putCommand(Exp4jEvalCommand.class.getName(), new Exp4jCommand());
	}

}
