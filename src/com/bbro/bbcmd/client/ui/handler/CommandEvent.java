package com.bbro.bbcmd.client.ui.handler;

import com.google.gwt.event.shared.GwtEvent;

public class CommandEvent extends GwtEvent<CommandHandler> {
	
	public final static Type<CommandHandler> TYPE = new Type<CommandHandler>();
	
	private String text;
	
	public CommandEvent(String text) {
		this.text = text;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CommandHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CommandHandler handler) {
		handler.onCommand(text);
	}

}
