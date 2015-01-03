package com.test.userinterface.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.userinterface.domain.Component;
import com.test.userinterface.domain.ComponentProperty;
import com.test.userinterface.domain.ComponentType;
import com.test.userinterface.views.ImageShare;
/**
 * @author hcl186（Gunasekar.A）
 *
 */
public class ComponentModel implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static ComponentModel instance;
	private final Map<Long,Component> components;

	private ComponentModel(Map<Long, Component> components) {
		this.components = components;
	}

	/**
	 * @return
	 */
	public static ComponentModel getInstance(){

		if (null == instance){
			synchronized (ComponentModel.class) {
				if( null == instance) {
					try {
					instance = createInstance();
					}
					catch(Exception ex) {
						throw new RuntimeException("Error while "
								+ "creating the instance",ex);
					}
				}
			}
		}
      return instance;

	}

	/**
	 * @return instance
	 * create the instance for the first time
	 */
	private static ComponentModel createInstance() {
		ArrayList<ComponentProperty> propertyList = new ArrayList<ComponentProperty>();
		propertyList.add(new ComponentProperty("property 1", "property 1 desc"));
		propertyList.add(new ComponentProperty("property 2", "property 2 desc"));
		propertyList.add(new ComponentProperty("property 3", "property 3 desc"));
		propertyList.add(new ComponentProperty("property 4", "property 4 desc"));

		String lineDelimiter = System.getProperty("line.separator", "\n");
		StringBuffer documentData = new StringBuffer();


		for(int i=0;i<20;i++){
			documentData.append("<Property"+i+">");
			documentData.append(lineDelimiter);
			documentData.append("             <PropertyValue="+"the value of property"+">");
			documentData.append(lineDelimiter);
			documentData.append("</Property"+">");
			documentData.append(lineDelimiter);
		}

		Component component;
		Map<Long, Component> components = new HashMap<Long, Component>();

		for(int i = 0; i < 5 ; i++){
			component = new Component();
			component.setComponentId(i+0L);
			component.setComponentName("BasicComponent"+i);
			component.setComponentType(ComponentType.BASIC);
			if((i > 0)&&((i%2)>0)){
				component.setConfigurationContents(ImageShare.getImage(ImageShare.SAMPLE_IMAGE));
			}
			else{
				component.setConfigurationContents(ImageShare.getImage(ImageShare.USER_FIELD));
			}
			component.setConfigurationFile(documentData.toString());
			component.setPropertyList(propertyList);
			components.put(component.getComponentId(),component);
		}

		for(int i = 5; i < 10 ; i++){
			component = new Component();
			component.setComponentId(i+0L);
			component.setComponentName("Layout Component"+i);
			component.setComponentType(ComponentType.LAYOUT);
			if((i > 0)&&((i%2)>0)){
				component.setConfigurationContents(ImageShare.getImage(ImageShare.SAMPLE_IMAGE));
			}
			else{
				component.setConfigurationContents(ImageShare.getImage(ImageShare.USER_FIELD));
			}
			component.setConfigurationFile(documentData.toString());
			component.setPropertyList(propertyList);
			components.put(component.getComponentId(),component);
		}
		for(int i = 10; i < 16 ; i++){
			component = new Component();
			component.setComponentId(i+0L);
			component.setComponentName("Business  Component"+i);
			component.setComponentType(ComponentType.BUSINESS);
			if((i > 0)&&((i%2)>0)){
				component.setConfigurationContents(ImageShare.getImage(ImageShare.SAMPLE_IMAGE));
			}
			else{
				component.setConfigurationContents(ImageShare.getImage(ImageShare.USER_FIELD));
			}
			component.setConfigurationFile(documentData.toString());
			component.setPropertyList(propertyList);
			components.put(component.getComponentId(),component);
		}


		return new ComponentModel(components);
	}

	/**
	 * refresh the basicFields once the refresh button is clicked
	 */
	public void refreshModel(){
		components.clear();
	}

	/**
	 * @return basic type components
	 */
	public List<Component> getBasicComponents() {
		List<Component> basicComponents = new ArrayList<Component>();
		for(Component component: components.values()){
			if(ComponentType.BASIC.equals(component.getComponentType())){
			basicComponents.add(component);
			}
		}
		return basicComponents;
	}

	public List<Component> getBuisnessComponents() {
		List<Component> businessComponents = new ArrayList<Component>();
		for(Component component: components.values()){
			if(ComponentType.BUSINESS.equals(component.getComponentType())){
				businessComponents.add(component);
			}
		}
		return businessComponents;
	}

	public List<Component> getLayoutComponents() {
		List<Component> layoutComponents = new ArrayList<Component>();
		for(Component component: components.values()){
			if(ComponentType.LAYOUT.equals(component.getComponentType())){
				layoutComponents.add(component);
			}
		}
		return layoutComponents;
	}

	public Component getComponentById(long componentId) {
		return components.get(componentId);
	}

}
