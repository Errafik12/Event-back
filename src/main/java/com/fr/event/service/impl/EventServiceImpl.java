package com.fr.event.service.impl;

import com.fr.event.domain.Event;
import com.fr.event.domain.res.Response;
import com.fr.event.repository.EventRepository;
import com.fr.event.service.EventService;
import com.fr.event.util.ApiResponse;
import com.fr.event.util.ResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    final
    EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public ResponseEntity<ApiResponse> createEvent(Event event) {
        Response rs = new Response();
        try {
            eventRepository.createEvent(event);
            rs.setOutMsgCode(0);
            rs.setOutMsgDesc("SUCCESS");
            return ResponseBuilder.build(ResponseBuilder.created(rs));
        } catch (Exception ex) {
            rs.setOutMsgCode(1);
            rs.setOutMsgDesc(ex.getMessage());
        }
        return ResponseBuilder.build(ResponseBuilder.badRequest(rs));
    }

    @Override
    public ResponseEntity<ApiResponse> getEventById(Integer eventId) {
        return ResponseBuilder.build(ResponseBuilder.success(eventRepository.getEventById(eventId)));
    }

    @Override
    public ResponseEntity<ApiResponse> getAllEvent() {
        return ResponseBuilder.build(ResponseBuilder.success(eventRepository.getAllEvent()));
    }

    @Override
    public ResponseEntity<ApiResponse> updateEvent(Event event) {
        Response rs = new Response();
        try {
            eventRepository.updateEvent(event);
            rs.setOutMsgCode(0);
            rs.setOutMsgDesc("SUCCESS");
            return ResponseBuilder.build(ResponseBuilder.created(rs));
        } catch (Exception ex) {
            rs.setOutMsgCode(1);
            rs.setOutMsgDesc(ex.getMessage());
        }
        return ResponseBuilder.build(ResponseBuilder.badRequest(rs));
    }
}
