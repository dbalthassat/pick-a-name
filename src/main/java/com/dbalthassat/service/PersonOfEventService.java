package com.dbalthassat.service;

import com.dbalthassat.dto.PersonOfEventDTO;
import com.dbalthassat.dto.PersonDTO;
import com.dbalthassat.entity.Event;
import com.dbalthassat.entity.PersonOfEvent;
import com.dbalthassat.exception.BadRequestException;
import com.dbalthassat.exception.NotFoundException;
import com.dbalthassat.mapper.EventPersonMapper;
import com.dbalthassat.mapper.PersonMapper;
import com.dbalthassat.utils.FriendshipUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonOfEventService {
	@Autowired
	private EventEntityService eventEntityService;

	@Autowired
	private EventPersonEntityService eventPersonEntityService;

	public PersonOfEventDTO find(Long eventId, String firstname) throws NotFoundException {
		PersonOfEvent personOfEvent = findEventPerson(eventId, firstname);
		return EventPersonMapper.map(personOfEvent);
	}

	public PersonDTO findFriend(Long eventId, String firstname) throws NotFoundException, BadRequestException {
		PersonOfEvent personOfEvent = findEventPerson(eventId, firstname);
		if(personOfEvent.getFriend() != null) {
			throw new BadRequestException("The person " + firstname + " already has a friend.");
		}
		personOfEvent.setFriend(FriendshipUtils.findFriend(personOfEvent, personOfEvent.getEvent().getPersons()));
		eventPersonEntityService.save(personOfEvent);
		PersonDTO person = PersonMapper.map(personOfEvent);
		PersonMapper.mapFriend(personOfEvent, person);
		return person;
	}



	private PersonOfEvent findEventPerson(Long eventId, String firstname) throws NotFoundException {
		Event event = eventEntityService.find(eventId);
		if(event == null) {
			throw new NotFoundException("The event " + eventId + " doesn't exist.");
		}
		Optional<PersonOfEvent> op = event.getPersons().stream()
				.filter(e -> e.getPerson().getName().equals(firstname))
				.findAny();
		if(!op.isPresent()) {
			throw new NotFoundException("The person " + firstname + " doesn't exist in the event.");
		}
		return op.get();
	}
}
