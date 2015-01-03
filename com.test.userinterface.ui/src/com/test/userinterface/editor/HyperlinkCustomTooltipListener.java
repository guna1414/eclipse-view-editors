package com.test.userinterface.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.Hyperlink;

import com.test.userinterface.domain.Component;
import com.test.userinterface.views.ImageShare;

public  class HyperlinkCustomTooltipListener implements Listener {

	private static String TABLEITEM = "_TABLEITEM";

	private Hyperlink link;
	private Display display;
	private Shell shell;

	private Shell tip = null;
	private Label label = null;

	private Listener labelListener = new Listener() {
		public void handleEvent(Event event) {
			Label label = (Label) event.widget;
			Shell shell = label.getShell();
			switch (event.type) {
				case SWT.MouseDown:
					Event e = new Event();
					e.item = link;
					// Assuming table is single select, set the selection as
					// if
					// the mouse down event went through to the table
					// fall through
				case SWT.MouseExit:
					shell.dispose();
					break;
			}
		}
	};

	public HyperlinkCustomTooltipListener(Hyperlink link, Display display) {
		this.link = link;
		this.display = display;
		this.shell = display.getActiveShell();
	}

	public void handleEvent(Event event) {
		switch (event.type) {
			case SWT.Dispose:
			case SWT.KeyDown:
			case SWT.MouseMove: {
				if (tip == null)
					break;
				tip.dispose();
				tip = null;
				label = null;
				break;
			}
			case SWT.MouseHover: {
				if (link != null) {
					if (tip != null && !tip.isDisposed()){
						tip.dispose();
					}
					if(null == shell || shell.isDisposed()){
						shell = display.getActiveShell();
					}
					tip = new Shell(shell, SWT.ON_TOP | SWT.TOOL);
					tip.setLayout(new FillLayout());
					label = new Label(tip, SWT.NONE);
					label.setForeground(display.getSystemColor(SWT.COLOR_INFO_FOREGROUND));
					label.setBackground(display.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
					label.setData(HyperlinkCustomTooltipListener.TABLEITEM, link);
					label.setImage(((Component)link.getData()).getConfigurationContents());
					label.addListener(SWT.MouseExit, labelListener);
					label.addListener(SWT.MouseDown, labelListener);
					Point size = tip.computeSize(SWT.DEFAULT, SWT.DEFAULT);
					Rectangle rect = ((Component)link.getData()).getConfigurationContents().getBounds();
					Point pt = link.toDisplay(rect.x, rect.y);
					tip.setBounds(pt.x + 20, pt.y - 7, size.x, size.y);
					tip.setVisible(true);
				}
			}
		}
	}

}
