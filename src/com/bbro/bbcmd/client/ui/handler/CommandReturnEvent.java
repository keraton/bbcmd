package com.bbro.bbcmd.client.ui.handler;

import com.google.gwt.event.shared.GwtEvent;

public class CommandReturnEvent extends GwtEvent<CommandReturnHandler> {
	
	public final static Type<CommandReturnHandler> TYPE = new Type<CommandReturnHandler>();
	
	private String text;
	
	public CommandReturnEvent(String text){
		this.text = text;
	}
	
	public CommandReturnEvent(String text, String newStack){
		this.text = text;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CommandReturnHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CommandReturnHandler handler) {
		handler.onRetour(text);
	}


}
