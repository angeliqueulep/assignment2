package com.example.imageserver;


import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DBSeeder implements CommandLineRunner {
    private final ImageRepository imageRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Image> images = List.of(
                Image.builder().name("jacket").filepath("https://www.madsnorgaard.com/cdn/shop/files/203372_8978_1.jpg?v=1712929973&width=533").build(),
                Image.builder().name("coat").filepath("https://www.alanpaine-europe.com/cdn/shop/files/rutland-ladies-tweed-waterproof-shooting-coat-lichen.jpg?v=1711480533&width=533").build(),
                Image.builder().name("hoodie").filepath("https://img.abercrombie.com/is/image/anf/KIC_122-4100-0232-616_prod1?policy=product-medium").build()
        );
        imageRepository.saveAll(images);

        imageRepository.findAll().forEach((image ->
                System.out.println(image.getId() + " " + image.getName())
        ));
    }
}
