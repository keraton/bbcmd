/**
 * 
 */
package com.bbro.bbcmd.client.ui;

import com.bbro.bbcmd.client.element.ElementWrapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.UIObject;

/**
 * @author yanti_liam
 *
 */
public class BasicCmdView extends UIObject implements IBBCmdView {

	private static BBCmdUiBinder uiBinder = GWT
			.create(BBCmdUiBinder.class);

	interface BBCmdUiBinder extends UiBinder<DivElement, BasicCmdView> {
	}
	
	private Presenter presenter;
	
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@UiField
	DivElement inputBox;
	
	@UiField
	Element inputCmd;
	
	@UiField
	Element inputStack;
	

	public BasicCmdView() {
		// createAndBindUi 
		setElement(uiBinder.createAndBindUi(this));
		
		ElementWrapper input = new ElementWrapper(inputCmd);
		input.onAttach();
		
		input.addKeyDownHandler(new KeyDownHandler() {
			
			@Override
			public void onKeyDown(KeyDownEvent event) {
				int key = event.getNativeKeyCode();
				if (KeyCodes.KEY_ENTER == key) {
					presenter.onSubmit();
				}
				else if (KeyCodes.KEY_UP == key){
					presenter.onUp();
				}
				else if (KeyCodes.KEY_DOWN == key){
					presenter.onDown();
				}
				
			}
		});
		
	}

	@Override
	public void addText(String text) {
		com.google.gwt.user.client.Element p = DOM.createElement("p");
		p.setInnerHTML(text);
		inputBox.appendChild(p);
		inputCmd.setInnerText(null);
	}
	
	@Override
	public String getCmdText() {
		return inputCmd.getInnerText();
	}

	@Override
	public void init() {
		inputCmd.focus();
	}
	
	@Override
	public void setCmdText(String text){
		inputCmd.setInnerText(text);
	}

	@Override
	public void setPath(String stack) {
		inputStack.setInnerText(stack);
	}

	@Override
	public void addErrText(String err) {
		com.google.gwt.user.client.Element p = DOM.createElement("p");
		p.addClassName("err");
		p.setInnerHTML(err);
		inputBox.appendChild(p);
		inputCmd.setInnerText(null);
	}
	
	
}
