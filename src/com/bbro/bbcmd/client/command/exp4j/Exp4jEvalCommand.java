package com.bbro.bbcmd.client.command.exp4j;

import java.util.HashMap;
import java.util.Set;

import com.bbro.bbcmd.client.core.CommandException;
import com.bbro.bbcmd.client.core.CommandServerServiceAsync;
import com.bbro.bbcmd.client.core.Commandable;
import com.bbro.bbcmd.client.uibridge.ExecutableRegistry;
import com.bbro.bbcmd.client.utils.StringUtils;
import com.bbro.bbcmd.shared.model.Responsable;
import com.bbro.bbcmd.shared.model.SimpleTextRequest;
import com.bbro.bbcmd.shared.model.SimpleTextResponse;
import com.bbro.bbcmd.shared.model.exp4j.Exp4jRequest;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class Exp4jEvalCommand implements Commandable {
	
	protected static final String KEY = "eval";
	
	protected CommandServerServiceAsync commandServerService;// = GWT.create(CommandServerService.class);

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getDescription() {
		return "Evaluate a mathematical expression with exp4j";
	}

	@Override
	public String getUsage() {
		return "eval expression";
	}

	@Override
	public void doCommand(String... args) throws CommandException {
		
		Exp4jRequest req = new Exp4jRequest();
		String eval = StringUtils.regroupArgs(args);
		req.setEval(eval);
		req.setContext(new HashMap<String, Double>());
		Set<String> keys = Exp4jContext.getInstance().keySet();
		for (String key : keys) {
			GWT.log("key"+key);
			req.getContext().put(key, Double.valueOf(Exp4jContext.getInstance().get(key)));
		}
		
		commandServerService.execute(Exp4jEvalCommand.class.getName(), req, new AsyncCallback<Responsable>() {
			
			@Override
			public void onSuccess(Responsable result) {
				SimpleTextResponse resp = (SimpleTextResponse) result;
				ExecutableRegistry.getExecutable().print(resp.getText());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				ExecutableRegistry.getExecutable().printErr(caught.getMessage());
			}
		});

	}

}
