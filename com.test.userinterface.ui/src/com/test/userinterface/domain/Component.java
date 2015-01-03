package com.test.userinterface.domain;

import java.util.List;

import org.eclipse.swt.graphics.Image;

public class Component {
	private Long componentId;
	private ComponentType componentType;
	private String componentName;
	private String componentFileName;
	private Image configurationContents;
	private String configurationFile;
	private List<ComponentProperty> propertyList;
	//private Bfile componentObject;
	public Long getComponentId() {
		return componentId;
	}
	public void setComponentId(Long componentId) {
		this.componentId = componentId;
	}
	public ComponentType getComponentType() {
		return componentType;
	}
	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getComponentFileName() {
		return componentFileName;
	}
	public void setComponentFileName(String componentFileName) {
		this.componentFileName = componentFileName;
	}
	public Image getConfigurationContents() {
		return configurationContents;
	}
	public void setConfigurationContents(Image configurationContents) {
		this.configurationContents = configurationContents;
	}
	public List<ComponentProperty> getPropertyList() {
		return propertyList;
	}
	public void setPropertyList(List<ComponentProperty> propertyList) {
		this.propertyList = propertyList;
	}
	public String getConfigurationFile() {
		return configurationFile;
	}
	public void setConfigurationFile(String configurationFile) {
		this.configurationFile = configurationFile;
	}
	@Override
	public String toString() {
		return componentName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Component other = (Component) obj;
		if (componentId != other.componentId){
			return false;
		}
		return true;
	}


}
