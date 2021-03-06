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
package  com.keraton.bbcmd.client.command.stack;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder.Method;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.keraton.bbcmd.client.command.basic.EchoCommand;
import com.keraton.bbcmd.client.command.basic.EmptyCommand;
import com.keraton.bbcmd.client.command.basic.ErrCommand;
import com.keraton.bbcmd.client.command.basic.ExitCommand;
import com.keraton.bbcmd.client.command.share.Commandable;
import com.keraton.bbcmd.client.command.share.Descriptable;
import com.keraton.bbcmd.client.command.share.Directable;
import com.keraton.bbcmd.client.command.share.Stackable;
import com.keraton.bbcmd.client.command2ui.event.CommandEvent;
import com.keraton.bbcmd.client.common.utils.CommandDTO;
import com.keraton.bbcmd.client.common.utils.CommandDTO.Source;
import com.keraton.bbcmd.client.common.utils.StringUtils;
import com.keraton.bbcmd.client.servercaller.data.RequestData;
import com.keraton.bbcmd.client.servercaller.event.ServerCommandEvent;

public abstract class AbstractServerStack implements 
	Stackable, Descriptable, Directable {
	
	protected final String KEY;
	protected final String urlPath;
	protected final String description;
	
	private final EmptyCommand emptyCommand = 
			(EmptyCommand) ClientStack.getINSTANCE().getCommands(EmptyCommand.KEY);
	private final EchoCommand echoCommand = 
			(EchoCommand) ClientStack.getINSTANCE().getCommands(EchoCommand.KEY);
	private final ErrCommand errCommand = 
			(ErrCommand) ClientStack.getINSTANCE().getCommands(ErrCommand.KEY);
	private ExitCommand exitCommand = new ExitCommand();
	private final SimpleEventBus bus;
	
	public AbstractServerStack(String key, String urlPath, String description, SimpleEventBus bus) {
		this.KEY = key;
		this.urlPath = urlPath;
		this.description = description;
		this.bus = bus;
	}

	@Override
	public String getKey() {
		return KEY;
	}
	
	@Override
	public void doCommand(CommandDTO command) {
		String args = command.getCommandArgs();
		if (args != null && !args.isEmpty()) {
			sendRequest(args);
		}
	}

	@Override
	public Commandable getCommands(CommandDTO commandDTO) {
		String args = commandDTO.getCommandArgs();
		Commandable command = null;
		if (ExitCommand.KEY.equals(args)){
			command = exitCommand;
		}
		else if ("".equals(args) || null == args) {
			command = emptyCommand;
		}
		else {
			command = this;
		}
		return command;
	}

	private void sendRequest(final String args) {
		RequestData request = createRequest(args);
		
		RequestCallback callback = new RequestCallback() {
			
			@Override
			public void onResponseReceived(Request request, Response response) {
				if (Response.SC_OK == response.getStatusCode()) { 
					String text = response.getText();
					CommandDTO command = new CommandDTO(echoCommand.getKey(), text,
							StringUtils.regroupCommandAndArgs(echoCommand.getKey(), text), 
							Source.SERVER);
					bus.fireEvent(new CommandEvent(command, echoCommand));
				}
				else {
					String text = "HTTP ("+ response.getStatusCode() +") : " 
							+ args
							+ " "
							+ response.getStatusText();
					CommandDTO command = new CommandDTO(errCommand.getKey(), text,
							StringUtils.regroupCommandAndArgs(errCommand.getKey(), text), 
							Source.SERVER);
					bus.fireEvent(new CommandEvent(command, errCommand));
				}
			}
			
			@Override
			public void onError(Request request, Throwable exception) {
				String text = exception.getMessage();
				CommandDTO command = new CommandDTO(errCommand.getKey(), text,
						StringUtils.regroupCommandAndArgs(errCommand.getKey(), text), 
						Source.SERVER);
				bus.fireEvent(new CommandEvent(command, errCommand));
			}
		};
		
		ServerCommandEvent srvCmd = new ServerCommandEvent(request, callback);
		bus.fireEvent(srvCmd);
	}
	
	@Override
	public String getDescription() {
		return description;
	}
	
	protected abstract Method getMethod();
	
	protected RequestData createRequest(String args) {
		return new RequestData(getMethod(), urlPath, args);
	}

}
