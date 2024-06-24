package dev.mukul.event_manager.controllers;

import dev.mukul.event_manager.models.Event;
import dev.mukul.event_manager.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/")
    public Event createEvent(@RequestBody  Event event){
        return this.eventService.create(event);
    }
}
