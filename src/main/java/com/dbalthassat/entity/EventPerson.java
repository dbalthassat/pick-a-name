package com.dbalthassat.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "event_person", schema = "public")
@AssociationOverrides({
		@AssociationOverride(name = "pk.event",
			joinColumns = @JoinColumn(name = "event_id")),
		@AssociationOverride(name = "pk.person",
			joinColumns = @JoinColumn(name = "person_id"))
})
public class EventPerson implements Serializable {
	@EmbeddedId
	private EventPersonId pk = new EventPersonId();

	@OneToOne
	private Person friend;

	public EventPerson() {}

	public EventPerson(Event event, Person person) {
		this.pk.setEvent(event);
		this.pk.setPerson(person);
	}

	@Transient
	public Event getEvent() {
		return pk.getEvent();
	}

	@Transient
	public Person getPerson() {
		return pk.getPerson();
	}

	public EventPersonId getPk() {
		return pk;
	}

	public void setPk(EventPersonId pk) {
		this.pk = pk;
	}

	public Person getFriend() {
		return friend;
	}

	public void setFriend(Person friend) {
		this.friend = friend;
	}
}
