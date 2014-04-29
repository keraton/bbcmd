package com.bbro.bbcmd.client;

import java.util.List;

import com.bbro.bbcmd.client.command.CommandDispatcherImpl;
import com.bbro.bbcmd.client.command.stack.ClientStack;
import com.bbro.bbcmd.client.command.stack.AbstractServerStack;
import com.bbro.bbcmd.client.command2ui.Command2UI;
import com.bbro.bbcmd.client.command2ui.ExecutableRegistry;
import com.bbro.bbcmd.client.init.InitializerFactory;
import com.bbro.bbcmd.client.srvcaller.ServerCallerManager;
import com.bbro.bbcmd.client.test.GWUnit;
import com.bbro.bbcmd.client.ui.BBCmdPresenter;
import com.bbro.bbcmd.client.ui.BasicCmdView;
import com.bbro.bbcmd.client.ui.IBBCmdView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BBCmd implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		// Start
		GWT.log("Start BBCmd");
		
		// Simple Event Bus
		final SimpleEventBus bus = new SimpleEventBus();
		
		// Init Core
		CommandDispatcherImpl commandDispatcher = new CommandDispatcherImpl(ClientStack.getINSTANCE());
		
		// Init UI
		IBBCmdView view = new BasicCmdView();
		BBCmdPresenter presenter = new BBCmdPresenter(view, bus);
		
		// Init Server Caller
		new ServerCallerManager(bus);
		
		// Init Server command
		InitializerFactory.getInstance().init(bus);
		
		// Wire Core <-> Command
		ExecutableRegistry.setExecutable(new Command2UI(commandDispatcher, bus));
		
		// Root panel
		RootPanel.getBodyElement().appendChild(view.getElement());
		
		// Load 
		presenter.init();
		
		GWT.log("BBCmd loaded");
		
	}
}
