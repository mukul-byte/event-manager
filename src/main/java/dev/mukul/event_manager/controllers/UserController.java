package dev.mukul.event_manager.controllers;

import dev.mukul.event_manager.DTO.*;
import dev.mukul.event_manager.models.User;
import dev.mukul.event_manager.repositories.UserRepository;
import dev.mukul.event_manager.services.UserService;
import dev.mukul.event_manager.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/")
    public ResponseEntity<RegisterUserResponseDto> createUser(@RequestBody User userDetails){
        User user =  this.userService.createUser(userDetails);
        RegisterUserResponseDto registerUserResponseDto = new RegisterUserResponseDto();
        registerUserResponseDto.setId(user.getId());
        registerUserResponseDto.setName(user.getName());
        registerUserResponseDto.setEmail(user.getEmail());
        registerUserResponseDto.setRole(user.getRole());
        return new ResponseEntity<RegisterUserResponseDto>(registerUserResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/register-event")
    public ResponseEntity<RegisterEventResponseDto> registerEvent(@RequestBody RegisterEventDTO registerEventDTO){
        try {
            User user =  this.userService.registerEvent(registerEventDTO);
            RegisterEventResponseDto registerEventResponseDto = new RegisterEventResponseDto();
            registerEventResponseDto.setId(user.getId());
            registerEventResponseDto.setName(user.getName());
            registerEventResponseDto.setEmail(user.getEmail());
            registerEventResponseDto.setRole(user.getRole());
            registerEventResponseDto.setRegisteredEvents(user.getRegisteredEvents());

            return new ResponseEntity<RegisterEventResponseDto>(registerEventResponseDto, HttpStatus.CREATED);
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }


    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        User user = this.userService.findUser(loginRequest.getEmail());
        String token = JwtHelper.generateToken(loginRequest.getEmail(), user.getRole());
        return ResponseEntity.ok(new LoginResponseDTO(loginRequest.getEmail(), token));
    }


}
