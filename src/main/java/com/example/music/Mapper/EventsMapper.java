package com.example.music.Mapper;

import com.example.music.Dto.EventsDto;
import com.example.music.Entity.Events;

public class EventsMapper {
    public static Events mapToEvents (EventsDto eventsdto){
        Events events = new Events();
        events.setVenueName(eventsdto.getVenueName());
        events.setStatus(eventsdto.getStatus());
        events.setLocation(eventsdto.getLocation());
        events.setTitle(eventsdto.getTitle());
        events.setImageUrl(eventsdto.getImageUrl());
        events.setTicketLink(eventsdto.getTicketLink());
        events.setLocationLatitude(eventsdto.getLocationLatitude());
        events.setLocationLongitude(eventsdto.getLocationLongitude());
        events.setStartDate(eventsdto.getStartDate());
        return events;
    }
    public static EventsDto mapToEventsDto (Events events){
        EventsDto eventsdto = new EventsDto();
        eventsdto.setId(events.getId());
        eventsdto.setVenueName(events.getVenueName());
        eventsdto.setStatus(events.getStatus());
        eventsdto.setLocation(events.getLocation());
        eventsdto.setTicketLink(events.getTicketLink());
        eventsdto.setStartDate(events.getStartDate());
        eventsdto.setTitle(events.getTitle());
        eventsdto.setImageUrl(events.getImageUrl());
        eventsdto.setLocationLatitude(events.getLocationLatitude());
        eventsdto.setLocationLongitude(events.getLocationLongitude());
        return eventsdto;

    }
}
