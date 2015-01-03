package com.test.userinterface.domain;

public class ComponentProperty{
	long propertyId;
	String propertyName;
	String propertyDesc;
	public ComponentProperty(String propertyName,String propertyDesc){
		this.propertyName = propertyName;
		this.propertyDesc = propertyDesc;
	}
	
	public ComponentProperty() {
		super();
	}

	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyDesc() {
		return propertyDesc;
	}
	public void setPropertyDesc(String propertyDesc) {
		this.propertyDesc = propertyDesc;
	}
	public long getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(long propertyId) {
		this.propertyId = propertyId;
	}

}
