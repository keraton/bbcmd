package com.bbro.bbcmd.server.filesystem;

public interface FileDAO {

	File getFile(String path);
	
	void saveFile(File file);
	
	File createFile(String path);
	
	void deleteFile(File file);
	
}
