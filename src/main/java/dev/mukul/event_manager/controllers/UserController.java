package dev.mukul.event_manager.controllers;

import dev.mukul.event_manager.DTO.RegisterEventDTO;
import dev.mukul.event_manager.models.User;
import dev.mukul.event_manager.repositories.UserRepository;
import dev.mukul.event_manager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User createUser(@RequestBody User userDetails){
        return this.userService.createUser(userDetails);
    }

    @PostMapping("/register")
    public User createUser(@RequestBody RegisterEventDTO registerEventDTO){
        try {
            return this.userService.registerEvent(registerEventDTO);
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }


}
