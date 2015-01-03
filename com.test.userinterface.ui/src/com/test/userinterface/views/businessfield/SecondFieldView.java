package com.test.userinterface.views.businessfield;

import org.eclipse.swt.widgets.Composite;

import com.test.userinterface.domain.ComponentType;
import com.test.userinterface.views.FieldView;

public class SecondFieldView extends FieldView {

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		super.createPartControl(parent);
		treeViewer.setInput(ComponentType.BUSINESS);
	}

}
