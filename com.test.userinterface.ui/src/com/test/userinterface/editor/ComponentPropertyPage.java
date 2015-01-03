package com.test.userinterface.editor;

import java.util.ArrayList;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.internal.forms.widgets.FormFonts;

import com.test.userinterface.domain.ComponentProperty;

public class ComponentPropertyPage extends FormPage {

	public static final String ID = "com.test.userinterface.editor.ComponentPropertyPage";
	private static final String TITLE = "Component Property";
	private FormToolkit toolkit;
	private ScrolledForm form;
	private Color feedColor;
	private TableViewer propertiesViewer;
	private Table propertiesTable;
	private ComponentFormEditor editor;

	public ComponentPropertyPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	public ComponentPropertyPage(ComponentFormEditor editor) {
		super(editor,ID,TITLE);
		this.editor = editor;
	}

	@Override
	protected void createFormContent(IManagedForm managedForm) {
		super.createFormContent(managedForm);

		this.toolkit = managedForm.getToolkit();
		this.form = managedForm.getForm();

		this.feedColor = new Color(Display.getDefault(), 70, 70, 70);

		this.toolkit.decorateFormHeading(this.form.getForm());

		GridLayout compositeLayout = new GridLayout(2, true);
		compositeLayout.marginHeight = 0;
		compositeLayout.marginTop = 5;
		compositeLayout.verticalSpacing = 0;

		Composite body = this.form.getBody();
		body.setLayout(compositeLayout);

		Composite leftComposite = this.toolkit.createComposite(body);
		leftComposite.setLayout(new GridLayout());
		GridDataFactory.fillDefaults().grab(true, true).applyTo(leftComposite);

		Composite rightComposite = this.toolkit.createComposite(body);
		rightComposite.setLayout(new GridLayout());
		GridDataFactory.fillDefaults().grab(true, true).applyTo(rightComposite);
		createPropertiesSection(leftComposite, "Property");
	}

	private void createPropertiesSection(Composite parent, String title) {

		this.toolkit.paintBordersFor(parent);

		Section section = new Section(parent, 256) {};
		this.toolkit.adapt(section);
		section.setTitleBarForeground(this.toolkit.getColors().getColor(
				"org.eclipse.ui.forms.TB_TOGGLE"));
		section.setTitleBarBackground(this.toolkit.getColors().getColor(
				"org.eclipse.ui.forms.TB_BG"));
		section.setTitleBarBorderColor(this.toolkit.getColors().getColor(
				"org.eclipse.ui.forms.TB_BORDER"));
		section.setFont(FormFonts.getInstance().getBoldFont(
				getSite().getShell().getDisplay(), section.getFont()));

		section.setText(title);
		section.setLayout(new GridLayout());
		GridDataFactory.fillDefaults().grab(true, true).hint(300, 100)
				.applyTo(section);
		this.toolkit.paintBordersFor(section);
		toolkit.createCompositeSeparator(section);
		Composite headerComposite = this.toolkit.createComposite(section, 0);
		RowLayout rowlayout = new RowLayout();
		TableLayout tableLayout1 = new TableLayout();
		GridLayout tableLayout = new GridLayout();
		tableLayout.marginWidth = 0;
		tableLayout.marginHeight = 0;
		tableLayout.numColumns =1;
		tableLayout.verticalSpacing = 5;
		headerComposite.setLayout(tableLayout);
		headerComposite.setBackground(null);

		GridData gd = new GridData(1808);
		gd.horizontalSpan =1;
		gd.verticalSpan = 4;
		gd.heightHint = 100;
		gd.widthHint = 150;


		this.propertiesViewer = new TableViewer(headerComposite, SWT.VIRTUAL |SWT.MULTI | SWT.BORDER |SWT.V_SCROLL);
		this.propertiesTable = propertiesViewer.getTable();
		propertiesTable.setHeaderVisible(true);
		propertiesTable.setLinesVisible(true);
		this.toolkit.paintBordersFor(propertiesTable);
		propertiesViewer.setContentProvider(new ArrayContentProvider());
		propertiesViewer.setInput(editor.getComponent().getPropertyList());
		this.propertiesViewer.getControl().setLayoutData(gd);

		createColumns(propertiesViewer);
		section.setClient(headerComposite);
	}

	private void createColumns(final TableViewer propertiesViewer) {
		TableViewerColumn propertyColumn = new TableViewerColumn(propertiesViewer, SWT.NONE);
		propertyColumn.getColumn().setText("Property");
		propertyColumn.getColumn().setWidth(200);
		propertyColumn.getColumn().setResizable(true);
		propertyColumn.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element){
					if(element instanceof ComponentProperty){
						return ((ComponentProperty)element).getPropertyName();
					}
					return "";
				}
			});

		TableViewerColumn propertyDescColumn = new TableViewerColumn(propertiesViewer, SWT.NONE);
		propertyDescColumn.getColumn().setText("Property Description");
		propertyDescColumn.getColumn().setWidth(200);
		propertyDescColumn.getColumn().setResizable(true);
		propertyDescColumn.setLabelProvider(new ColumnLabelProvider(){
			    @Override
				public String getText(Object element){
					if(element instanceof ComponentProperty){
						return ((ComponentProperty)element).getPropertyDesc();
					}
					return "";
				}
			});

	}



	}
