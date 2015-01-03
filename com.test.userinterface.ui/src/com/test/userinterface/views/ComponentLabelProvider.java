package com.test.userinterface.views;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class ComponentLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		if(null != element){
		return element.toString();
		}
		return null;
	}

	public Image getImage(Object element) {
		return ImageShare.getImage(ImageShare.CLASS_ICON);
	}

}
