package com.bbro.bbcmd.client.ui;

import java.util.LinkedList;

import com.bbro.bbcmd.client.ui.IBBCmdView.Presenter;
import com.bbro.bbcmd.client.ui.handler.CommandErrReturnEvent;
import com.bbro.bbcmd.client.ui.handler.CommandErrReturnHandler;
import com.bbro.bbcmd.client.ui.handler.CommandEvent;
import com.bbro.bbcmd.client.ui.handler.CommandReturnEvent;
import com.bbro.bbcmd.client.ui.handler.CommandReturnHandler;
import com.bbro.bbcmd.client.ui.handler.PathChangeEvent;
import com.bbro.bbcmd.client.ui.handler.PathChangeHandler;
import com.google.gwt.event.shared.SimpleEventBus;

public class BBCmdPresenter implements Presenter{
	
	private SimpleEventBus bus;
	
	private IBBCmdView view;
	
	private LinkedList<String> commands = new LinkedList<String>();
	
	private int commandIndex = 0;
	
	private String path = "";
	
	public BBCmdPresenter(final IBBCmdView view, SimpleEventBus bus) {
		this.view = view;
		this.bus = bus;
		view.setPresenter(this);
		
		bus.addHandler(CommandReturnEvent.TYPE, new CommandReturnHandler() {
			@Override
			public void onRetour(String text) {
				if (null != text && !"".equals(text)) {
					view.addText(text);
				}
			}
		});
		
		bus.addHandler(CommandErrReturnEvent.TYPE, new CommandErrReturnHandler() {
			
			@Override
			public void onRetour(String text) {
				if (null != text && !"".equals(text)) {
					view.addErrText(text);
				}
			}
		});
		
		bus.addHandler(PathChangeEvent.TYPE, new PathChangeHandler() {
			@Override
			public void onPathChange(String newPath) {
				view.setPath(newPath);
				path = newPath;
			}
		});
	}

	@Override
	public void onSubmit() {
		String text = this.view.getCmdText() == null ? "" : this.view.getCmdText();
		this.view.addText(path + "$ " + text);
		commands.addFirst(text);
		commandIndex = 0;

		bus.fireEvent(new CommandEvent(text));
		// TODO Block the input ?
	}

	@Override
	public void init() {
		this.view.init();
	}

	@Override
	public void onUp() {
		if (commands.size() > commandIndex) {
			this.view.setCmdText(commands.get(commandIndex));
			commandIndex++;
		}
	}

	@Override
	public void onDown() {
		if (0 < commandIndex) {
			commandIndex--;
			
			if (commandIndex == 0) {
				this.view.setCmdText("");
			}
			else {
				this.view.setCmdText(commands.get(commandIndex-1));
			}
		}
		
	}


}
