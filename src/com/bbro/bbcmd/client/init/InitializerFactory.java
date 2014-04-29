package com.bbro.bbcmd.client.init;

import com.google.gwt.event.shared.SimpleEventBus;

public class InitializerFactory {
	
	private static InitializerFactory instance = new InitializerFactory();
	private JSInitializer initializer;
	
	public static InitializerFactory getInstance() {
		return instance;
	}
	
	private InitializerFactory() {
	}
	
	public void init(SimpleEventBus bus) {
		if (null == initializer) {
			initializer = new JSInitializer(bus);
			initializer.buildServerStack();
		}
	}

}
