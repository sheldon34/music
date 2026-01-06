package com.example.music.service;

import com.example.music.Dto.TrackDto;

import java.util.List;

public interface TrackService {
    TrackDto createTrack(TrackDto trackDto);
    TrackDto getTrackById(Long id);
//    TrackDto getTrackByName(String name);
    TrackDto updateTrack( Long id , TrackDto trackDto);
    void   deleteTrackById(Long id);
    List<TrackDto> getAllTracks();
}
