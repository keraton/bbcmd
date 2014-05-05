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
package  com.keraton.bbcmd.client.init;

import com.google.gwt.event.shared.SimpleEventBus;
import com.keraton.bbcmd.client.command.basic.ErrCommand;
import com.keraton.bbcmd.client.command.share.Commandable;
import com.keraton.bbcmd.client.command.stack.AbstractServerStack;
import com.keraton.bbcmd.client.command.stack.ClientStack;
import com.keraton.bbcmd.client.command.stack.GetServerStack;
import com.keraton.bbcmd.client.command.stack.PostServerStack;
import com.keraton.bbcmd.client.command.stack.ServerStackParameter;
import com.keraton.bbcmd.client.command2ui.event.CommandEvent;
import com.keraton.bbcmd.client.common.utils.CommandDTO;
import com.keraton.bbcmd.client.common.utils.StringUtils;
import com.keraton.bbcmd.client.common.utils.CommandDTO.Source;

public class JSInitializer implements Initializer {
	
	private final SimpleEventBus bus;
	private Commandable errCommand = new ErrCommand();

	JSInitializer(SimpleEventBus bus) {
		this.bus = bus;
	}
	
	public void buildServerStack() {
		
		try {
			int commandNumber = JSInitializerUtils.getCommandsSize();
			for(int index=0; index<commandNumber; index++) {
				ServerStackParameter parameter = new ServerStackParameter();
				parameter.setBus(bus);
				parameter.setDescription(JSInitializerUtils.getDescription(index));
				parameter.setKey(JSInitializerUtils.getKey(index));
				parameter.setUrlPath(JSInitializerUtils.getUrlPath(index));
				
				AbstractServerStack serverStack = null;
				
				String defaultCommand = JSInitializerUtils.getMethod(index);
				if("POST".equalsIgnoreCase(defaultCommand)) {
					serverStack = new PostServerStack(parameter);
				}
				else {
					// Default server stack => Get
					serverStack = new GetServerStack(parameter);
				}
				
				ClientStack.getINSTANCE().addCommand(serverStack);
				
			}
		}
		catch(Exception exception) {
			String text = exception.getMessage();
			CommandDTO command = new CommandDTO(errCommand.getKey(), text,
					StringUtils.regroupCommandAndArgs(errCommand.getKey(), text), 
					Source.SERVER);
			bus.fireEvent(new CommandEvent(command, errCommand));
		}
		
	}
	

	@Override
	public InitInfo getInitInfo() {
		
		InitInfo initInfo = new InitInfo();
		initInfo.setUseBody(JSInitializerUtils.isUseBody());
		initInfo.setDomId(JSInitializerUtils.getId());
		initInfo.setHeight(JSInitializerUtils.getHeight());
		initInfo.setWidth(JSInitializerUtils.getWidth());
		
		return initInfo;
	}
	
	private static class JSInitializerUtils {
	
		private static native String getId() /*-{
			if ($wnd.bbcmdObj && $wnd.bbcmdObj.id) {
				return $wnd.bbcmdObj.id;
			}
			return null;
		}-*/;
		
		private static native int getWidth() /*-{
			if ($wnd.bbcmdObj && $wnd.bbcmdObj.width) {
				return $wnd.bbcmdObj.width;
			}
			return 600;
		}-*/;
		
		private static native int getHeight() /*-{
			if ($wnd.bbcmdObj && $wnd.bbcmdObj.height) {
				return $wnd.bbcmdObj.height;
			}
			return 400;
		}-*/;
		
		private static native boolean isUseBody() /*-{
			if ($wnd.bbcmdObj && $wnd.bbcmdObj.useBody) {
				return $wnd.bbcmdObj.useBody;
			}
			return false;
		}-*/;
		
		private static native String getDefaultCommand() /*-{
			if ($wnd.bbcmdObj && $wnd.bbcmdObj.defaultCmd) {
				return $wnd.bbcmdObj.defaultCmd;
			}
			return null;
		}-*/;
		
		// =========== COMMAND INFO
		
		private static native int getCommandsSize()  /*-{
			if ($wnd.bbcmdObj && $wnd.bbcmdObj.commands) {
				return $wnd.bbcmdObj.commands.length;
			}
			return 0;
		}-*/;
		
		private static native String getKey(int index) /*-{
			if ($wnd.bbcmdObj.commands[index].key)
				return $wnd.bbcmdObj.commands[index].key;
			return null;
		}-*/;
		
		private static native String getUrlPath(int index) /*-{
			if ($wnd.bbcmdObj.commands[index].urlPath)
				return $wnd.bbcmdObj.commands[index].urlPath;
			return null;
		}-*/;
		
		private static native String getDescription(int index) /*-{
			if ($wnd.bbcmdObj.commands[index].description)
				return $wnd.bbcmdObj.commands[index].description;
			return null;
		}-*/;
		
		private static native String getMethod(int index) /*-{
			if ($wnd.bbcmdObj.commands[index].method)
				return $wnd.bbcmdObj.commands[index].method;
			return null;
		}-*/;
	}

}
