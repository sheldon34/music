package com.example.music.service.Imp;

import com.example.music.Dto.TrackDto;
import com.example.music.Entity.Track;
import com.example.music.Mapper.TrackMapper;
import com.example.music.Repo.trackRepo;
import com.example.music.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class TrackServiceImp implements TrackService {
    private final trackRepo trackRepo;

    @Override
    public TrackDto createTrack(TrackDto trackDto) {
        Track track = TrackMapper.mapToTrack(trackDto);
        Track savedTrack= trackRepo.save(track);


        return TrackMapper.mapToTrackDto(savedTrack);
    }

    @Override
    public TrackDto getTrackById(Long id) {
        Track track = trackRepo.findById(id).orElseThrow(()->new RuntimeException("Track not found "));

        return TrackMapper.mapToTrackDto(track);
    }

    @Override
    public TrackDto updateTrack(Long id , TrackDto trackDto) {
        Track track = trackRepo.findById(id).orElseThrow(()->new RuntimeException("Track not found "));

        Track updatedTrack = TrackMapper.mapToTrack(trackDto);
        Track savedTrack = trackRepo.save(updatedTrack);
        return TrackMapper.mapToTrackDto(savedTrack);
    }

    @Override
    public void deleteTrackById(Long id) {
        Track track = trackRepo.findById(id).orElseThrow(()->new RuntimeException("Track not found "));
        trackRepo.delete(track);

    }


    @Override
    public List<TrackDto> getAllTracks() {
        List <Track> tracks = trackRepo.findAll();

        return tracks.stream().map(TrackMapper::mapToTrackDto)
                .collect(Collectors.toList());

    }
}
