package com.bbro.bbcmd.shared.model;

public class SimpleTextRequest implements Requestable {

	private static final long serialVersionUID = 1L;

	private String text;

	public final String getText() {
		return text;
	}

	public final void setText(String text) {
		this.text = text;
	}
	
}
