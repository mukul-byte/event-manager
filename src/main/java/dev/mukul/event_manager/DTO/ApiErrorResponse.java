package dev.mukul.event_manager.DTO;

public record ApiErrorResponse(
    int errorCode,
    String description) {}
