/**
 * 
 */
package com.bbro.bbcmd.client.command.exp4j;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author yanti_liam
 *
 */
public class Exp4jTokenizerTest {

	@Test
	public void test() {
		
		// Test simple
		Exp4jTokenizer tokenizer = new Exp4jTokenizer("var1 = 12");
		
		assertTrue(tokenizer.hasNext());
		assertEquals("var1", tokenizer.next());
		
		assertTrue(tokenizer.hasNext());
		assertEquals("=", tokenizer.next());
		
		assertTrue(tokenizer.hasNext());
		assertEquals("12", tokenizer.next());
		
		assertTrue(!tokenizer.hasNext());
		
		// Test simple
		tokenizer = new Exp4jTokenizer("var1=12");
		
		assertTrue(tokenizer.hasNext());
		assertEquals("var1", tokenizer.next());
		
		assertTrue(tokenizer.hasNext());
		assertEquals("=", tokenizer.next());
		
		assertTrue(tokenizer.hasNext());
		assertEquals("12", tokenizer.next());
		
		assertTrue(!tokenizer.hasNext());
		
		// Test simple
		tokenizer = new Exp4jTokenizer("var1= 12");
		
		assertTrue(tokenizer.hasNext());
		assertEquals("var1", tokenizer.next());
		
		assertTrue(tokenizer.hasNext());
		assertEquals("=", tokenizer.next());
		
		assertTrue(tokenizer.hasNext());
		assertEquals("12", tokenizer.next());
		
		assertTrue(!tokenizer.hasNext());
	}

}
