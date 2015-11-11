package com.dbalthassat.service;

import com.dbalthassat.dto.EventDTO;
import com.dbalthassat.entity.Event;
import com.dbalthassat.exception.NotFoundException;
import com.dbalthassat.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
	@Autowired
	private EventEntityService entityService;

	public EventDTO create(EventDTO event) {
		Event entity = EventMapper.map(event);
		EventDTO dto = EventMapper.map(entityService.create(entity));
		return EventMapper.mapPersons(entity, dto);
	}

	public EventDTO find(Long id) throws NotFoundException {
		Event event = entityService.find(id);
		if(event == null) {
			throw new NotFoundException("The event " + id + " doesn't exist.");
		}
		return EventMapper.map(event);
	}
}
