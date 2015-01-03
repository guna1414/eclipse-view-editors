package com.test.userinterface.domain;

public class Event {
	private Long eventId;
	private String evnetName;
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getEvnetName() {
		return evnetName;
	}
	public void setEvnetName(String evnetName) {
		this.evnetName = evnetName;
	}
	@Override
	public String toString() {
		return  evnetName ;
	}



}
