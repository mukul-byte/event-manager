package dev.mukul.event_manager.configs;

import dev.mukul.event_manager.exceptions.UserNotExistException;
import dev.mukul.event_manager.models.Role;
import dev.mukul.event_manager.models.User;
import dev.mukul.event_manager.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = repository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(getAuthorities(user.getRole()))
                .build();
    }
    private Set<GrantedAuthority> getAuthorities(Role r) {
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                .collect(Collectors.toSet());

        Set<GrantedAuthority> set = new HashSet<GrantedAuthority>();
        SimpleGrantedAuthority role = new SimpleGrantedAuthority(""+r);
        set.add(role);
        return set;
    }
}