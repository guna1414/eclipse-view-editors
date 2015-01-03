package com.test.userinterface.views;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import com.test.userinterface.domain.Component;
import com.test.userinterface.domain.ComponentPage;
import com.test.userinterface.editor.ComponentFormEditor;
import com.test.userinterface.editor.ComponentPageFormEditor;

public class  FieldDoubleClickListener implements IDoubleClickListener{
	@Override
	public void doubleClick(final DoubleClickEvent event) {
		if (null == event) {
			return;
		}
		ISelection selection = event.getSelection();
		if (null == selection) {
			return;
		}
		if (selection.isEmpty()) {
			return;
		}
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;

			Object firstElement = structuredSelection.getFirstElement();

			if (firstElement instanceof Component) {
				System.out.println(((Component) firstElement).getComponentName());
				ComponentFormEditor.openComponent(((Component) firstElement).getComponentId());
			}
			else if (firstElement instanceof ComponentPage) {
				System.out.println(((ComponentPage) firstElement).getPageName());
				ComponentPageFormEditor.openComponent(((ComponentPage) firstElement).getPageId());
			}
		}
	}
}





