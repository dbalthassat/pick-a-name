package com.dbalthassat.controller;

import com.dbalthassat.dto.EventPersonDisplayDTO;
import com.dbalthassat.dto.PersonDTO;
import com.dbalthassat.exception.BadRequestException;
import com.dbalthassat.exception.NotFoundException;
import com.dbalthassat.service.EventPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("events/{eventId}/persons")
public class EventPersonController {
	@Autowired
	private EventPersonService eventPersonService;

	@RequestMapping(value = "/{name}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public EventPersonDisplayDTO findEventPerson(@PathVariable Long eventId, @PathVariable String name) throws NotFoundException {
		return eventPersonService.find(eventId, name);
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.PUT, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public PersonDTO findFriend(@PathVariable Long eventId, @PathVariable String name) throws NotFoundException, BadRequestException {
		return eventPersonService.findFriend(eventId, name);
	}
}
