package com.test.userinterface.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.part.ViewPart;

import com.test.userinterface.domain.ComponentType;

public class ComponentTabbedView extends ViewPart{
	private TreeViewer treeViewer;
	private IAction showBasicFields;
	private IAction showLayoutFields;
	private IAction showBusinessFields;
	private IAction showPages;
	private IAction refreshAction;

	@Override
	public void createPartControl(Composite parent) {
		PatternFilter filter = new PatternFilter();
		FilteredTree tree = new FilteredTree(parent,SWT.MULTI | SWT.V_SCROLL , filter, true);
		treeViewer = tree.getViewer();
		treeViewer.setContentProvider(new FiledContentProvider());
		treeViewer.setLabelProvider(new ComponentLabelProvider());
		treeViewer.expandToLevel(2);
		treeViewer.setInput(ComponentType.BASIC);
		ComponentTabbedView.this.setContentDescription("Basic Fields");
		createActions();
		IActionBars actionBars = getViewSite().getActionBars();
		contributeToActionBars(actionBars);
		treeViewer.addDoubleClickListener(new FieldDoubleClickListener());
	}

	private void contributeToActionBars(IActionBars actionBars) {
		fillLocalToolBar(actionBars.getToolBarManager());
		contributeToDropDownMenu(actionBars.getMenuManager());

	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(new Separator());
		manager.add(refreshAction);
		manager.add(new Separator());

	}

	private void contributeToDropDownMenu(IMenuManager manager) {
		manager.add(this.showBasicFields);
		manager.add(new Separator());
		manager.add(this.showLayoutFields);
		manager.add(new Separator());
		manager.add(this.showBusinessFields);
		manager.add(new Separator());
		manager.add(this.showPages);
	}

	private void createActions() {
		refreshAction = new Action(){
			public void run(){

			}
		};
		refreshAction.setText(ViewConstants.REFRESH);
		refreshAction.setToolTipText(ViewConstants.REFRESH_TIP);
		refreshAction.setImageDescriptor(ImageShare.getImageDescriptor(ImageShare.REFRESH_ICON));

		//creating enable basic Fields action
		showBasicFields = new Action() {
			public void run(){
					treeViewer.setInput(ComponentType.BASIC);
					treeViewer.refresh();
					ComponentTabbedView.this.setContentDescription("Basic Components");
			}
		};
		showBasicFields.setText("show Basic Fields");

		//creating layout fields
		showLayoutFields = new Action() {
			public void run(){
					treeViewer.setInput(ComponentType.LAYOUT);
					treeViewer.refresh();
					ComponentTabbedView.this.setContentDescription("Layout Components");
			}
		};
		showLayoutFields.setText("show Layout Fields");

		//creating business fields action
		showBusinessFields = new Action() {
			public void run(){
					treeViewer.setInput(ComponentType.BUSINESS);
					treeViewer.refresh();
					ComponentTabbedView.this.setContentDescription("Business Components");
			}
		};
		showBusinessFields.setText("show Business Fields");

		//creating pages action
		showPages = new Action(){
			public void run(){
				treeViewer.setInput(ComponentType.PAGE);
				treeViewer.refresh();
				ComponentTabbedView.this.setContentDescription("Component Pages");
			}
		};
		showPages.setText("show Pages");
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setContentDescription(String description) {
		super.setContentDescription(description);
	}
}
