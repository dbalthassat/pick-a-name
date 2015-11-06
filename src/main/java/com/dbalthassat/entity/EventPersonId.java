package com.dbalthassat.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class EventPersonId implements Serializable {
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference("event")
	private Event event;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference("person")
	private Person person;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
