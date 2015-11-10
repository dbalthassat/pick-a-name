package com.dbalthassat.mapper;

import com.dbalthassat.dto.EventDTO;
import com.dbalthassat.entity.Event;
import com.dbalthassat.entity.EventPerson;

import java.util.stream.Collectors;

public class EventMapper {
	private EventMapper() {}

	public static Event map(EventDTO dto) {
		Event event = new Event();
		event.setId(dto.getId());
		event.setName(dto.getName());
		event.setSlug(dto.getSlug());
		event.setEventPersons(dto.getPersons().stream().map(p -> new EventPerson(event, p)).collect(Collectors.toSet()));
		return event;
	}

	public static EventDTO map(Event event) {
		EventDTO dto = mapToDisplay(event);
		dto.setPersons(event.getEventPersons().stream().map(EventPerson::getPerson).collect(Collectors.toList()));
		return dto;
	}

	public static EventDTO mapToDisplay(Event event) {
		EventDTO dto = new EventDTO();
		dto.setId(event.getId());
		dto.setName(event.getName());
		dto.setSlug(event.getSlug());
		return dto;
	}
}
