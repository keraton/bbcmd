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

import java.util.Set;

import com.keraton.bbcmd.client.command.exception.IllegalOptionCommandException;
import com.keraton.bbcmd.client.command.share.Commandable;
import com.keraton.bbcmd.client.command2ui.ExecutableRegistry;
import com.keraton.bbcmd.client.common.Environment;

public class EnvCommand implements Commandable {
	
	public static String KEY = "env";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public void doCommand(String textInput) throws IllegalOptionCommandException {
		Environment environment = Environment.getInstance();
		String[] args = textInput.split(" ");
		
		if (textInput.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			Set<String> envs = environment.getKeyEnv();
			
			for (String key : envs) {
				sb.append(key);
				sb.append("=");
				sb.append(environment.getEnv(key));
				sb.append("<br/>");
			}
			ExecutableRegistry.getExecutable().print(sb.toString());
		}
		else if (args.length == 1){
			String text = args[0];
			if (text.indexOf("=") <1) {
				throw new IllegalOptionCommandException();
			}
			String[] valueKey = text.split("=");
			if (valueKey.length != 2) {
				throw new IllegalOptionCommandException();
			}
			
			String key = valueKey[0];
			String value = valueKey[1];
			environment.putEnv(key, value);
			
		}
		else {
			throw new IllegalOptionCommandException();
		}
	}

}
