package com.bbro.bbcmd.client.ui.event;

import com.google.gwt.event.shared.GwtEvent;

public class CommandErrReturnEvent extends GwtEvent<CommandErrReturnHandler> {
	
	public final static Type<CommandErrReturnHandler> TYPE = new Type<CommandErrReturnHandler>();
	
	private String text;
	
	public CommandErrReturnEvent(String text){
		this.text = text;
	}
	
	public CommandErrReturnEvent(String text, String newStack){
		this.text = text;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CommandErrReturnHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CommandErrReturnHandler handler) {
		handler.onRetour(text);
	}


}
