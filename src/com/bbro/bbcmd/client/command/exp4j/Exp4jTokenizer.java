package com.bbro.bbcmd.client.command.exp4j;

import java.util.Iterator;


public class Exp4jTokenizer implements Iterator<String>{
	
	private final char[] keyChars = {' ', '='};
	
	private String text;
	
	private int pos = 0;
	
	public Exp4jTokenizer(String text) {
		if (text == null) {
			throw new IllegalArgumentException("Ex4jTokenizer text should be not null");
		}
		this.text = text;
	}
	
	@Override
	public boolean hasNext() {
		return pos < text.length();
	}

	@Override
	public String next() {
		StringBuilder nextToken = new StringBuilder();
		
		// Eliminate white spaces before token
		gotoNextNonWhiteSpaceChar();
		
		if (text.charAt(pos) == '=') {
			nextToken.append('=');
			pos++;
		}
		else {
			char c;
			
			while (pos < text.length() && isNotKeyWords(text.charAt(pos))) {
	
				c = text.charAt(pos);
				nextToken.append(c);
				pos++;
				
			} 
		}
		
		return nextToken.toString();
	}

	private boolean isNotKeyWords(char c) {
		for (int i = 0; i < keyChars.length; i++) {
			if (c == keyChars[i]) {
				return false;
			}
		}
		return true;
	}

	private void gotoNextNonWhiteSpaceChar() {
		while (pos < text.length() && text.charAt(pos) == ' ' ) {
			pos++;
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Ex4jTokenizer should not be called");
	}

}
