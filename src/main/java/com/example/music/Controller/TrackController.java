package com.example.music.Controller;


import com.example.music.Dto.TrackDto;
import com.example.music.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/track/")
public class TrackController {
    private final TrackService trackService;
    @PostMapping("upload")
    public ResponseEntity<TrackDto> createTrack(TrackDto trackDto){
        TrackDto track = trackService.createTrack(trackDto);
        return new  ResponseEntity<>(track, HttpStatus.OK);
    }
    @PatchMapping("update/{id}")
    public ResponseEntity<TrackDto> updateTrack(@PathVariable Long id , TrackDto trackDto){
        TrackDto track=trackService.updateTrack(id, trackDto);
        return new  ResponseEntity<>(track, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<TrackDto>> getAllTracks(){
        List<TrackDto> tracks = trackService.getAllTracks();
        return new ResponseEntity<>(tracks, HttpStatus.OK);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<TrackDto> getById(@PathVariable Long id){
        TrackDto track = trackService.getTrackById(id);
        return new ResponseEntity<>(track, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteTrack(@PathVariable Long id){
        trackService.deleteTrackById(id);
        return new ResponseEntity<>("Track deleted",HttpStatus.OK);
    }
}
