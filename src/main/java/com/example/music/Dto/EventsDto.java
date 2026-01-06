package com.example.music.Dto;

import com.example.music.Enums.eventStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.NamedEntityGraph;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventsDto {
    private Long  id;
    private String title;
    private String venueName;
    private String location;
    private LocalDate startDate;
    private String ticketLink;
    private String imageUrl;
    private Double LocationLongitude;
    private Double LocationLatitude;
    private eventStatus status;

}
