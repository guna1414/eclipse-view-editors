package com.test.userinterface.editor;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.internal.forms.widgets.FormFonts;

public class ComponentConfigurationPage extends FormPage {
	public static final String ID = "com.test.userinterface.editor.ComponentConfigurationPage";
	private static final String TITLE = "Configuration.xml";
	private FormToolkit toolkit;
	private ScrolledForm form;
	private Color feedColor;
	private TextViewer configurationText;
	private Section configurationSection;
	private ComponentFormEditor editor;

	public ComponentConfigurationPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	public ComponentConfigurationPage(ComponentFormEditor editor) {
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

		GridLayout compositeLayout = new GridLayout(2, false);
		compositeLayout.marginHeight = 0;
		compositeLayout.marginTop = 5;
		compositeLayout.verticalSpacing = 0;

		Composite body = this.form.getBody();
		body.setLayout(compositeLayout);

		Composite leftComposite = this.toolkit.createComposite(body);
		leftComposite.setLayout(new GridLayout());
		GridDataFactory.fillDefaults().grab(true, true).applyTo(leftComposite);

		createConfigurationSection(leftComposite, "Configuration File");
	}

	private void createConfigurationSection(Composite parent, String title){


		this.configurationSection = new Section(parent, 256);
		this.toolkit.adapt(this.configurationSection);
		this.configurationSection.setTitleBarForeground(this.toolkit.getColors().getColor(
				"org.eclipse.ui.forms.TB_TOGGLE"));
		this.configurationSection.setTitleBarBackground(this.toolkit.getColors().getColor(
				"org.eclipse.ui.forms.TB_BG"));
		this.configurationSection.setTitleBarBorderColor(this.toolkit.getColors().getColor(
				"org.eclipse.ui.forms.TB_BORDER"));
		this.configurationSection.setFont(FormFonts.getInstance().getBoldFont(
				getSite().getShell().getDisplay(), this.configurationSection.getFont()));

		this.configurationSection.setText(title);
		this.configurationSection.setLayout(new GridLayout());
		GridDataFactory.fillDefaults().grab(true, true).hint(300, 100)
		.applyTo(this.configurationSection);

		this.toolkit.paintBordersFor(configurationSection);
		toolkit.createCompositeSeparator(configurationSection);
		Composite headerComposite = this.toolkit.createComposite(configurationSection, 0);
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
		gd.heightHint = 50;
		gd.widthHint = 150;

		this.configurationText = new TextViewer(headerComposite, SWT.VIRTUAL |SWT.MULTI | SWT.BORDER |SWT.V_SCROLL);
		configurationText.getControl().setLayoutData(gd);
		configurationText.setDocument(new Document(editor.getComponent().getConfigurationFile()));
		configurationText.setEditable(false);
		configurationSection.setClient(headerComposite);

	}

}
