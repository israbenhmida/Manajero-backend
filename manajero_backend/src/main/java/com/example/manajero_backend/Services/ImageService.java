package com.example.manajero_backend.Services;
import com.example.manajero_backend.Entities.Image;
import com.example.manajero_backend.Repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Optional<Image> getImageById(Long id) {
        return imageRepository.findById(id);
    }

    public Image uploadImage(MultipartFile imageFile) throws IOException {
        String imageName = StringUtils.cleanPath(imageFile.getOriginalFilename());
        String imageExtension = StringUtils.getFilenameExtension(imageName);
        String generatedImageName = UUID.randomUUID().toString() + "." + imageExtension;

        Path imagePath = Paths.get("uploads").resolve(generatedImageName);
        Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

        Image image = new Image();
        image.setImageName(imageName);
        image.setImagePath(imagePath.toString());

        return imageRepository.save(image);
    }

    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
}
