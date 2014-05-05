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
package  com.keraton.bbcmd.client.common.utils;

import com.keraton.bbcmd.client.common.utils.CommandDTO.Source;

public class StringUtils {

	public static String regroupArgs(String...args) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
		}
		return sb.toString();
	}
	
	public static String reconstructArgs(String...args) {
		String result = "";
		for (String string : args) {
			result += string + " ";
		}
		return result;
	}
	
	public static CommandDTO createCommandsArgs(String text, Source source) {
		String[] args = text.split(" ");
		String[] argsCmd = null;
		if(args.length > 0) {
			argsCmd = new String[args.length-1];
			for (int i = 1; i < args.length; i++) {
				argsCmd[i-1] = args[i];
			}
		}
		return new CommandDTO(args[0], reconstructArgs(argsCmd), text, source);
	}
	
	public static String regroupCommandAndArgs(String cmd, String args){
		return (cmd + (args == null || args.isEmpty() ? "" : " " + args)).trim();
	}
}
