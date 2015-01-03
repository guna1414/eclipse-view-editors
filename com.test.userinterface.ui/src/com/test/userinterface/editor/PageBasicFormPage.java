package com.test.userinterface.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.internal.forms.widgets.FormFonts;

import com.test.userinterface.domain.ComponentType;
import com.test.userinterface.domain.Component;
import com.test.userinterface.domain.ComponentPage;
import com.test.userinterface.views.ImageShare;

public class PageBasicFormPage extends FormPage {

	private static String ID = "com.test.userinterface.editor.PageBasicFormPage";
	private String TITLE ;
	private FormEditor editor;
	private FormToolkit toolkit;
	private ScrolledForm form;
	private Color feedColor;
	private SWTImageCanvas swtComponentImage;
	private ComponentType componentType;
	private ComponentPage page;
	private List<Component> components;

	public PageBasicFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title+" FIELDS");
		this.editor = editor;
		if(title.equalsIgnoreCase("BASIC")){
			this.componentType = ComponentType.BASIC;
		}
		if(title.equalsIgnoreCase("LAYOUT")){
			this.componentType = ComponentType.LAYOUT;
		}
		if(title.equalsIgnoreCase("BUSINESS")){
			this.componentType = ComponentType.BUSINESS;
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

		section.setText("Field List");

		section.setLayout(new GridLayout());
		GridDataFactory.fillDefaults().grab(true, true)
				.applyTo(section);

		Composite headerComposite = this.toolkit.createComposite(section, 0);

		GridLayout layout = new GridLayout(1, true);
		layout.horizontalSpacing = 10;
		headerComposite.setLayout(layout);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(headerComposite);
		/*RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		rowLayout.wrap = true;
		rowLayout.fill = true;
		rowLayout.marginLeft = 10;
		rowLayout.marginRight = 10;
		rowLayout.marginTop = 0;
		rowLayout.marginHeight =50;
		rowLayout.justify = false;
		headerComposite.setLayout(rowLayout);*/

		page = ((ComponentPageFormEditor)editor).getPage();

		if(null != page){
		components = page.getComponents();
		}


		if ((null != components) && (null != componentType)&& (!components.isEmpty())) {
			for (final Component c : components) {

				if ((null != c) && (componentType.equals(c.getComponentType()))) {
					Hyperlink link = this.toolkit.createHyperlink(
							headerComposite, c.getComponentName(),
							4 | SWT.COLOR_LIST_SELECTION);
					GridDataFactory.fillDefaults().applyTo(link);
					//link.setLayoutData(new RowData(50,50));
					link.setData(c);
					link.addMouseListener(new MouseListener() {

						@Override
						public void mouseUp(MouseEvent paramMouseEvent) {
							System.out.println("mouseUp called");

						}

						@Override
						public void mouseDown(MouseEvent paramMouseEvent) {
							System.out.println("mouse down called");
							ComponentFormEditor.openComponent(c.getComponentId());

						}

						@Override
						public void mouseDoubleClick(MouseEvent paramMouseEvent) {
							System.out.print("mouseDoubleClick called");

						}
					});
					addToolTipSupport(link, headerComposite.getDisplay());
				}
			}
		}
		section.setClient(headerComposite);
	}

	private void addToolTipSupport(Hyperlink link, Display display) {
		link.setToolTipText("");
		HyperlinkCustomTooltipListener tooltipListener = new HyperlinkCustomTooltipListener(
				link, display);
		link.addListener(SWT.Dispose, tooltipListener);
		link.addListener(SWT.KeyDown, tooltipListener);
		link.addListener(SWT.MouseMove, tooltipListener);
		link.addListener(SWT.MouseHover, tooltipListener);
	}


}
