package com.dbalthassat.controller;

import com.dbalthassat.dto.Event;
import com.dbalthassat.dto.Person;
import com.dbalthassat.repository.EventRepository;
import com.dbalthassat.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(value = {"", "/"}, produces = "application/json; charset=utf-8")
    public Event event() {
        Person p1 = new Person(1L, "toto");
        Person p2 = new Person(2L, "tata");
        List<Person> names = Arrays.asList(p1, p2);
        Event e = new Event(1L, names);
        e.setName("tutu");
        Event e2 = new Event(2L, null);
        personRepository.save(p1);
        personRepository.save(p2);
        eventRepository.save(e);
        eventRepository.save(e2);
        return e;
    }

    @RequestMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public Event findEvent(@PathVariable Long id) {
      return eventRepository.findOne(id);
    }

}
