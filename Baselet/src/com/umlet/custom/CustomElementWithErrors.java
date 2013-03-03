package com.umlet.custom;

import java.util.List;

import com.baselet.control.Constants;
import com.baselet.diagram.draw.BaseDrawHandlerSwing;
import com.baselet.diagram.draw.ColorOwn;
import com.baselet.element.GridElement;


public class CustomElementWithErrors extends CustomElement {

	private static final long serialVersionUID = 1L;
	
	private List<CompileError> errors;

	public CustomElementWithErrors() {
		super();
	}
	
	public CustomElementWithErrors(List<CompileError> errors) {
		super();
		this.errors = errors;
	}

	@Override
	public void paint() {
		BaseDrawHandlerSwing drawer = new BaseDrawHandlerSwing(g2, getHandler(), ColorOwn.RED, Constants.DEFAULT_BACKGROUND_COLOR, this.getRealSize());
		drawer.drawRectangle(0, 0, this.getRealSize().width, this.getRealSize().height);
		if (errors != null) {
			float pos = textHeight();
			drawer.printLeft("Custom Element With Errors:", (int) pos);
			pos += textHeight();
			for (CompileError error : errors) {
				drawer.printLeft(error.getLineNr() + ": " + error.getError(), (int) pos);
				pos += textHeight();
			}
		}
		drawer.drawAll(isSelected);
	}

	@Override
	public final GridElement CloneFromMe() {
		CustomElementWithErrors e = (CustomElementWithErrors) super.CloneFromMe();
		e.setCode(this.getCode());
		e.errors = this.errors;
		return e;
	}
}