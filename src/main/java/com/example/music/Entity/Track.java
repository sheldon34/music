package com.example.music.Entity;

import com.example.music.Enums.trackType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;

public class Track {
    private Long  id;
    private String title;
    private String artist;
    @Enumerated(EnumType.STRING)
    private trackType type =trackType.ORIGINAL;
    private Date releaseDate;

}
