package com.jedivision.service;

import com.jedivision.entity.Event;

public interface EventService {
    Iterable<Event> findAll();
    Event findById(Long eventId);
}
