package com.dbalthassat.controller;

import com.dbalthassat.dto.EventPersonDisplayDTO;
import com.dbalthassat.exception.NotFoundException;
import com.dbalthassat.service.EventPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("events/{eventId}/persons")
public class EventPersonController {
	@Autowired
	private EventPersonService eventPersonService;

	@RequestMapping(value = "/{firstname}", produces = "application/json; charset=utf-8")
	public EventPersonDisplayDTO findEventPerson(@PathVariable Long eventId, @PathVariable String firstname) throws NotFoundException {
		return eventPersonService.find(eventId, firstname);
	}
}
