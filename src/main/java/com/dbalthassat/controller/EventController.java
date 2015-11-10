package com.dbalthassat.controller;

import com.dbalthassat.dto.EventDTO;
import com.dbalthassat.dto.groups.Create;
import com.dbalthassat.exception.BadRequestException;
import com.dbalthassat.mapper.EventMapper;
import com.dbalthassat.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {
	@Autowired
	private EventService eventService;

	@SuppressWarnings("MVCPathVariableInspection")
	@RequestMapping(value = {"/", "/{id}"}, method = RequestMethod.POST, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@Transactional
	public EventDTO createEvent(@Validated(Create.class) @RequestBody EventDTO event,
							BindingResult result) throws BadRequestException {
		if(result.hasErrors()) {
			throw new BadRequestException(result.getAllErrors().toString());
		}
		return EventMapper.map(eventService.create(EventMapper.map(event)));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public EventDTO findEvent(@PathVariable Long id) {
		return EventMapper.mapToDisplay(eventService.find(id));
	}
}
