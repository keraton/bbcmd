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
package com.keraton.bbcmd.client.ui;

import com.google.gwt.event.shared.SimpleEventBus;
import com.keraton.bbcmd.client.command2ui.event.UICommandEvent;
import com.keraton.bbcmd.client.common.utils.StringUtils;
import com.keraton.bbcmd.client.common.utils.CommandDTO.Source;


public class BBCmdJSInput {
	
	private SimpleEventBus bus;
	
	private BBCmdJSInput(SimpleEventBus bus){
		this.bus = bus;
		exportStaticMethod();
	}
	
	private static BBCmdJSInput instance;
	
	public static void init(SimpleEventBus bus){
		instance = new BBCmdJSInput(bus);
	}
	
	public static void call(String commandAndArgs) {
    	instance.bus.fireEvent(new UICommandEvent(StringUtils.createCommandsArgs(commandAndArgs, Source.JS)));
    }
	
    public static native void exportStaticMethod() /*-{
		$wnd.call = $entry(@com.keraton.bbcmd.client.ui.BBCmdJSInput::call(Ljava/lang/String;));
	}-*/;

}
