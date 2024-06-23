package dev.mukul.event_manager.DTO;

import dev.mukul.event_manager.models.Event;
import dev.mukul.event_manager.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RegisterUserResponseDto {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private List<Event> registeredEvents = new ArrayList<>();

}
