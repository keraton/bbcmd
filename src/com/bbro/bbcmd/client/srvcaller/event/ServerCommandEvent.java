package com.bbro.bbcmd.client.srvcaller.event;

import com.bbro.bbcmd.client.srvcaller.data.RequestData;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.http.client.RequestCallback;

public class ServerCommandEvent extends GwtEvent<ServerCommandHandler> {
	
	public final static Type<ServerCommandHandler> TYPE = new Type<ServerCommandHandler>();
	
	private final RequestData data;
	private final RequestCallback callback;
	
	public ServerCommandEvent(RequestData data, RequestCallback callback) {
		this.data = data;
		this.callback = callback;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ServerCommandHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ServerCommandHandler handler) {
		handler.onCommand(this);
	}

	public final RequestData getData() {
		return data;
	}

	public final RequestCallback getCallback() {
		return callback;
	}

}
