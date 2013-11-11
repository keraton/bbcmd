package com.bbro.bbcmd.client.command.exp4j;

import static org.junit.Assert.*;

import org.junit.Test;

public class Exp4jContextTest {
	

	@Test
	public void testGetPut() {
		Exp4jContext.getInstance().put("v","12");
		assertEquals("12", Exp4jContext.getInstance().get("v"));
		
		assertNull(Exp4jContext.getInstance().get("x"));
	}

}
