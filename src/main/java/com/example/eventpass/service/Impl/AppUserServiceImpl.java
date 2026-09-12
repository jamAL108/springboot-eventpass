package com.example.eventpass.service.Impl;

import com.example.eventpass.entity.AppUser;
import com.example.eventpass.entity.dto.AppUser.AppUserResponse;
import com.example.eventpass.entity.dto.AppUser.CreateAppUserRequest;
import com.example.eventpass.exceptions.UserNotFoundException;
import com.example.eventpass.persistence.AppUserRepository;
import com.example.eventpass.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AppUserResponse> getAllUsers(){
        return appUserRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, AppUserResponse.class))
                .toList();
    }

    @Override
    public AppUserResponse findById(Long id){
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("USer not Found with id: " + id));
        return modelMapper.map(user, AppUserResponse.class);
    }

    @Override
    public AppUserResponse createUser(CreateAppUserRequest request){
        AppUser user = modelMapper.map(request, AppUser.class);
        AppUser createdUser = appUserRepository.save(user);
        return modelMapper.map(createdUser, AppUserResponse.class);
    }
}
