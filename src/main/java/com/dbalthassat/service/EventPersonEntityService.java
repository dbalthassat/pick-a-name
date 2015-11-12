package com.dbalthassat.service;

import com.dbalthassat.entity.PersonOfEvent;
import com.dbalthassat.repository.EventPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventPersonEntityService {
	@Autowired
	private EventPersonRepository eventPersonRepository;

	public PersonOfEvent save(PersonOfEvent ep) {
		return eventPersonRepository.save(ep);
	}
}
