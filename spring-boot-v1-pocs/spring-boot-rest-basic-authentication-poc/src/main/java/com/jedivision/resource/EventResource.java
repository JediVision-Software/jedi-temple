package com.jedivision.resource;

import com.jedivision.entity.Event;
import com.jedivision.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EventResource {

    private final EventService eventService;

    @Autowired
    public EventResource(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public Iterable<Event> findAll() {
        return eventService.findAll();
    }

    @RequestMapping(value = "/event/{eventId}", method = RequestMethod.GET)
    public Event findById(@PathVariable("eventId") Long eventId) {
        return eventService.findById(eventId);
    }
}
