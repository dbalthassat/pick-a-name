package com.dbalthassat.controller;

import com.dbalthassat.entity.Event;
import com.dbalthassat.entity.EventPerson;
import com.dbalthassat.entity.Person;
import com.dbalthassat.exception.BadRequestException;
import com.dbalthassat.repository.EventRepository;
import com.dbalthassat.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.groups.Default;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event")
public class EventController {
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private EventRepository eventRepository;

	@RequestMapping(value = "/init", produces = "application/json; charset=utf-8")
	@Transactional
	public Event init() {
		Event e = new Event();
		e.setName("HTG");
		eventRepository.save(e);

		Person p1 = new Person("Élise");
		Person p2 = new Person("Damien");
		personRepository.save(p1);
		personRepository.save(p2);

		Set<EventPerson> persons = new HashSet<>();
		persons.add(new EventPerson(e, p1));
		persons.add(new EventPerson(e, p2));
		e.getEventPersons().addAll(persons);

		return e;
	}

	@SuppressWarnings("MVCPathVariableInspection")
	@RequestMapping(value = {"/", "/{id}"}, method = RequestMethod.POST, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@Transactional
	public Event createEvent(@Validated(Event.Create.class) @RequestBody Event event,
							BindingResult result) throws BadRequestException {
		if(result.hasErrors()) {
			throw new BadRequestException(result.getAllErrors().toString());
		}
		personRepository.save(event.getPersons().stream().map(Person::new).collect(Collectors.toList()));
		eventRepository.save(event);
		return event;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public void deleteEvent(@PathVariable Long id) {
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public void updateEvent(@PathVariable Long id) {
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Event findEvent(@PathVariable Long id) {
		return eventRepository.findOne(id);
	}
}
