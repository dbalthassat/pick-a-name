package com.dbalthassat.service;

import com.dbalthassat.dto.EventPersonDisplayDTO;
import com.dbalthassat.entity.Event;
import com.dbalthassat.entity.EventPerson;
import com.dbalthassat.exception.NotFoundException;
import com.dbalthassat.mapper.EventPersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventPersonService {
	@Autowired
	private EventEntityService eventEntityService;

	public EventPersonDisplayDTO find(Long eventId, String firstname) throws NotFoundException {
		Event event = eventEntityService.find(eventId);
		if(event == null) {
			throw new NotFoundException("The event " + eventId + " doesn't exist.");
		}
		Optional<EventPerson> op = event.getEventPersons().stream()
				.filter(e -> e.getPerson().getName().equals(firstname))
				.findAny();
		if(!op.isPresent()) {
			throw new NotFoundException("The person " + firstname + " doesn't exist in the event.");
		}
		return EventPersonMapper.map(op.get());
	}
}
