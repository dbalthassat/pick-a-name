package com.dbalthassat.service;

import com.dbalthassat.dto.EventPersonDisplayDTO;
import com.dbalthassat.dto.PersonDTO;
import com.dbalthassat.entity.Event;
import com.dbalthassat.entity.EventPerson;
import com.dbalthassat.exception.BadRequestException;
import com.dbalthassat.exception.NotFoundException;
import com.dbalthassat.mapper.EventPersonMapper;
import com.dbalthassat.mapper.PersonMapper;
import com.dbalthassat.utils.FriendshipUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventPersonService {
	@Autowired
	private EventEntityService eventEntityService;

	@Autowired
	private EventPersonEntityService eventPersonEntityService;

	public EventPersonDisplayDTO find(Long eventId, String firstname) throws NotFoundException {
		EventPerson eventPerson = findEventPerson(eventId, firstname);
		return EventPersonMapper.map(eventPerson);
	}

	public PersonDTO findFriend(Long eventId, String firstname) throws NotFoundException, BadRequestException {
		EventPerson eventPerson = findEventPerson(eventId, firstname);
		if(eventPerson.getFriend() != null) {
			throw new BadRequestException("The person " + firstname + " already has a friend.");
		}
		eventPerson.setFriend(FriendshipUtils.findFriend(eventPerson, eventPerson.getEvent().getEventPersons()));
		eventPersonEntityService.save(eventPerson);
		PersonDTO person = PersonMapper.map(eventPerson);
		PersonMapper.mapFriend(eventPerson, person);
		return person;
	}



	private EventPerson findEventPerson(Long eventId, String firstname) throws NotFoundException {
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
		return op.get();
	}
}
