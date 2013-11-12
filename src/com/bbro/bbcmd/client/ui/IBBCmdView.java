package com.bbro.bbcmd.client.ui;

import com.google.gwt.dom.client.Element;


public interface IBBCmdView {

	void addText(String text);
	
	void addErrText(String err);
	
	void init();
	
	String getCmdText();
	
	Element getElement();
	
	void setCmdText(String text);
	
	void setPath(String stack);
	
	void setPresenter(Presenter presenter);

	public interface Presenter {
		void init();
		void onSubmit();
		void onUp();
		void onDown();
	}

	void clean();
	
}
