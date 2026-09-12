package com.example.eventpass.entity.dto.AppUser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppUserRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    private String bio;
}
