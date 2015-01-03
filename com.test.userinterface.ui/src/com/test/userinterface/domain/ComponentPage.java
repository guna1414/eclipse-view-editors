package com.test.userinterface.domain;

import java.util.List;

/**
 * @author hcl186(A.Gunasekar)
 *
 */
public class ComponentPage {
	private List<Component> components;
	private List<Event> events;
	private List<Rule> rules;
	private String pageName;
	private long pageId;

	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public long getPageId() {
		return pageId;
	}
	public void setPageId(long pageId) {
		this.pageId = pageId;
	}

	public List<Component> getComponents() {
		return components;
	}
	public void setComponents(List<Component> components) {
		this.components = components;
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public List<Rule> getRules() {
		return rules;
	}
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	@Override
	public String toString() {
		return pageName ;
	}


}
