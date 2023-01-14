package com.fr.event.controller;

import com.fr.event.domain.Event;
import com.fr.event.service.EventService;
import com.fr.event.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/event")
public class EventController {

    final
    EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping("/getEventById/{eventId}")
    public ResponseEntity<ApiResponse> getEventById(@PathVariable("eventId") Integer eventId) {
        return eventService.getEventById(eventId);
    }

    @GetMapping("/getAllEvent")
    public ResponseEntity<ApiResponse> getAllEvent() {
        return eventService.getAllEvent();
    }

    @PostMapping("/updateEvent")
    public ResponseEntity<ApiResponse> updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }

}
