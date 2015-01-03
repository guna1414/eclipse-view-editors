package com.test.userinterface.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;

import com.test.userinterface.domain.ComponentPage;
import com.test.userinterface.model.ComponentModel;
import com.test.userinterface.model.PageModel;

public class ComponentPageFormEditor extends FormEditor{

	public static final String ID = "com.test.userinterface.editor.ComponentPageFormEditor.ID";
	public static final String basicID = "com.test.userinterface.editor.BasicFormPage";
	public static final String layoutID = "com.test.userinterface.editor.LayoutFormPage";
	public static final String businessID = "com.test.userinterface.editor.BusinessFormPage";
	public static final String eventID = "com.test.userinterface.editor.EventFormPage";
	public static final String ruleID = "com.test.userinterface.editor.RuleFormPage";

	private ComponentPage page;



	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {

		if (input instanceof ComponentPageEditorInput) {
			ComponentPageEditorInput editorInput = (ComponentPageEditorInput) input;
			long pageId = editorInput.getPageId();
			page = PageModel.getInstance().getComponentById(pageId);
		}
		super.init(site, input);
		setPage(page);
		setPartName(page.getPageName());

	}


	@Override
	protected void addPages() {
		try {
			addPage(new PageBasicFormPage(this, basicID, "BASIC"));
			addPage(new PageBasicFormPage(this, layoutID, "LAYOUT"));
			addPage(new PageBasicFormPage(this, businessID, "BUSINESS"));
			addPage(new PageListPage(this, eventID, "EVENT"));
			addPage(new PageListPage(this, ruleID, "RULE"));
		} catch (PartInitException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void doSave(IProgressMonitor arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	public static void openComponent(final long pageId) {


		Display display = getDisplay();

		display.asyncExec(new Runnable() {

			@Override
			public void run() {
				ComponentPageEditorInput input = new ComponentPageEditorInput(pageId);

				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(input, ID);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		});



	}

	private static Display getDisplay() {
		return null == Display.getCurrent() ? Display.getDefault() : Display.getCurrent();
	}

	public ComponentPage getPage() {
		return page;
	}


	public void setPage(ComponentPage page) {
		this.page = page;
	}


}
