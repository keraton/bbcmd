package com.bbro.bbcmd.client.uibridge;

public interface Executable {
	
	void print(String in);
	
	void printPath(String path);
	
	void printErr(String err);
	
	void clean();

}
