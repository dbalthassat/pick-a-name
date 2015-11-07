package com.dbalthassat.controller;

import com.dbalthassat.entity.Event;
import com.dbalthassat.entity.EventPerson;
import com.dbalthassat.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("eventPerson")
public class EventPersonController {
	@Autowired
	private EventRepository eventRepository;

	@RequestMapping(value = "/{eventId}/{firstname}", produces = "application/json; charset=utf-8")
	public EventPerson findEventPerson(@PathVariable Long eventId, @PathVariable String firstname) {
		Event event = eventRepository.findOne(eventId);
		if(event == null) {
			return null;
		}
		return event.getEventPersons().stream()
				.filter(e -> e.getPerson().getName().equals(firstname))
				.findAny()
				.orElse(null);
	}
}
