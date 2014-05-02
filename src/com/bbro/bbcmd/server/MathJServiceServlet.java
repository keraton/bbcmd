package com.bbro.bbcmd.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.keraton.mathj.MathJ;
import org.keraton.mathj.context.Context;
import org.keraton.mathj.context.impl.MapContext;
import org.keraton.mathj.func.Function;
import org.keraton.mathj.reader.FunctionReader;
import org.keraton.mathj.reader.SyntaxException;
import org.keraton.mathj.reader.ValidationReaderException;
import org.keraton.mathj.reader.impl.FunctionStringReader;
import org.keraton.mathj.reader.impl.Lexeme;
import org.keraton.mathj.reader.impl.Lexeme.Type;
import org.keraton.mathj.reader.impl.Lexer;

import com.google.gwt.http.client.URL;

@SuppressWarnings("serial")
public class MathJServiceServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String value = (String) req.getParameter("value");
		//+ => %252B
		// / => %252F
		System.out.println("before : " + value);
		value = value.replaceAll("%2B", "+");
		value = value.replaceAll("%2F", "/");
		value = value.replaceAll("%3D", "=");
		System.out.println("before : " + value);
		
		FunctionReader reader = new FunctionStringReader();
		try {
			Context context = getContext(req);
			
			MathJ mathJ = new MathJ(context);
			
			if (value.indexOf("=") > -1) {
				treatSet(resp, value, reader, context, mathJ);
			}
			else {
				System.out.println(value);
				Function func = reader.read(value.trim());
				resp.getWriter().write("" + mathJ.apply(func).value());
			}
		} catch (SyntaxException e) {
			resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, e.getMessage());
		} catch (ValidationReaderException e) {
			resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, e.getMessage());
		}
	}

	private Context getContext(HttpServletRequest req) {
		if (null == req.getSession().getAttribute("context")) {
			Context context = new MapContext();
			req.getSession().setAttribute("context", context);
		}
		Context context = (Context) req.getSession().getAttribute("context");
		return context;
	}

	private void treatSet(HttpServletResponse resp, String value,
			FunctionReader reader, Context context, MathJ mathJ)
			throws IOException, ValidationReaderException, SyntaxException {
		String[] values = value.split("=");
		if (values.length > 2 || values.length < 2 ) {
			resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Format error more than two '=' ");
		}
		else {
			String variable = values[0].trim();
			String equation = values[1].trim();
			
			Lexer lex = new Lexer(variable);
			Lexeme lexeme = lex.nextLexeme();
			if (lexeme.getType() == Type.VAR) {
				context.setContext(lexeme.getToken(), reader.read(equation));
			}
			else if (lexeme.getType() == Type.CONST) {
				int expected = Integer.parseInt(variable);
				int result = mathJ.apply(reader.read(equation)).value();
				if(result == expected) {
					resp.getWriter().write("true");
				}
				else {
					resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Expected = " +expected 
							+ ", but result =" + result);
				}
			}
			else {
				resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Format incorrect");
			}
		}
	}

	
}