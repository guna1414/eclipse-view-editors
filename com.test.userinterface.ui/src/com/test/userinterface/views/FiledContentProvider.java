package com.test.userinterface.views;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.test.userinterface.model.ComponentModel;
import com.test.userinterface.model.PageModel;
import com.test.userinterface.domain.ComponentType;

public class FiledContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getChildren(Object parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getElements(Object parent) {
		if(parent instanceof ComponentType){
			if(ComponentType.BASIC.equals(parent)){
				return ComponentModel.getInstance().getBasicComponents().toArray();
			}
			else if(ComponentType.BUSINESS.equals(parent)){
				return ComponentModel.getInstance().getBuisnessComponents().toArray();
			}
			else if(ComponentType.LAYOUT.equals(parent)){
				return ComponentModel.getInstance().getLayoutComponents().toArray();
			}
			else if(ComponentType.PAGE.equals(parent)){
				return PageModel.getInstance().getComponents().toArray();
			}
		}
		return null;
	}

	@Override
	public Object getParent(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
