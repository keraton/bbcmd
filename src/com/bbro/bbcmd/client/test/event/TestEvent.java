package com.bbro.bbcmd.client.test.event;

import com.google.gwt.event.shared.GwtEvent;

public class TestEvent extends GwtEvent<TestHandler> {
	
	public final static Type<TestHandler> TYPE = new Type<TestHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<TestHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(TestHandler handler) {
		handler.onTest(this);
	}

}
