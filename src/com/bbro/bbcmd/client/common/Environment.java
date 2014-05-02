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
package  com.bbro.bbcmd.client.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Environment {

	private static Environment instance = new Environment();

	public static Environment getInstance(){
		return instance;
	}
	
	private Environment(){
		envMaps = new HashMap<String, String>();
		envMaps.put("USER_AGENT", getUserAgent());
	}
	
	private Map<String, String> envMaps;
	
	public void putEnv(String key, String value){
		envMaps.put(key, value);
	}
	
	public String getEnv(String key){
		return envMaps.get(key);
	}
	
	public Set<String> getKeyEnv(){
		return envMaps.keySet();
	}
	
	public static native String getUserAgent() /*-{
	return navigator.userAgent.toLowerCase();
	}-*/;
}
