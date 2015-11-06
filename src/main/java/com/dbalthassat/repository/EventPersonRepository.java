package com.dbalthassat.repository;

import com.dbalthassat.entity.EventPerson;
import com.dbalthassat.entity.EventPersonId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventPersonRepository extends JpaRepository<EventPerson, EventPersonId> {
}
