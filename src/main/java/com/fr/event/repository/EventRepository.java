package com.fr.event.repository;

import com.fr.event.domain.Event;

import java.util.List;

public interface EventRepository {

    void createEvent(Event event);

    Event getEventById(Integer eventId);

    List<Event> getAllEvent();

    void updateEvent(Event event);
}
