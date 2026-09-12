package com.example.eventpass.service;

import com.example.eventpass.entity.dto.AppUser.AppUserResponse;
import com.example.eventpass.entity.dto.AppUser.CreateAppUserRequest;

import java.util.List;

public interface AppUserService {

    List<AppUserResponse> getAllUsers();
    AppUserResponse findById(Long id);
    AppUserResponse createUser(CreateAppUserRequest request);
}
