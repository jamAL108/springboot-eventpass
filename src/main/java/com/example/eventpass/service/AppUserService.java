package com.example.eventpass.service;

import com.example.eventpass.entity.AppUser;
import com.example.eventpass.entity.dto.AppUser.AppUserResponse;
import com.example.eventpass.entity.dto.AppUser.CreateAppUserRequest;
import com.example.eventpass.exceptions.UserNotFoundException;
import com.example.eventpass.persistence.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;

    AppUserService(AppUserRepository appUserRepository, ModelMapper modelMapper){
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
    }

    public List<AppUserResponse> getAllUsers(){
        return appUserRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, AppUserResponse.class))
                .toList();
    }

    public AppUserResponse findById(Long id){
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("USer not Found with id: " + id));
        return modelMapper.map(user, AppUserResponse.class);
    }

    public AppUserResponse createUser(CreateAppUserRequest request){
        AppUser user = modelMapper.map(request, AppUser.class);
        AppUser createdUser = appUserRepository.save(user);
        return modelMapper.map(createdUser, AppUserResponse.class);
    }
}
