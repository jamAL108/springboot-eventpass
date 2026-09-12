package com.example.eventpass.service;

import com.example.eventpass.entity.dto.artist.ArtistResponse;
import com.example.eventpass.entity.dto.artist.CreateArtistRequest;

import java.util.List;

public interface ArtistService {

    List<ArtistResponse> getAllArtist();
    ArtistResponse findById(Long id);
    ArtistResponse createArtist(CreateArtistRequest request);
}
