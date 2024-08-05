package com.example.manajero_backend.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long IdImage;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "image_name")
    private String imageName;
}
