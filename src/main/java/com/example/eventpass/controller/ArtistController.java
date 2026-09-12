package com.example.eventpass.controller;

import com.example.eventpass.entity.dto.artist.ArtistResponse;
import com.example.eventpass.entity.dto.artist.CreateArtistRequest;
import com.example.eventpass.service.Impl.ArtistServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artist")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistServiceImpl artistService;

    @GetMapping()
    public ResponseEntity<List<ArtistResponse>> getAllArtist(){
        List<ArtistResponse> artists = artistService.getAllArtist();
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponse> getArtistById(@PathVariable Long id){
        ArtistResponse artist = artistService.findById(id);
        return ResponseEntity.ok(artist);
    }

    @PostMapping()
    public ResponseEntity<ArtistResponse> createArtist(@Valid @RequestBody CreateArtistRequest request){
        ArtistResponse artist = artistService.createArtist(request);
        return ResponseEntity.status(201).body(artist);
    }
}
