package com.bbro.bbcmd.client.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void test() {
		assertEquals("123", StringUtils.regroupArgs("1","2","3"));
		
		assertEquals("12", StringUtils.regroupArgs("12"));
	}

}
