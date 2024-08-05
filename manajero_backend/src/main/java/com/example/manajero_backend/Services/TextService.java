package com.example.manajero_backend.Services;

import com.example.manajero_backend.Entities.Text;
import com.example.manajero_backend.Repositories.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TextService {
    @Autowired
    private TextRepository textRepository;

    public List<Text> getAllTexts() {
        return textRepository.findAll();
    }

    public Optional<Text> getTextById(Long id) {
        return textRepository.findById(id);
    }

    public Text createText(Text text) {
        return textRepository.save(text);
    }

    public Text updateText(Long id, Text textDetails) {
        Optional<Text> optionalText = textRepository.findById(id);
        if (optionalText.isPresent()) {
            Text text = optionalText.get();
            text.setDescription(textDetails.getDescription());
            return textRepository.save(text);
        }
        return null;
    }

    public void deleteText(Long id) {
        textRepository.deleteById(id);
    }
}
