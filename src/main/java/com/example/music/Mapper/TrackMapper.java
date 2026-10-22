package com.example.music.Mapper;

import com.example.music.Dto.TrackDto;
import com.example.music.Entity.Track;

public class TrackMapper {

    public static Track mapToTrack (TrackDto trackDto){
        Track track = new Track();
      track.setType(trackDto.getType());
      track.setVideoId(trackDto.getVideoId());
      track.setStorageUrl(trackDto.getStorageUrl());


        return track;
    }
    public static TrackDto mapToTrackDto (Track track){
        TrackDto trackDto = new TrackDto();
        trackDto.setId(track.getId());
        trackDto.setType(track.getType());
        trackDto.setVideoId(track.getVideoId());
        trackDto.setStorageUrl(track.getStorageUrl());
        return trackDto;
    }
}
