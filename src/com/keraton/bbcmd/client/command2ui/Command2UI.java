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
package  com.keraton.bbcmd.client.command2ui;

import com.google.gwt.event.shared.SimpleEventBus;
import com.keraton.bbcmd.client.command.CommandDispatcher;
import com.keraton.bbcmd.client.command2ui.event.CommandEvent;
import com.keraton.bbcmd.client.command2ui.event.CommandHandler;
import com.keraton.bbcmd.client.command2ui.event.UICommandEvent;
import com.keraton.bbcmd.client.command2ui.event.UICommandHandler;
import com.keraton.bbcmd.client.common.utils.CommandDTO;
import com.keraton.bbcmd.client.ui.event.CleanEvent;
import com.keraton.bbcmd.client.ui.event.CommandErrReturnEvent;
import com.keraton.bbcmd.client.ui.event.CommandReturnEvent;
import com.keraton.bbcmd.client.ui.event.PathChangeEvent;

public class Command2UI implements Executable {
	
	protected SimpleEventBus bus;

	public Command2UI(final CommandDispatcher dispatcher, 
				final SimpleEventBus bus) {
		this.bus = bus;
		
		// UI -> Command
		bus.addHandler(UICommandEvent.TYPE, new UICommandHandler() {
			
			@Override
			public void onCommand(UICommandEvent event) {
				CommandDTO command = event.getCommandDTO();
				// Bridge
				dispatcher.dispatch(command);
			}
		});
		
		// Generic -> Command
		bus.addHandler(CommandEvent.TYPE, new CommandHandler() {
			
			@Override
			public void onCommand(CommandEvent event) {
				dispatcher.dispatch(event.getCommandable(), event.getCommandDTO());
			}
		});
		
	}
	
	@Override
	public void print(String in) {
		bus.fireEvent(new CommandReturnEvent(in));
	}

	@Override
	public void printPath(String path) {
		bus.fireEvent(new PathChangeEvent(path));
	}

	@Override
	public void printErr(String err) {
		bus.fireEvent(new CommandErrReturnEvent(err));
	}

	@Override
	public void clean() {
		bus.fireEvent(new CleanEvent());
	}
	
}
