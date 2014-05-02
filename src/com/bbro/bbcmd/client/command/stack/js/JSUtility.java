package com.bbro.bbcmd.client.command.stack.js;

import com.bbro.bbcmd.client.command2ui.ExecutableRegistry;

public class JSUtility {

    public static void printGWT(String toPrint) { 
    	ExecutableRegistry.getExecutable().print(toPrint);
    }
    
    public static native void exportStaticMethod() /*-{
		$wnd.printGWT = $entry(@com.bbro.bbcmd.client.command.stack.js.JSUtility::printGWT(Ljava/lang/String;));
	}-*/;

}
