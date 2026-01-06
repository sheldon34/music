package com.example.music.service;

import com.example.music.Dto.EventsDto;
import com.example.music.Enums.eventStatus;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface EventService  {
    EventsDto createEvent(String title, String venueName, String location, LocalDate startDate, String ticketLink , MultipartFile imageUrl, Double LocationLongitude, Double LocationLatitude , eventStatus status);
    EventsDto getEventById(Long id);
    void deleteEvents(Long id);
    EventsDto updateEvent(Long id , String title, String venueName, String location, LocalDate startDate, String ticketLink , MultipartFile imageUrl, Double LocationLongitude, Double LocationLatitude , eventStatus status);
    List<EventsDto> getAllEvents();
}
