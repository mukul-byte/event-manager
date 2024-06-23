package dev.mukul.event_manager.repositories;

import dev.mukul.event_manager.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Jpa21Utils;

public interface EventRepository extends JpaRepository<Event, Long> {

}
