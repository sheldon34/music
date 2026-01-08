package com.example.music.Entity;

import com.example.music.Enums.TrackSource;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String videoId;
    @Enumerated(EnumType.STRING)
    private TrackSource type=TrackSource.YOUTUBE;
    private String storageUrl;
}
