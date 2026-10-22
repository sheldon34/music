package com.example.music.Dto;

import com.example.music.Enums.TrackSource;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackDto {


    private Long  id;
    private String videoId;
    private TrackSource type;
    private String storageUrl;
}
