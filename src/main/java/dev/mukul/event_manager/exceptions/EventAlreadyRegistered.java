package dev.mukul.event_manager.exceptions;

public class EventAlreadyRegistered extends RuntimeException {
    public EventAlreadyRegistered(String message) {
        super(message);
    }
}
