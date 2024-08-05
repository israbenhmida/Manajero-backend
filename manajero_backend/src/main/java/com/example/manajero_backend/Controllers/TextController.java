package com.example.manajero_backend.Controllers;

import com.example.manajero_backend.Entities.Text;
import com.example.manajero_backend.Services.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200",methods={RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE,RequestMethod.GET,RequestMethod.PATCH})
@RequestMapping("/api/texts")
public class TextController {
    @Autowired
    private TextService textService;

    @GetMapping("/all")
    public List<Text> getAllTexts() {
        return textService.getAllTexts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Text> getTextById(@PathVariable Long id) {
        Optional<Text> text = textService.getTextById(id);
        if (text.isPresent()) {
            return ResponseEntity.ok(text.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public Text createText(@RequestBody Text text) {
        return textService.createText(text);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Text> updateText(@PathVariable Long id, @RequestBody Text textDetails) {
        Text updatedText = textService.updateText(id, textDetails);
        if (updatedText != null) {
            return ResponseEntity.ok(updatedText);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteText(@PathVariable Long id) {
        textService.deleteText(id);
        return ResponseEntity.noContent().build();
    }
}
