package com.bbro.bbcmd.shared.model;

public class SimpleTextResponse implements Responsable {

	private static final long serialVersionUID = 1L;
	
	private String text;

	public final String getText() {
		return text;
	}

	public final void setText(String text) {
		this.text = text;
	}
	

}
