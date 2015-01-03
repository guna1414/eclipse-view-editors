package com.test.userinterface.domain;

public enum ComponentType {
	BASIC,LAYOUT,BUSINESS, PAGE;
	
	public static ComponentType getType(int i){
		if(i == 1){
			return ComponentType.BASIC;
		}
		else if(i==2){
			return ComponentType.LAYOUT;
		}
		else if(i==3){
			return ComponentType.BUSINESS;
		}
		else if(i==4){
			return ComponentType.PAGE;
		}
		else{
			return null;
		}
	}

}
