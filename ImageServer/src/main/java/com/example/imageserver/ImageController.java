package com.example.imageserver;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/images")
public class ImageController {
    private final ImageRepository imageRepository;

    @GetMapping("list")
    public List<Image> getImages() {
        return imageRepository.findAll();
    }

    @GetMapping("{id}")
    public String getImageByName(@PathVariable Long id) {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isEmpty()) {
            return "";
        }

        return image.get().getFilepath();
    }


    @PostMapping("/create")
    public ResponseEntity<String> createImage(@RequestBody Image image) {
        try {
            Image savedImage = imageRepository.save(image);
            return ResponseEntity.status(HttpStatus.CREATED).body("Image created successfully with ID: " + savedImage.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create image: " + e.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteImageByName(@PathVariable Long id) {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete image: " + id);
        }
        imageRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Image deleted successfully with ID: " + id);
    }
}
