package dev.mukul.event_manager.services;

import dev.mukul.event_manager.models.Event;
import dev.mukul.event_manager.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event create(Event event) {
        return this.eventRepository.save(event);
    }
}
