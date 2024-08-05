package com.example.manajero_backend.Repositories;

import com.example.manajero_backend.Entities.Text;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextRepository extends JpaRepository<Text, Long> {
}
