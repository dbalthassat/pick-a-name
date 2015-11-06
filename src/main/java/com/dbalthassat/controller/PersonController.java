package com.dbalthassat.controller;

import com.dbalthassat.entity.Event;
import com.dbalthassat.entity.EventPerson;
import com.dbalthassat.entity.Person;
import com.dbalthassat.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/person")
public class PersonController {
	@Autowired
	private PersonRepository personRepository;

	@RequestMapping(value = {"", "/"}, produces = "application/json; charset=utf-8")
	@Transactional
	public Person person() {
		Person person = personRepository.findOne(1L);
		System.out.println(">>>>>>>>>>>>>>>>>>>> " + person.getEventPersons().size());
		return person;
	}
}
