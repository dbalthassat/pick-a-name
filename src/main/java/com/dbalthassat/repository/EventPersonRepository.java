package com.dbalthassat.repository;

import com.dbalthassat.entity.PersonOfEvent;
import com.dbalthassat.entity.PersonOfEventId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventPersonRepository extends JpaRepository<PersonOfEvent, PersonOfEventId> {
}
