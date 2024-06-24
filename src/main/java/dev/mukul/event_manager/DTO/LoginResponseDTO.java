package dev.mukul.event_manager.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private String email;
    private String token;

    public LoginResponseDTO(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
