package com.example.imageserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.function.Function;

@Configuration
public class FunctionConfiguration {
    private final Logger logger =
            LoggerFactory.getLogger(FunctionConfiguration.class);
    ImageRepository imageRepository;
    public FunctionConfiguration(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    @Bean
    Function<ProductDTO, Boolean> email(){
        return (ProductDTO product)->{
            Long imageId = product.getImageId();
            logger.info("Deleting Image with ID: " + imageId);

            Optional<Image> optionalImage = imageRepository.findById(imageId);
            if (optionalImage.isEmpty()) {
                return false;
            }
            imageRepository.delete(optionalImage.get());
            return true;
        };
    }

}