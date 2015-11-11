package com.dbalthassat.mapper;

import com.dbalthassat.dto.PersonDTO;
import com.dbalthassat.entity.EventPerson;
import com.dbalthassat.entity.Person;

import java.util.Objects;

public class PersonMapper {
	public static PersonDTO map(EventPerson eventPerson) {
		if(eventPerson == null) {
			return null;
		}
		PersonDTO dto = map(eventPerson.getPerson());
		dto.setHasFriend(eventPerson.getFriend() != null);
		return dto;
	}

	private static PersonDTO map(Person person) {
		if(person == null) {
			return null;
		}
		PersonDTO dto = new PersonDTO();
		dto.setId(person.getId());
		dto.setName(person.getName());
		return dto;
	}

	public static PersonDTO mapFriend(EventPerson op, PersonDTO person) {
		Objects.requireNonNull(op);
		Objects.requireNonNull(person);
		person.setFriend(op.getFriend() != null ? op.getFriend().getName() : "");
		return person;
	}
}
