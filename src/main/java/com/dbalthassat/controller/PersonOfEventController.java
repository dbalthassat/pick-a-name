package com.dbalthassat.controller;

import com.dbalthassat.dto.PersonOfEventDTO;
import com.dbalthassat.dto.PersonDTO;
import com.dbalthassat.exception.BadRequestException;
import com.dbalthassat.exception.NotFoundException;
import com.dbalthassat.service.PersonOfEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("events/{eventId}/persons")
public class PersonOfEventController {
	@Autowired
	private PersonOfEventService personOfEventService;

	@RequestMapping(value = "/{name}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public PersonOfEventDTO findEventPerson(@PathVariable Long eventId, @PathVariable String name) throws NotFoundException {
		return personOfEventService.find(eventId, name);
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.PUT, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public PersonDTO findFriend(@PathVariable Long eventId, @PathVariable String name) throws NotFoundException, BadRequestException {
		return personOfEventService.findFriend(eventId, name);
	}
}
