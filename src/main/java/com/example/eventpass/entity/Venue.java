package com.example.eventpass.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.REMOVE)
    private List<Seat> seats = new ArrayList<>();
}
