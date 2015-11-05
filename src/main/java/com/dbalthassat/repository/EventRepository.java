package com.dbalthassat.repository;

import com.dbalthassat.dto.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
