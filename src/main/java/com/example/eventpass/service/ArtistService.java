package com.example.eventpass.service;

import com.example.eventpass.entity.Artist;
import com.example.eventpass.entity.Event;
import com.example.eventpass.entity.dto.artist.ArtistResponse;
import com.example.eventpass.entity.dto.artist.CreateArtistRequest;
import com.example.eventpass.entity.dto.event.EventResponse;
import com.example.eventpass.exceptions.EventNotFoundException;
import com.example.eventpass.persistence.ArtistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ModelMapper modelMapper;

    ArtistService(ArtistRepository artistRepository, ModelMapper modelMapper){
        this.artistRepository = artistRepository;
        this.modelMapper = modelMapper;
    }

    public List<ArtistResponse> getAllArtist(){
        return artistRepository.findAll()
                .stream()
                .map(artist -> modelMapper.map(artist, ArtistResponse.class))
                .toList();
    }

    public ArtistResponse findById(Long id){
        Artist artist = artistRepository.findById(id)
                .orElseThrow(()-> new EventNotFoundException("Artist not Found with id: " + id));
        return modelMapper.map(artist, ArtistResponse.class);
    }

    public ArtistResponse createArtist(CreateArtistRequest request){
        Artist artist = modelMapper.map(request, Artist.class);
        Artist createdArtist = artistRepository.save(artist);
        return modelMapper.map(createdArtist, ArtistResponse.class);
    }
}
