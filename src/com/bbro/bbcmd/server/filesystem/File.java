package com.bbro.bbcmd.server.filesystem;

import java.io.Serializable;
import java.util.Date;

public interface File extends Serializable {

	String getName();
	
	void setName(String name);
	
	Date getCreationDate();
	
	void setCreationDate(Date date);
	
	Date getModificationDate();
	
	void setModificationDate(Date date);
	
	String getPath();
	
	void setPath(String path);
	
	String getContent();
	
	void setContent(String content);
	
	String getOwner();
	
	void setOwner(String owner);
	
}
