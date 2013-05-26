package com.bbro.bbcmd.client.ui.handler;

import com.google.gwt.event.shared.GwtEvent;

public class PathChangeEvent extends GwtEvent<PathChangeHandler> {
	
	public final static Type<PathChangeHandler> TYPE = new Type<PathChangeHandler>();
	
	private String newPath;
	
	public PathChangeEvent(String newPath){
		this.newPath = newPath;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<PathChangeHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PathChangeHandler handler) {
		handler.onPathChange(newPath);
	}

}
