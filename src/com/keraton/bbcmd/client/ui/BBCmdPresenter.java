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
package  com.keraton.bbcmd.client.ui;

import java.util.LinkedList;

import com.google.gwt.event.shared.SimpleEventBus;
import com.keraton.bbcmd.client.command2ui.event.UICommandEvent;
import com.keraton.bbcmd.client.ui.IBBCmdView.Presenter;
import com.keraton.bbcmd.client.ui.event.CleanEvent;
import com.keraton.bbcmd.client.ui.event.CleanHandler;
import com.keraton.bbcmd.client.ui.event.CommandErrReturnEvent;
import com.keraton.bbcmd.client.ui.event.CommandErrReturnHandler;
import com.keraton.bbcmd.client.ui.event.CommandReturnEvent;
import com.keraton.bbcmd.client.ui.event.CommandReturnHandler;
import com.keraton.bbcmd.client.ui.event.PathChangeEvent;
import com.keraton.bbcmd.client.ui.event.PathChangeHandler;

public class BBCmdPresenter implements Presenter{
	
	private SimpleEventBus bus;
	private IBBCmdView view;
	private LinkedList<String> commands = new LinkedList<String>();
	private int commandIndex = 0;
	private String path = "";
	private String tempText = "";
	
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
		
		bus.addHandler(CleanEvent.TYPE, new CleanHandler() {
			
			@Override
			public void onClean() {
				view.clean();
				commandIndex = 0;
			}
		});
	}

	@Override
	public void onSubmit() {
		String text = this.view.getCmdText() == null ? "" : this.view.getCmdText();
		if (text.endsWith("\\")) {
			view.setSymbol(">");
			this.view.setTextFromInputCmd();
			tempText += text.substring(0, text.lastIndexOf("\\"));
		}
		else {
			view.setSymbol("$");
			this.view.setTextFromInputCmd();
			tempText += text;
			commands.addFirst(tempText);
			commandIndex = 0;
	
			bus.fireEvent(new UICommandEvent(tempText));
			tempText = "";
		}
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

	@Override
	public void onTabInput() {
		// TODO Auto-generated method stub
	}

}
