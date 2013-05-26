package com.bbro.bbcmd.client;

import com.bbro.bbcmd.client.core.CommandDispatcherImpl;
import com.bbro.bbcmd.client.ui.BBCmdPresenter;
import com.bbro.bbcmd.client.ui.BasicCmdView;
import com.bbro.bbcmd.client.ui.IBBCmdView;
import com.bbro.bbcmd.client.uibridge.ExecutableRegistry;
import com.bbro.bbcmd.client.uibridge.Executor;
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
		SimpleEventBus bus = new SimpleEventBus();
		
		// Init Core
		CommandDispatcherImpl commandDispatcher = new CommandDispatcherImpl(MainStackBuilder.buildMainStack());
		
		// Init UI
		IBBCmdView view = new BasicCmdView();
		BBCmdPresenter presenter = new BBCmdPresenter(view, bus);
		
		// Wire Core <-> Command
		ExecutableRegistry.setExecutable(new Executor(commandDispatcher, bus));
		
		// Root panel
		RootPanel.getBodyElement().appendChild(view.getElement());
		
		// Load 
		presenter.init();
		GWT.log("BBCmd loaded");
		
	}
}
