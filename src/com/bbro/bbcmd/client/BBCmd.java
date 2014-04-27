package com.bbro.bbcmd.client;

import com.bbro.bbcmd.client.bridge.CommandBridge;
import com.bbro.bbcmd.client.bridge.ExecutableRegistry;
import com.bbro.bbcmd.client.bridge.handler.UICommandEvent;
import com.bbro.bbcmd.client.command.ClientStack;
import com.bbro.bbcmd.client.command.sample.SampleStack;
import com.bbro.bbcmd.client.command.sample_server.HelloServerCommand;
import com.bbro.bbcmd.client.core.CommandDispatcherImpl;
import com.bbro.bbcmd.client.ui.BBCmdPresenter;
import com.bbro.bbcmd.client.ui.BasicCmdView;
import com.bbro.bbcmd.client.ui.IBBCmdView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
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
		
		// Wire Core <-> Command
		ExecutableRegistry.setExecutable(new CommandBridge(commandDispatcher, bus));
		
		// Root panel
		RootPanel.getBodyElement().appendChild(view.getElement());
		
		// Load 
		presenter.init();
		
		// Add main stack TODO SRP Violation
		ClientStack.getINSTANCE().addCommand(new HelloServerCommand());
		ClientStack.getINSTANCE().addCommand(new SampleStack());
		
		// Add History TODO SRP violation
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				String token = event.getValue();
				bus.fireEvent(new UICommandEvent(token));
			}
		});
		
		GWT.log("BBCmd loaded");
		
	}
}
