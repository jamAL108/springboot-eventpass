package com.example.eventpass.service.Impl;

import com.example.eventpass.entity.Artist;
import com.example.eventpass.entity.dto.artist.ArtistResponse;
import com.example.eventpass.entity.dto.artist.CreateArtistRequest;
import com.example.eventpass.exceptions.EventNotFoundException;
import com.example.eventpass.persistence.ArtistRepository;
import com.example.eventpass.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ArtistResponse> getAllArtist(){
        return artistRepository.findAll()
                .stream()
                .map(artist -> modelMapper.map(artist, ArtistResponse.class))
                .toList();
    }

    @Override
    public ArtistResponse findById(Long id){
        Artist artist = artistRepository.findById(id)
                .orElseThrow(()-> new EventNotFoundException("Artist not Found with id: " + id));
        return modelMapper.map(artist, ArtistResponse.class);
    }

    @Override
    public ArtistResponse createArtist(CreateArtistRequest request){
        Artist artist = modelMapper.map(request, Artist.class);
        Artist createdArtist = artistRepository.save(artist);
        return modelMapper.map(createdArtist, ArtistResponse.class);
    }
}
