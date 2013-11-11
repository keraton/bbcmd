package com.bbro.bbcmd.client.command.exp4j;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bbro.bbcmd.client.MockCommandServerServiceAsync;
import com.bbro.bbcmd.client.core.CommandException;
import com.bbro.bbcmd.client.uibridge.ExecutableRegistry;
import com.bbro.bbcmd.client.uibridge.ExecutableRegistryMockInit;
import com.bbro.bbcmd.client.uibridge.MockExecutable;
import com.bbro.bbcmd.shared.model.Responsable;
import com.bbro.bbcmd.shared.model.SimpleTextRequest;
import com.bbro.bbcmd.shared.model.SimpleTextResponse;
import com.bbro.bbcmd.shared.model.exp4j.Exp4jRequest;

public class Exp4jEvalCommandTest {
	
	private Exp4jEvalCommand evalCommand;
	
	@Before
	public void before() {
		ExecutableRegistryMockInit.init();
		evalCommand = new Exp4jEvalCommand();
	}

	@Test
	public void testDoCommandSuccess() throws CommandException {
		SimpleTextResponse responsable = new SimpleTextResponse();
		responsable.setText("reponds");
		MockCommandServerServiceAsync mockAsync = new MockCommandServerServiceAsync(true, responsable, null);
		evalCommand.commandServerService = mockAsync;
		evalCommand.doCommand("1","2","3");
		
		assertEquals("123", ((Exp4jRequest) mockAsync.getRequestable()).getEval());
		assertEquals(Exp4jEvalCommand.class.getName(), mockAsync.getPath());
		
		MockExecutable executable = (MockExecutable)ExecutableRegistry.getExecutable();
		assertEquals("reponds", executable.getIn());
	}
	
	@Test
	public void testDoCommandFailure() throws CommandException {
		SimpleTextResponse responsable = new SimpleTextResponse();
		responsable.setText("reponds");
		MockCommandServerServiceAsync mockAsync = new MockCommandServerServiceAsync(false, null, new Throwable("err"));
		evalCommand.commandServerService = mockAsync;
		evalCommand.doCommand("1","2","3");
		
		assertEquals("123", ((Exp4jRequest) mockAsync.getRequestable()).getEval());
		assertEquals(Exp4jEvalCommand.class.getName(), mockAsync.getPath());
		
		MockExecutable executable = (MockExecutable)ExecutableRegistry.getExecutable();
		assertEquals("err", executable.getErr());
	}

}
