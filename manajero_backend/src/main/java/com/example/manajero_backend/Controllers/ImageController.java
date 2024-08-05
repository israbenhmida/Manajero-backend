package com.example.manajero_backend.Controllers;

import com.example.manajero_backend.Entities.Image;
import com.example.manajero_backend.Repositories.ImageRepository;
import com.example.manajero_backend.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200",methods={RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE,RequestMethod.GET,RequestMethod.PATCH})

@RequestMapping("/api/images")
public class ImageController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/all")
    public List<Image> getAllImages() {
        return imageService.getAllImages();
    }
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
        Optional<Image> imageOpt = imageService.getImageById(id);
        if (imageOpt.isPresent()) {
            Image image = imageOpt.get();
            Path imagePath = Path.of(image.getImagePath());
            try {
                byte[] imageData = Files.readAllBytes(imagePath);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // Adjust as per the actual image type
                return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Image> uploadImage(@RequestParam("image") MultipartFile imageFile) {
        try {
            Image uploadedImage = imageService.uploadImage(imageFile);
            return ResponseEntity.ok(uploadedImage);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
}
