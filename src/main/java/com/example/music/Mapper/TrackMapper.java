package com.example.music.Mapper;

import com.example.music.Dto.TrackDto;
import com.example.music.Entity.Track;

public class TrackMapper {

    public static Track mapToTrack (TrackDto trackDto){
        Track track = new Track();
      track.setType(trackDto.getType());
      track.setVideoId(track.getVideoId());
      track.setStorageUrl(trackDto.getStorageUrl());


        return track;
    }
    public static TrackDto mapToTrackDto (Track track){
        TrackDto trackDto = new TrackDto();
        trackDto.setType(track.getType());
        trackDto.setStorageUrl(track.getStorageUrl());
        trackDto.setVideoId(track.getVideoId());
        return trackDto;
    }
}
