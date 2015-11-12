package com.dbalthassat.mapper;

import com.dbalthassat.dto.PersonOfEventDTO;
import com.dbalthassat.entity.PersonOfEvent;

/**
 * ${END}
 *
 * @author dbalthassat
 */
public class EventPersonMapper {
	public static PersonOfEventDTO map(PersonOfEvent personOfEvent) {
		if(personOfEvent == null) {
			return null;
		}
		PersonOfEventDTO dto = new PersonOfEventDTO();
		dto.setEventId(personOfEvent.getEvent().getId());
		dto.setPerson(PersonMapper.map(personOfEvent));
		return dto;
	}
}
