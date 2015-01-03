package com.test.userinterface.editor;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
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
import org.eclipse.ui.forms.widgets.Hyperlink;

import com.test.userinterface.views.ImageShare;

public class ComponentImagePage extends FormPage {

	public static final String ID = "com.test.userinterface.editor.ComponentOverviewFormPage";
	private static final String TITLE = "Component Image";

	private FormToolkit toolkit;
	private ScrolledForm form;
	private SWTImageCanvas swtComponentImage;
	private Color feedColor;
	private Image sourceImage;
	private ComponentFormEditor editor;

	public ComponentImagePage(ComponentFormEditor editor) {
		super(editor, ID, TITLE);
		this.editor = editor;
	}

	public ComponentImagePage(String id, String title) {
		super(id, title);
		// TODO Auto-generated constructor stub
	}

	public ComponentImagePage(FormEditor editor, String id, String title) {
		super(editor, id, title);

	}

	protected void createFormContent(IManagedForm managedForm) {
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
		GridDataFactory.fillDefaults().grab(true, true).hint(300, 50)
		.applyTo(leftComposite);

		/*Composite rightComposite = this.toolkit.createComposite(body);
		rightComposite.setLayout(new GridLayout());
		GridDataFactory.fillDefaults().grab(true, true).applyTo(rightComposite);*/

		createImageSection(leftComposite, "Component Image");
	}


	private void createImageSection(Composite parent, String title) {
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
		GridDataFactory.fillDefaults().grab(true, true)
				.applyTo(section);

		this.toolkit.paintBordersFor(section);
		toolkit.createCompositeSeparator(section);
		Composite headerComposite = this.toolkit.createComposite(section, 0);
		GridLayout tableLayout = new GridLayout();
		headerComposite.setLayout(tableLayout);

		GridData gdheader = new GridData();
		gdheader.horizontalSpan =1;
		gdheader.verticalSpan = 1;
		gdheader.grabExcessHorizontalSpace = true;
		gdheader.grabExcessVerticalSpace = true;
		headerComposite.setLayoutData(gdheader);



		sourceImage = editor.getComponent().getConfigurationContents();

		Rectangle imageBound = sourceImage.getBounds();

		GridData gd = new GridData();
		gd.horizontalSpan =1;
		gd.verticalSpan = 1;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		gd.heightHint = imageBound.height;
		gd.widthHint = imageBound.width;
		gd.horizontalIndent = 0;
		gd.verticalIndent = 0;


		this.swtComponentImage = new SWTImageCanvas(headerComposite, SWT.VIRTUAL |SWT.MULTI | SWT.BORDER |SWT.V_SCROLL);
		swtComponentImage.setLayoutData(gd);
		swtComponentImage.loadImage(sourceImage);



		section.setClient(headerComposite);
		}
}
