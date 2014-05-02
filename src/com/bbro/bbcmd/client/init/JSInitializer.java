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
package  com.bbro.bbcmd.client.init;

import com.bbro.bbcmd.client.command.basic.ErrCommand;
import com.bbro.bbcmd.client.command.share.Commandable;
import com.bbro.bbcmd.client.command.stack.ClientStack;
import com.bbro.bbcmd.client.command.stack.GetServerStack;
import com.bbro.bbcmd.client.command.stack.GetServerStackParameter;
import com.bbro.bbcmd.client.command2ui.event.CommandEvent;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.SimpleEventBus;

public class JSInitializer implements Initializer {
	
	private final SimpleEventBus bus;
	private Commandable errCommand = new ErrCommand();

	JSInitializer(SimpleEventBus bus) {
		this.bus = bus;
	}
	
	public void buildServerStack() {
		
//		try {
			GWT.log("****");
			int commandNumber = JSInitializerUtils.getCommandsSize();
			GWT.log(""+ commandNumber);
			for(int i=0; i<commandNumber; i++) {
				GetServerStackParameter parameter = new GetServerStackParameter();
				parameter.setBus(bus);
				parameter.setDescription(JSInitializerUtils.getDescription(i));
				parameter.setKey(JSInitializerUtils.getKey(i));
				parameter.setUrlPath(JSInitializerUtils.getUrlPath(i));
				
				GetServerStack getServerStack = new GetServerStack(parameter);
				ClientStack.getINSTANCE().addCommand(getServerStack);
				
			}
//		}
//		catch(Exception exception) {
//			GWT.log(exception.getMessage(), exception);
//			bus.fireEvent(new CommandEvent(exception.getMessage(), errCommand));
//		}
		
	}
	

	@Override
	public InitInfo getInitInfo() {
		
		InitInfo initInfo = new InitInfo();
		initInfo.setUseBody(JSInitializerUtils.isUseBody());
		initInfo.setDomId(JSInitializerUtils.getId());
		initInfo.setHeight(JSInitializerUtils.getHeight());
		initInfo.setWidth(JSInitializerUtils.getWidth());
		
		GWT.log("Info OK");
		
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
	}

}
