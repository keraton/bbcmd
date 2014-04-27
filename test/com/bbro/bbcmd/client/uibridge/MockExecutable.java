package com.bbro.bbcmd.client.uibridge;

import com.bbro.bbcmd.client.bridge.Executable;

public class MockExecutable implements Executable {
	
	private String in;
	private String path;
	private String err;

	@Override
	public void print(String in) {
		this.in = in;
	}

	@Override
	public void printPath(String path) {
		this.path = path;
	}

	@Override
	public void printErr(String err) {
		this.err = err;
	}

	/**
	 * @return the in
	 */
	public final String getIn() {
		return in;
	}

	/**
	 * @return the path
	 */
	public final String getPath() {
		return path;
	}

	/**
	 * @return the err
	 */
	public final String getErr() {
		return err;
	}

	@Override
	public void clean() {
		
	}

}
