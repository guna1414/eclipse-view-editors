package com.test.userinterface.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.userinterface.domain.Component;
import com.test.userinterface.domain.ComponentPage;
import com.test.userinterface.domain.ComponentType;
import com.test.userinterface.domain.Event;
import com.test.userinterface.domain.Rule;
import com.test.userinterface.views.ImageShare;

public class PageModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -238880634511847695L;

	private static PageModel instance;

	private final Map<Long,ComponentPage> componentPages;

	private PageModel(Map<Long,ComponentPage> componentPages){
		this.componentPages = componentPages;
	}

	public static PageModel getInstance(){

		if (null == instance){
			synchronized (PageModel.class) {
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
	private static PageModel createInstance() {
		List<Component> componentFields = new ArrayList<Component>();
		List<Event> eventList = new ArrayList<Event>();
		List<Rule> ruleList = new ArrayList<Rule>();
		Component component;
	    Event event;
	    Rule rule;

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
			componentFields.add(component);
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
			componentFields.add(component);
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
			componentFields.add(component);
		}
		for(int i = 0; i < 10 ; i++){
			event = new Event();
			event.setEventId(Long.getLong(String.valueOf(i)));
			event.setEvnetName("Event " + i);
			eventList.add(event);
		}

		for(int i = 0; i < 15 ; i++){
			rule = new Rule();
			rule.setRuleId(Long.getLong(String.valueOf(i)));
			rule.setRuleName("Rule " + i);
			ruleList.add(rule);
		}

		Map<Long,ComponentPage> componentPages = new HashMap<Long,ComponentPage>();
		ComponentPage page1 = new ComponentPage();
		page1.setPageId(1L);
		page1.setPageName("Location");
		page1.setComponents(componentFields);
		page1.setEvents(eventList);
		page1.setRules(ruleList);

		componentPages.put(page1.getPageId(), page1);
		return new PageModel(componentPages);
	}

	public Collection<ComponentPage> getComponents(){
		return componentPages.values();
	}

	public ComponentPage getComponentById(long pageId) {
		return componentPages.get(pageId);
	}

}
