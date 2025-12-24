package com.example.music.Entity;


import com.example.music.Enums.eventStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Events")

public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long  id;
private String title;
private String venueName;
private String location;
private LocalDate startDate;
private String ticketLink;
private String imageUrl;
@Enumerated(EnumType.STRING)
private eventStatus status=eventStatus.UPCOMING;
}