package com.example.manajero_backend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Text implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long IdText;
    private String description;

}
