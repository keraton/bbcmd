package com.bbro.bbcmd.client.init;

import com.google.gwt.event.shared.SimpleEventBus;

public class InitializerFactory {
	
	private static InitializerFactory instance = new InitializerFactory();
	private Initializer initializer;
	
	public static InitializerFactory getInstance() {
		return instance;
	}
	
	private InitializerFactory() {
	}
	
	public InitInfo init(SimpleEventBus bus) {
		if (null == initializer) {
			initializer = new JSInitializer(bus);
		}
		return initializer.getInitInfo();
	}
	
	public void buildServerStack() {
		initializer.buildServerStack();
	}

}
