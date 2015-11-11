package com.dbalthassat.mapper;

import com.dbalthassat.dto.EventPersonDisplayDTO;
import com.dbalthassat.entity.EventPerson;

/**
 * ${END}
 *
 * @author dbalthassat
 */
public class EventPersonMapper {
	public static EventPersonDisplayDTO map(EventPerson eventPerson) {
		if(eventPerson == null) {
			return null;
		}
		EventPersonDisplayDTO dto = new EventPersonDisplayDTO();
		dto.setEventId(eventPerson.getEvent().getId());
		dto.setPerson(PersonMapper.map(eventPerson));
		return dto;
	}
}
