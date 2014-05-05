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
package  com.keraton.bbcmd.client.command.basic;

import java.util.HashMap;
import java.util.Map;

import com.keraton.bbcmd.client.command.exception.CommandException;
import com.keraton.bbcmd.client.command.share.Commandable;
import com.keraton.bbcmd.client.command.share.Descriptable;
import com.keraton.bbcmd.client.command2ui.ExecutableRegistry;
import com.keraton.bbcmd.client.common.utils.CommandDTO;

public class LsCommand implements Commandable {
	
	public static final String KEY = "ls";
	private Map<String, Commandable> commands = new HashMap<String, Commandable>(); 

	
	public LsCommand(Map<String, Commandable> commands) {
		this.commands = commands;
	}
	
	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public void doCommand(CommandDTO commandInput) throws CommandException {
		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		for (Commandable command : commands.values()) {
			sb.append("<tr>");
			appendTD(sb, command.getKey());
			appendTD(sb, command instanceof Descriptable ? "<span class=\'desc\'> " + 
									((Descriptable)command).getDescription() + "</span>" 
									: "");
			sb.append("</tr>");
		}
		sb.append("</table>");
		ExecutableRegistry.getExecutable().print(sb.toString());
	}

	private void appendTD(StringBuilder sb, String key) {
		sb.append("<td>");
		sb.append(key);
		sb.append("</td>");
	}


}
