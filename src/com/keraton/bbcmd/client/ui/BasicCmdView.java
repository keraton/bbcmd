/**
 * 
 */
/*
 *	 The MIT License (MIT)
 *	
 *	Copyright (c) [2014] [bowie.brotosumpeno]
 *	
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *	
 *	The above copyright notice and this permission notice shall be included in all
 *	copies or substantial portions of the Software.
 *	
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *	SOFTWARE.
 */
package  com.keraton.bbcmd.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.UIObject;
import com.keraton.bbcmd.client.ui.element.ElementWrapper;

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
	private String cmd;
	
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@UiField
	DivElement inputBox;
	
	@UiField
	Element inputCmd, boxCmd, inputStack, inputSymbol;
	

	public BasicCmdView() {
		// createAndBindUi 
		setElement(uiBinder.createAndBindUi(this));
		
		// init symbol
		inputSymbol.setInnerHTML("$");
		
		ElementWrapper input = new ElementWrapper(inputCmd);
		input.onAttach();
		
		ElementWrapper inputBox = new ElementWrapper(boxCmd);
		inputBox.onAttach();
		
		input.addKeyDownHandler(new KeyDownHandler() {
			
			@Override
			public void onKeyDown(KeyDownEvent event) {
				onInputKeyDownHandler(event);
			}

		});
		
		inputBox.addKeyDownHandler(new KeyDownHandler() {
			
			@Override
			public void onKeyDown(KeyDownEvent event) {
				onEditKeyDown(event);
			}

		});
		
	}
	
	private void onEditKeyDown(KeyDownEvent event) {
		int key = event.getNativeKeyCode();
		if (KeyCodes.KEY_ENTER == key) {
			if (event.isControlKeyDown()) {
				focusOnInputMode();
				presenter.onSubmit();
			}
		}
	}
	
	private void focusOnInputMode() {
		cmd = boxCmd.getInnerText();
		boxCmd.getStyle().setVisibility(Visibility.HIDDEN);
		inputCmd.getStyle().setVisibility(Visibility.VISIBLE);
		inputCmd.focus();
		inputCmd.setInnerText(boxCmd.getInnerHTML());
		boxCmd.setInnerText("");
	}

	private void onInputKeyDownHandler(KeyDownEvent event) {
		int key = event.getNativeKeyCode();
		if (KeyCodes.KEY_ENTER == key) {
			if (event.isControlKeyDown()) {
				focusOnBoxMode();
			}
			else {
				cmd = inputCmd.getInnerText();
				presenter.onSubmit();
			}
		}
		else if (KeyCodes.KEY_UP == key){
			presenter.onUp();
		}
		else if (KeyCodes.KEY_DOWN == key){
			presenter.onDown();
		}
		else if (KeyCodes.KEY_TAB == key){
			event.preventDefault();
			presenter.onTabInput();
		}
	}

	private void focusOnBoxMode() {
		boxCmd.setInnerText(inputCmd.getInnerText());
		inputCmd.setInnerText("");
		inputCmd.getStyle().setVisibility(Visibility.HIDDEN);
		boxCmd.getStyle().setVisibility(Visibility.VISIBLE);
		boxCmd.focus();
	}
	
	@Override
	public void setTextFromInputCmd(){
		addText(  inputStack.getInnerText()
				+ inputSymbol.getInnerText() 
				+ " " 
				+ inputCmd.getInnerText());
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
		return cmd;
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

	@Override
	public void clean() {
		int length = inputBox.getChildCount();
		List<Node> nodes = new ArrayList<Node>();
		for (int i =0; i<length; i++) {
			nodes.add(inputBox.getChild(i));
		}
		
		for (Node node : nodes) {
			inputBox.removeChild(node);
		}
		
		inputCmd.setInnerText(null);
	}
	
	@Override
	public void setSymbol(String symbol) {
		inputSymbol.setInnerHTML(symbol);
	}
	
}
