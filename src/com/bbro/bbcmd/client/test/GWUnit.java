package com.bbro.bbcmd.client.test;

import com.bbro.bbcmd.client.test.event.TestEvent;
import com.bbro.bbcmd.client.test.event.TestHandler;
import com.google.gwt.event.shared.SimpleEventBus;

public abstract class GWUnit implements TestHandler {
	
	private final SimpleEventBus bus;

	public GWUnit(SimpleEventBus bus) {
		this.bus = bus;
	}

	public void onTest(TestEvent event) {
		Info info = new Info();
		before();
		try {
			test(info);
		}
		catch(Exception e) {
			// TODO ex. bus.fire... 
		}
		after();
	}
	
	protected abstract void test(Info info);

	protected void before() {}
	protected void after() {}
	
}
