package com.bbro.bbcmd.client.command.exp4j;

import com.bbro.bbcmd.client.core.CommandException;
import com.bbro.bbcmd.client.core.Commandable;
import com.bbro.bbcmd.client.utils.StringUtils;

public class Exp4jSetCommand implements Commandable {
	
	/*========== PRIVATE GRAMMAR ===========
	 * -= Grammar =-
	 * VAR OP_EQ NUM
	 * VAR 			:= alphanumeric (start with letter)
	 * OP_EQ 		:= =
	 * NUM			:= NUM
	 */
	class Var {
		String var;
	}
	
	class Equal_Operand {
	}
	
	class Num {
		double num;
	}
	
	class Expression {
		Var var;
		Equal_Operand op;
		Num num;
	}
	
	//================ We can simplify this =========
	//================ this a bit overkill ==========
	
	protected static final String KEY = "set";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getDescription() {
		return "Set variable into Exp4j context";
	}

	@Override
	public String getUsage() {
		return "set var = value (ex. x = 12)";
	}

	@Override
	public void doCommand(String... args) throws CommandException {
		/* Set interpreter
		 * Ex. var1 = 12
		 * 	   var2 = 12 13 14
		 * 	   var1 = var2
		 * ======================
		 * -= Grammar =-
		 * VAR OP_EQ NUM
		 * VAR 			:= alphanumeric (start with letter)
		 * OP_EQ 		:= =
		 * NUM			:= NUM
		 */ 
		
		// Regroup all arguments
		String text = StringUtils.regroupArgs(args);
		
		// Create token
		Exp4jTokenizer tokenizer = new Exp4jTokenizer(text);
		
		// Interprete data
		Expression exp = new Expression();
		
		// 1st get var
		if (tokenizer.hasNext()) {
			String token = tokenizer.next();
			
			// Token is letter and number but start with letter
			if(token.matches("^[a-zA-Z][a-zA-Z_0-9]*")) {
				Var var = new Var();
				var.var = token;
				exp.var = var;
			}
			else {
				throw new CommandException("var expected to start with letter");
			}
		}
		else {
			throw new CommandException("var expected");
		}
		
		// 2nd get operand
		if (tokenizer.hasNext()) {
			String token = tokenizer.next();
			
			if (token.equals("=")) {
				Equal_Operand eq = new Equal_Operand();
				exp.op = eq;
			}
			else {
				throw new CommandException(" '=' expected");
			}
		}
		else {
			throw new CommandException(" '=' expected");
		}
		
		// 3rd get num
		if (tokenizer.hasNext()) {
			String token = tokenizer.next();
			
			try {
				double num_dbl = Double.parseDouble(token);
				Num num = new Num();
				num.num = num_dbl;
				exp.num = num;
			}
			catch (NumberFormatException e) {
				throw new CommandException("numeric value expected");
			}
		}
		else {
			throw new CommandException("numeric value expected");
		}
		
		// Validate end grammar
		if (tokenizer.hasNext()) {
			throw new CommandException("End expression expected");
		}
		
		// We got expression here : Exp
		Exp4jContext.getInstance().put(exp.var.var, exp.num.num+"");
		
	}

}

