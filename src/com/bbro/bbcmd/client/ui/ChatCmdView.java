/**
 * 
 */
package com.bbro.bbcmd.client.ui;

import com.bbro.bbcmd.client.element.ElementWrapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
@Deprecated
public class ChatCmdView extends UIObject implements IBBCmdView {

	private static BBCmdUiBinder uiBinder = GWT
			.create(BBCmdUiBinder.class);

	interface BBCmdUiBinder extends UiBinder<DivElement, ChatCmdView> {
	}
	
	private Presenter presenter;
	
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@UiField
	DivElement inputBox;
	
	@UiField
	InputElement inputCmd;
	
	@UiField
	InputElement submitButton;

	public ChatCmdView() {
		// createAndBindUi 
		setElement(uiBinder.createAndBindUi(this));
		
		ElementWrapper ew = new ElementWrapper(submitButton);
		ew.onAttach();
		
		ew.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				presenter.onSubmit();				
			}
		});
		
		ElementWrapper input = new ElementWrapper(inputCmd);
		input.onAttach();
		
		input.addKeyDownHandler(new KeyDownHandler() {
			
			@Override
			public void onKeyDown(KeyDownEvent event) {
				int key = event.getNativeKeyCode();
				if (KeyCodes.KEY_ENTER == key) {
					presenter.onSubmit();
				}
			}
		});
		
	}

	@Override
	public void addText(String text) {
		com.google.gwt.user.client.Element p = DOM.createElement("p");
		p.setInnerHTML("&gt; " + text);
		inputBox.appendChild(p);
		inputCmd.setValue(null);
	}
	
	public String getCmdText() {
		return inputCmd.getValue();
	}

	@Override
	public void init() {
		inputCmd.focus();
	}

	@Override
	public void setCmdText(String text) {
	}

	@Override
	public void setPath(String stack) {
		// TODO Auto-generated method stub
	}
	
	
}
