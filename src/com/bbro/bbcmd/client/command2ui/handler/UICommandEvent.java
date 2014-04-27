package com.bbro.bbcmd.client.command2ui.handler;

import com.google.gwt.event.shared.GwtEvent;

public class UICommandEvent extends GwtEvent<UICommandHandler> {
	
	public final static Type<UICommandHandler> TYPE = new Type<UICommandHandler>();
	
	private String text;
	
	public UICommandEvent(String text) {
		this.text = text;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<UICommandHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(UICommandHandler handler) {
		handler.onCommand(text);
	}

}
