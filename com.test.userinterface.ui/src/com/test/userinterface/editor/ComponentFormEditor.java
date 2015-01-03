package com.test.userinterface.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.editor.FormEditor;

import com.test.userinterface.domain.Component;
import com.test.userinterface.model.ComponentModel;

public class ComponentFormEditor extends FormEditor {

	public static final String ID = "com.test.userinterface.editor.ComponentFormEditor.ID";

	private Component component;
	private boolean dirty;

	@Override
	protected void setContentDescription(String description) {
		// TODO Auto-generated method stub
		super.setContentDescription("Content Description");
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Title Page";
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {

		if (input instanceof ComponentEditorInput) {
			ComponentEditorInput editorInput = (ComponentEditorInput) input;
			long componentId = editorInput.getComponentId();
			component = ComponentModel.getInstance().getComponentById(componentId);
		}
		super.init(site, input);
		setComponent(component);
		setPartName(component.getComponentName());

	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
		firePropertyChange(IWorkbenchPartConstants.PROP_DIRTY);
	}
	@Override
	protected void addPages() {
		try {
			addPage(new ComponentImagePage(this));
			addPage(new ComponentPropertyPage(this));
			addPage(new ComponentConfigurationPage(this));
		} catch (PartInitException e) {
			e.printStackTrace();
		}

	}


	public static void openComponent(final long componentId) {


		Display display = getDisplay();

		display.asyncExec(new Runnable() {

			@Override
			public void run() {
				ComponentEditorInput input = new ComponentEditorInput(componentId);

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


	@Override
	public void doSave(IProgressMonitor paramIProgressMonitor) {
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

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}



}
