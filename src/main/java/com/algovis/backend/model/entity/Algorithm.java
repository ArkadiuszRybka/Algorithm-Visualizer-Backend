package com.algovis.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter @Getter @ToString
@NoArgsConstructor
@Table(name = "algorithm")
public class Algorithm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String description;
}
