package com.bbro.bbcmd.client.command.exp4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.bbro.bbcmd.client.core.CommandException;

public class Exp4jSetCommandTest {
	
	
	Exp4jSetCommand exp4jSetCommand = null;
	
	@Before
	public void before() {
		exp4jSetCommand = new Exp4jSetCommand();
	}

	@Test
	public void testOK1() throws CommandException {
		exp4jSetCommand.doCommand("v=12");
		String result = Exp4jContext.getInstance().get("v");
		assertEquals("12.0", result);
	}
	
	@Test
	public void testOK2() throws CommandException {
		exp4jSetCommand.doCommand("x12","=", "13");
		String result = Exp4jContext.getInstance().get("x12");
		assertEquals("13.0", result);
	}
	
	@Test(expected=CommandException.class)
	public void testKO1() throws CommandException {
		exp4jSetCommand.doCommand("1x = 0");
		Exp4jContext.getInstance().get("x12");
		fail();
	}
	
	@Test(expected=CommandException.class)
	public void testKO2() throws CommandException {
		exp4jSetCommand.doCommand("v < 0");
		Exp4jContext.getInstance().get("x12");
		fail();
	}
	
	@Test(expected=CommandException.class)
	public void testKO3() throws CommandException {
		exp4jSetCommand.doCommand("v =! 0");
		Exp4jContext.getInstance().get("x12");
		fail();
	}
	
	@Test(expected=CommandException.class)
	public void testKO4() throws CommandException {
		exp4jSetCommand.doCommand("v = a");
		Exp4jContext.getInstance().get("x12");
		fail();	
	}

}
