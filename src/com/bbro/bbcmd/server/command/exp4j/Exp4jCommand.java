package com.bbro.bbcmd.server.command.exp4j;

import java.util.Set;

import javax.servlet.http.HttpSession;

import com.bbro.bbcmd.server.core.CommandableServer;
import com.bbro.bbcmd.shared.model.Requestable;
import com.bbro.bbcmd.shared.model.Responsable;
import com.bbro.bbcmd.shared.model.SimpleTextResponse;
import com.bbro.bbcmd.shared.model.exp4j.Exp4jRequest;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

public class Exp4jCommand implements CommandableServer {

	@Override
	public Responsable doCommand(HttpSession session, Requestable request) {
		Exp4jRequest str = (Exp4jRequest) request;
		SimpleTextResponse resp = new SimpleTextResponse();
		
		ExpressionBuilder exp = new ExpressionBuilder(str.getEval());
		try {
			exp.withVariables(str.getContext());
			
			Calculable build = exp.build();
			Set<String> keys = str.getContext().keySet();
			
			for (String key : keys) {
				build.setVariable(key, str.getContext().get(key));
			}
			double calc = build.calculate();
			resp.setText(calc + "");
		} catch (UnknownFunctionException e) {
			resp.setText(e.getMessage());
		} catch (UnparsableExpressionException e) {
			resp.setText(e.getMessage());
		}
		
		return resp;
	}

}
