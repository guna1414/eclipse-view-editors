package com.test.userinterface.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.internal.forms.widgets.FormFonts;
import org.eclipse.swt.widgets.Tree;

import com.test.userinterface.domain.ComponentPage;
import com.test.userinterface.domain.Event;
import com.test.userinterface.domain.Rule;
import com.test.userinterface.views.ImageShare;

public class PageListPage extends FormPage {
	private class CustomContentProvider implements ITreeContentProvider {

		@Override
		public Object[] getElements(Object input) {
			// TODO Auto-generated method stub
			return ((ArrayList)input).toArray();
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub

		}

		@Override
		public void inputChanged(Viewer paramViewer, Object paramObject1,
				Object paramObject2) {
			// TODO Auto-generated method stub

		}

		@Override
		public Object[] getChildren(Object paramObject) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getParent(Object paramObject) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasChildren(Object paramObject) {
			// TODO Auto-generated method stub
			return false;
		}


	}

	private class ListLabelProvider extends LabelProvider {

		@Override
		public Image getImage(Object element) {
			// TODO Auto-generated method stub
			return ImageShare.getImage(ImageShare.BINDING_ICON);
		}

	}

	private FormEditor editor;
	private List<Event> eventList;
	private List<Rule> ruleList;
	private boolean eventPage = false;
	private String title ;
	private FormToolkit toolkit;
	private ScrolledForm form;
	private Color feedColor;
	private TreeViewer viewer;
	//private Tree viewer;
	private ComponentPage page;

	public PageListPage(FormEditor editor, String id, String title) {
		super(editor, id, title + " LIST");
		this.editor = editor;
		this.title = title;
		if(title.equalsIgnoreCase("EVENT")){
			eventPage = true;
		}
	}

	@Override
	protected void createFormContent(IManagedForm managedForm) {
		super.createFormContent(managedForm);
		this.toolkit = managedForm.getToolkit();
		this.form = managedForm.getForm();

		this.feedColor = new Color(Display.getDefault(), 70, 70, 70);

		this.toolkit.decorateFormHeading(this.form.getForm());

		Composite body = this.form.getBody();

		GridLayout compositeLayout = new GridLayout(1, true);
		compositeLayout.marginHeight = 0;
		compositeLayout.marginTop = 5;
		compositeLayout.verticalSpacing = 0;

		body.setLayout(compositeLayout);

		Section section = new Section(body, 256) {};
		this.toolkit.adapt(section);
		section.setTitleBarForeground(this.toolkit.getColors().getColor(
				"org.eclipse.ui.forms.TB_TOGGLE"));
		section.setTitleBarBackground(this.toolkit.getColors().getColor(
				"org.eclipse.ui.forms.TB_BG"));
		section.setTitleBarBorderColor(this.toolkit.getColors().getColor(
				"org.eclipse.ui.forms.TB_BORDER"));
		section.setFont(FormFonts.getInstance().getBoldFont(
				getSite().getShell().getDisplay(), section.getFont()));

		section.setText(title + " LIST");

		section.setLayout(new GridLayout());
		GridDataFactory.fillDefaults().grab(true, true)
				.applyTo(section);

		Composite headerComposite = this.toolkit.createComposite(section, 0);
		GridLayout layout = new GridLayout(3, true);
		layout.horizontalSpacing = 10;
		headerComposite.setLayout(layout);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(headerComposite);

		if(null != editor){
			page = ((ComponentPageFormEditor)editor).getPage();
		}
		if(null != page){
			eventList = page.getEvents();
			ruleList = page.getRules();
		}
		PatternFilter filter = new PatternFilter();
		FilteredTree tree = new FilteredTree(headerComposite,SWT.MULTI | SWT.V_SCROLL , filter, true);
		//viewer = toolkit.createTree(headerComposite, 8388610);
		viewer = tree.getViewer();
		viewer.setContentProvider(new CustomContentProvider());
		viewer.setLabelProvider(new ListLabelProvider());
		viewer.setInput(eventPage?eventList:ruleList);
		section.setClient(headerComposite);
	}



}
