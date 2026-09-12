package com.example.eventpass.entity.dto.AppUser;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserResponse {

    private Long id;

    private String name;
    private String email;
    private String bio;
}
