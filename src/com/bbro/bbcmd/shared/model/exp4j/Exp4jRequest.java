package com.bbro.bbcmd.shared.model.exp4j;

import java.util.HashMap;

import com.bbro.bbcmd.shared.model.Requestable;

public class Exp4jRequest implements Requestable {

	private static final long serialVersionUID = 1L;
	
	private String eval;
	
	private HashMap<String, Double> context;

	/**
	 * @return the eval
	 */
	public final String getEval() {
		return eval;
	}

	/**
	 * @param eval the eval to set
	 */
	public final void setEval(String eval) {
		this.eval = eval;
	}

	/**
	 * @return the context
	 */
	public final HashMap<String, Double> getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public final void setContext(HashMap<String, Double> context) {
		this.context = context;
	}

}
