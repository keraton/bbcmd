package com.bbro.bbcmd.client.command2ui.event;

import com.bbro.bbcmd.client.command.Commandable;
import com.google.gwt.event.shared.GwtEvent;

public class CommandEvent extends GwtEvent<CommandHandler> {
	
	public final static Type<CommandHandler> TYPE = new Type<CommandHandler>();
	
	private String text;
	private Commandable commandable;
	
	public CommandEvent(String text, Commandable commandable) {
		this.text = text;
		this.commandable = commandable;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CommandHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CommandHandler handler) {
		handler.onCommand(this);
	}

	public final String getText() {
		return text;
	}

	public final Commandable getCommandable() {
		return commandable;
	}

}
