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
package  com.keraton.bbcmd.client.servercaller;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.keraton.bbcmd.client.servercaller.data.RequestData;

public class HTTPServerCallerImpl implements ServerCaller {

	@Override
	public void send(RequestData requestData, final RequestCallback callback) {
		
		RequestBuilder builder = new RequestBuilder(requestData.getMethod(), 
				URL.encode(requestData.getPath()));

		try {
			GWT.log(requestData.getRequest());
			builder.sendRequest(requestData.getRequest(), new RequestCallback() {

				@Override
				public void onResponseReceived(Request request,
						Response response) {
					callback.onResponseReceived(request, response);
				}

				@Override
				public void onError(Request request, Throwable exception) {
					callback.onError(request, exception);
				}
			  });
		} catch (com.google.gwt.http.client.RequestException e) {
			callback.onError(null, e);
		}

	}

}
