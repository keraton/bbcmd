package com.bbro.bbcmd.client.bridge.handler;

import com.google.gwt.event.shared.GwtEvent;

public class UIFindCommandEvent extends GwtEvent<UIFindCommandHandler> {
	
	public final static Type<UIFindCommandHandler> TYPE = new Type<UIFindCommandHandler>();
	
	private final String text;
	private final int counter;
	
	public UIFindCommandEvent(String text, int counter) {
		this.text = text;
		this.counter = counter;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<UIFindCommandHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(UIFindCommandHandler handler) {
		handler.onCommand(this);
	}

	public final String getText() {
		return text;
	}

	public final int getCounter() {
		return counter;
	}

}
