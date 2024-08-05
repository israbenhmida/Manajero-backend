package com.example.manajero_backend.Repositories;

import com.example.manajero_backend.Entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
