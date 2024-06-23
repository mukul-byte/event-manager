package dev.mukul.event_manager.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterEventDTO {
    private Long userId;
    private Long eventId;
}
