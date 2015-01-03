package com.test.userinterface.views.layoutfield;

import org.eclipse.swt.widgets.Composite;

import com.test.userinterface.domain.ComponentType;
import com.test.userinterface.views.FieldView;

public class ThirdFieldView extends FieldView {

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		treeViewer.setInput(ComponentType.LAYOUT);
	}


}
