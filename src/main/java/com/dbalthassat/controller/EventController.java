package com.dbalthassat.controller;

import com.dbalthassat.entity.Event;
import com.dbalthassat.entity.EventPerson;
import com.dbalthassat.entity.Person;
import com.dbalthassat.repository.EventPersonRepository;
import com.dbalthassat.repository.EventRepository;
import com.dbalthassat.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/event")
public class EventController {
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private EventPersonRepository eventPersonRepository;

	@RequestMapping(value = {"", "/"}, produces = "application/json; charset=utf-8")
	@Transactional
	public Event event() {
		Event e = new Event(1L);
		e.setName("HTG");
		e.setSlug("htg");
		eventRepository.save(e);

		Person p1 = new Person(1L, "Élise");
		Person p2 = new Person(2L, "Damien");
		personRepository.save(p1);
		personRepository.save(p2);

		Set<EventPerson> persons = new HashSet<>();
		persons.add(new EventPerson(e, p1));
		persons.add(new EventPerson(e, p2));
		e.getEventPersons().addAll(persons);
		eventPersonRepository.save(persons);

		return e;
	}

	@RequestMapping(value = "/{id}", produces = "application/json; charset=utf-8")
	public Event findEvent(@PathVariable Long id) {
		return eventRepository.findOne(id);
	}
}
