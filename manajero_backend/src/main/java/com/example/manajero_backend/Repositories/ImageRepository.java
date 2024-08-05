package com.example.manajero_backend.Repositories;

import com.example.manajero_backend.Entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
