package com.example.manajero_backend.Services;

import com.example.manajero_backend.Entities.Question;
import com.example.manajero_backend.Repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public Question updateQuestion(Long id, Question updatedQuestion) {
        if (questionRepository.existsById(id)) {
            updatedQuestion.setIdQuestion(id);
            return questionRepository.save(updatedQuestion);
        }
        return null;
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
