package com.fr.event.service;

import com.fr.event.domain.Event;
import com.fr.event.util.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface EventService {

    ResponseEntity<ApiResponse> createEvent(Event event);

    ResponseEntity<ApiResponse> getEventById(Integer eventId);

    ResponseEntity<ApiResponse> getAllEvent();

    ResponseEntity<ApiResponse> updateEvent(Event event);
}
