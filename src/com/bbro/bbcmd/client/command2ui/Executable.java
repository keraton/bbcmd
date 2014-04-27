package com.bbro.bbcmd.client.command2ui;

public interface Executable {
	
	void print(String in);
	
	void printPath(String path);
	
	void printErr(String err);
	
	void clean();

}
