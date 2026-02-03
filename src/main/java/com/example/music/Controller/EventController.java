package com.example.music.Controller;

import com.example.music.Dto.EventsDto;
import com.example.music.Enums.eventStatus;
import com.example.music.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
@PostMapping(value = "/upload", consumes = "multipart/form-data")
  public ResponseEntity<EventsDto> createEvent(@RequestParam ("title")String title,
                                        @RequestParam("venueName") String venueName,
                                         @RequestParam ("location")String location,
                                               @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                        @RequestParam ("ticketLink")String ticketLink ,
                                        @RequestParam ("imageUrl") MultipartFile imageUrl,
                                        @RequestParam ("LocationLongitude") Double LocationLongitude,
                                         @RequestParam("LocationLatitude") Double LocationLatitude ,
                                        @RequestParam("status") eventStatus status){
      EventsDto eventDto = eventService.createEvent(title, venueName,location, startDate, ticketLink , imageUrl, LocationLongitude,  LocationLatitude , status);
      return new ResponseEntity<>(eventDto, HttpStatus.CREATED);


  }

@PatchMapping(value = "/update/{id}", consumes = "multipart/form-data")
public ResponseEntity<EventsDto> updateEvent( @PathVariable Long id ,
                                                 @RequestParam (value = "title",required = false)String title,
                                               @RequestParam(value = "venueName",required = false) String venueName,
                                               @RequestParam (value = "location",required = false)String location,
                                              @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                               @RequestParam (value = "ticketLink",required = false)String ticketLink ,
                                               @RequestParam (value = "imageUrl",required = false) MultipartFile imageUrl,
                                               @RequestParam (value = "LocationLongitude",required = false) Double LocationLongitude,
                                               @RequestParam(value = "LocationLatitude",required = false) Double LocationLatitude ,
                                               @RequestParam(value = "status",required = false) eventStatus status){
    EventsDto eventsDto=eventService.updateEvent(id,title, venueName,location, startDate, ticketLink , imageUrl, LocationLongitude,  LocationLatitude , status);
    System.out.println("ticketLink: " + eventsDto.getTicketLink());
    System.out.println("startDate: " + eventsDto.getStartDate());
    return  new ResponseEntity<>(eventsDto, HttpStatus.OK);
}
@GetMapping("/getAll")
public ResponseEntity<List<EventsDto>> getAllEvents(){
    List<EventsDto> eventsDto=eventService.getAllEvents();
    return new ResponseEntity<>(eventsDto, HttpStatus.OK);
}
@GetMapping("/get/{id}")
public ResponseEntity<EventsDto> getById(@PathVariable Long id){
    EventsDto events=eventService.getEventById(id);
    return new ResponseEntity<>(events,HttpStatus.OK);
}
@DeleteMapping("/delete/{id}")

    public ResponseEntity<String>deleteEvent(@PathVariable Long id){
    eventService.deleteEvents(id);
    return new ResponseEntity<>("Event deleted",HttpStatus.OK);
    }
}
