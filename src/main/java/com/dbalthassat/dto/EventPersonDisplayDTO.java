package com.dbalthassat.dto;

public class EventPersonDisplayDTO {
	private Long eventId;
	private PersonDTO person;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public PersonDTO getPerson() {
		return person;
	}

	public void setPerson(PersonDTO person) {
		this.person = person;
	}
}
