package dev.mukul.event_manager.services;

import dev.mukul.event_manager.DTO.RegisterEventDTO;
import dev.mukul.event_manager.exceptions.EventAlreadyRegistered;
import dev.mukul.event_manager.exceptions.EventNotExistException;
import dev.mukul.event_manager.exceptions.UserNotExistException;
import dev.mukul.event_manager.models.Event;
import dev.mukul.event_manager.models.Role;
import dev.mukul.event_manager.models.User;
import dev.mukul.event_manager.repositories.EventRepository;
import dev.mukul.event_manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;


    public User createUser(User userDetails) {
        userDetails.setRole(Role.USER);

        return this.userRepository.save(userDetails);
    }

    public User registerEvent(RegisterEventDTO registerEventDTO) {
        //validate user
        Optional<User> user = this.userRepository.findById(registerEventDTO.getUserId());
        if(user.isEmpty()){
            throw new UserNotExistException();
        }

        //validate event
        Optional<Event> event = this.eventRepository.findById(registerEventDTO.getEventId());
        if(event.isEmpty()){
            throw new EventNotExistException();
        }

        for(Event e: user.get().getRegisteredEvents()){
            if(e.getId().equals(registerEventDTO.getEventId())){
                throw new EventAlreadyRegistered("Event already registered");
            }
        }
        user.get().getRegisteredEvents().add(event.get());
        return this.userRepository.save(user.get());
    }
}
