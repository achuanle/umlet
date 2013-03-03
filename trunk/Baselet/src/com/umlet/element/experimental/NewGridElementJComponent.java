package com.umlet.element.experimental;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;

import com.baselet.control.Utils;
import com.baselet.diagram.draw.BaseDrawHandler;

public class NewGridElementJComponent extends JComponent {
	private static final long serialVersionUID = 1L;
	
	private BaseDrawHandler drawer;
	private BaseDrawHandler metaDrawer;
	private NewGridElement gridElement;
	
	public NewGridElementJComponent(BaseDrawHandler drawer, BaseDrawHandler metaDrawer, NewGridElement gridElement) {
		this.drawer = drawer;
		this.metaDrawer = metaDrawer;
		this.gridElement = gridElement;
	}

	@Override
	public void paint(Graphics g) {
		drawer.setGraphics(g);
		metaDrawer.setGraphics(g);
		drawer.drawAll(gridElement.isSelected);
		metaDrawer.drawAll();
	}

	/**
	 * Must be overwritten because Swing uses this method to tell if 2 elements are overlapping
	 * It's also used to determine which element gets selected if there are overlapping elements (the smallest one)
	 * IMPORTANT: on overlapping elements, contains is called for all elements until the first one returns true, then the others contain methods are not called
	 */
	@Override
	public boolean contains(Point p) {
		return Utils.contains(gridElement, p);
	}

	/**
	 * Must be overwritten because Swing sometimes uses this method instead of contains(Point)
	 */
	@Override
	public boolean contains(int x, int y) {
		return Utils.contains(gridElement, new Point(x, y));
	}

}
