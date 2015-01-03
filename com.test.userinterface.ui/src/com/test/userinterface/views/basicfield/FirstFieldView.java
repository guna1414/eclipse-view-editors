package com.test.userinterface.views.basicfield;

import org.eclipse.swt.widgets.Composite;

import com.test.userinterface.views.FieldView;
import com.test.userinterface.domain.ComponentType;

public class FirstFieldView extends FieldView {

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		treeViewer.setInput(ComponentType.BASIC);
	}


}