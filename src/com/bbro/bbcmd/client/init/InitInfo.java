package com.bbro.bbcmd.client.init;

public class InitInfo {
	
	private int width;
	private int height;
	private String domId;
	private boolean useBody;
	
	public final int getWidth() {
		return width;
	}
	public final void setWidth(int width) {
		this.width = width;
	}
	
	public final int getHeight() {
		return height;
	}
	
	public final void setHeight(int height) {
		this.height = height;
	}
	
	public final String getDomId() {
		return domId;
	}

	public final void setDomId(String domId) {
		this.domId = domId;
	}
	
	public final boolean isUseBody() {
		return useBody;
	}
	
	public final void setUseBody(boolean useBody) {
		this.useBody = useBody;
	}
	
}
