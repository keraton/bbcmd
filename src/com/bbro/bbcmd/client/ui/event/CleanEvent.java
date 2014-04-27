package com.bbro.bbcmd.client.ui.event;

import com.google.gwt.event.shared.GwtEvent;

public class CleanEvent extends GwtEvent<CleanHandler> {
	
	public final static Type<CleanHandler> TYPE = new Type<CleanHandler>();
	
	
	public CleanEvent(){
	}
	

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CleanHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CleanHandler handler) {
		handler.onClean();
	}


}
