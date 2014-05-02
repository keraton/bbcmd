/*
 *	 The MIT License (MIT)
 *	
 *	Copyright (c) [2014] [bowie.brotosumpeno]
 *	
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *	
 *	The above copyright notice and this permission notice shall be included in all
 *	copies or substantial portions of the Software.
 *	
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *	SOFTWARE.
 */
package  com.bbro.bbcmd.client;

import com.bbro.bbcmd.client.command.CommandDispatcherImpl;
import com.bbro.bbcmd.client.command.stack.ClientStack;
import com.bbro.bbcmd.client.command2ui.Command2UI;
import com.bbro.bbcmd.client.command2ui.ExecutableRegistry;
import com.bbro.bbcmd.client.init.InitInfo;
import com.bbro.bbcmd.client.init.InitializerFactory;
import com.bbro.bbcmd.client.servercaller.ServerCallerManager;
import com.bbro.bbcmd.client.ui.BBCmdPresenter;
import com.bbro.bbcmd.client.ui.BasicCmdView;
import com.bbro.bbcmd.client.ui.IBBCmdView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
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
		
		GWT.log("Start BBCmd");
		
		final SimpleEventBus bus = new SimpleEventBus();
		
		InitInfo initInfo = InitializerFactory.getInstance().init(bus);
		GWT.log("init info" + initInfo.getDomId());
		GWT.log("init info" + initInfo.isUseBody());
		if (null == initInfo.getDomId() && !initInfo.isUseBody())
			return;
		
		// Init Core
		CommandDispatcherImpl commandDispatcher = new CommandDispatcherImpl(ClientStack.getINSTANCE());
		
		// Init UI
		IBBCmdView view = new BasicCmdView();
		BBCmdPresenter presenter = new BBCmdPresenter(view, bus);
		
		// Init Server Caller
		new ServerCallerManager(bus);
		
		// Wire Core <-> Command
		ExecutableRegistry.setExecutable(new Command2UI(commandDispatcher, bus));
		
		// Load 
		loadInitConfig(initInfo, view);
		
		presenter.init();
		
		GWT.log("here");
		InitializerFactory.getInstance().buildServerStack();
		
		GWT.log("BBCmd loaded");
		
	}

	private void loadInitConfig(InitInfo initInfo, IBBCmdView view) {
		if (initInfo.isUseBody()) {
			RootPanel.getBodyElement().appendChild(view.getElement());
			RootPanel.getBodyElement().addClassName("bbcmd_body");
		}
		else {
			RootPanel.get(initInfo.getDomId()).getElement().getStyle().setWidth(initInfo.getWidth(), Unit.PX);
			RootPanel.get(initInfo.getDomId()).getElement().getStyle().setHeight(initInfo.getHeight(), Unit.PX);
			RootPanel.get(initInfo.getDomId()).getElement().addClassName("bbcmd_body");
			RootPanel.get(initInfo.getDomId()).getElement().addClassName("bbcmd_body_box");
			RootPanel.get(initInfo.getDomId()).getElement().appendChild(view.getElement());
		}
	}
}
