package com.test.userinterface.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.part.ViewPart;

import com.test.userinterface.domain.Component;
import com.test.userinterface.editor.ComponentFormEditor;

public class FieldView extends ViewPart {

	protected TreeViewer treeViewer;
	protected IAction refreshAction;


	public void createPartControl(Composite parent) {
		PatternFilter filter = new PatternFilter();
		FilteredTree tree = new FilteredTree(parent,SWT.MULTI | SWT.V_SCROLL , filter, true);
		treeViewer = tree.getViewer();
		treeViewer.setContentProvider(new FiledContentProvider());
		treeViewer.setLabelProvider(new ComponentLabelProvider());
		treeViewer.expandToLevel(2);
		createActions();
		contributeToActionBars();
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
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
				}
			}




		});
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalToolBar(bars.getToolBarManager());
		}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(new Separator());
		manager.add(refreshAction);
		manager.add(new Separator());
	}

	private void createActions() {
		refreshAction = new Action(){
			public void run(){

			}
		};
		refreshAction.setText(ViewConstants.REFRESH);
		refreshAction.setToolTipText(ViewConstants.REFRESH_TIP);
		refreshAction.setImageDescriptor(ImageShare.getImageDescriptor(ImageShare.REFRESH_ICON));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
