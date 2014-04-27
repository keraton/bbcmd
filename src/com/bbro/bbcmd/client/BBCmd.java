package com.bbro.bbcmd.client;

import com.bbro.bbcmd.client.command.basic.ClientStack;
import com.bbro.bbcmd.client.command.dispatcher.CommandDispatcherImpl;
import com.bbro.bbcmd.client.command2ui.Command2UI;
import com.bbro.bbcmd.client.command2ui.ExecutableRegistry;
import com.bbro.bbcmd.client.command2ui.event.UICommandEvent;
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
		ExecutableRegistry.setExecutable(new Command2UI(commandDispatcher, bus));
		
		// Root panel
		RootPanel.getBodyElement().appendChild(view.getElement());
		
		// Load 
		presenter.init();
		
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
