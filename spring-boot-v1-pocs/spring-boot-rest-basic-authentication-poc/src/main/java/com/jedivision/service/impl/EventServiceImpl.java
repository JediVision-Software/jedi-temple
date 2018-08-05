package com.jedivision.service.impl;

import com.jedivision.entity.Event;
import com.jedivision.repository.EventRepository;
import com.jedivision.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Iterable<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(Long eventId) {
        return eventRepository.findOne(eventId);
    }
}
