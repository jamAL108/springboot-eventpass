package com.example.eventpass.controller;

import com.example.eventpass.entity.AppUser;
import com.example.eventpass.entity.dto.AppUser.AppUserResponse;
import com.example.eventpass.entity.dto.AppUser.CreateAppUserRequest;
import com.example.eventpass.entity.dto.artist.ArtistResponse;
import com.example.eventpass.entity.dto.artist.CreateArtistRequest;
import com.example.eventpass.service.AppUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping()
    public ResponseEntity<List<AppUserResponse>> getAllArtist(){
        List<AppUserResponse> users = appUserService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserResponse> getArtistById(@PathVariable Long id){
        AppUserResponse user = appUserService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<AppUserResponse> createArtist(@Valid @RequestBody CreateAppUserRequest request){
        AppUserResponse user = appUserService.createUser(request);
        return ResponseEntity.status(201).body(user);
    }
}
