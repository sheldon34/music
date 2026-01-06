package com.example.music.service.Imp;

import com.example.music.Dto.EventsDto;
import com.example.music.Entity.Events;
import com.example.music.Enums.eventStatus;
import com.example.music.Mapper.EventsMapper;
import com.example.music.R2Config.R2MediaService;
import com.example.music.Repo.EventsRepo;
import com.example.music.service.EventService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional

public class EventsServiceImp implements EventService {
    private final R2MediaService r2MediaService;
    private final EventsRepo eventsRepo;
    @Override
    public EventsDto createEvent(String title, String venueName, String location, LocalDate startDate, String ticketLink, MultipartFile imageUrl, Double LocationLongitude, Double LocationLatitude, eventStatus status) {
        Events events = new Events();
        events.setTitle(title);
        events.setVenueName(venueName);
        events.setLocation(location);
        events.setStartDate(startDate);
        events.setTicketLink(ticketLink);
        events.setLocationLongitude(LocationLongitude);
        events.setLocationLatitude(LocationLatitude);
        events.setStatus(status);
        if (imageUrl != null) {
            String Image= r2MediaService.uploadFile(imageUrl);
            String imageurl=r2MediaService.getPublicUrl(Image);
            events.setImageUrl(imageurl);
        }
        Events saveEvents= eventsRepo.save(events);

        return EventsMapper.mapToEventsDto(saveEvents);
    }

    @Override
    public EventsDto getEventById(Long id) {
        Events events=eventsRepo.findById(id).orElseThrow(()-> new RuntimeException("Event not found"));
        return EventsMapper.mapToEventsDto(events);
    }

    @Override
    public void deleteEvents(Long id) {
        Events events = eventsRepo.findById(id).orElseThrow(()->new RuntimeException("Event not found"));
        if(events.getImageUrl()!=null){
            r2MediaService.deleteFile(events.getImageUrl());

        }
        eventsRepo.deleteById(id);

    }

    @Override
    public EventsDto updateEvent(Long id, String title, String venueName, String location, LocalDate startDate, String ticketLink, MultipartFile imageUrl, Double LocationLongitude, Double LocationLatitude, eventStatus status) {
        Events events= eventsRepo.findById(id).orElseThrow(()-> new RuntimeException("Event not found"));
        if (events.getTitle()!=null)
        {events.setTitle(title);
        }
        if(events.getVenueName()!=null){
            events.setVenueName(venueName);
        }
        if (events.getLocation()!=null){
            events.setLocation(location);
        }
        if (events.getStartDate()!=null){
            events.setStartDate(startDate);
        }
        if (events.getTicketLink()!=null){
            events.setTicketLink(ticketLink);

        }
        if (events.getLocationLongitude()!=null){
            events.setLocationLongitude(LocationLongitude);
        }
        if (events.getLocationLatitude()!=null){
            events.setLocationLatitude(LocationLatitude);
        }
        if (events.getStatus()!=null){
            events.setStatus(status);
        }
        if (events.getImageUrl()!=null){
            String newImage=r2MediaService.updateFile(events.getImageUrl(),imageUrl);
            String imageurl=r2MediaService.getPublicUrl(newImage);
            events.setImageUrl(imageurl);
        }
        Events savedEvents= eventsRepo.save(events);
        return EventsMapper.mapToEventsDto(savedEvents);
    }

    @Override
    public List<EventsDto> getAllEvents() {
        List<Events> events = eventsRepo.findAll();
        return events.stream().map(EventsMapper::mapToEventsDto).collect(Collectors.toList());
    }
}
