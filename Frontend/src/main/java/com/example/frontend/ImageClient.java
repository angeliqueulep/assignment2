package com.example.frontend;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("image-service")
public interface ImageClient {
    @GetMapping("/images/list")
    public List<Image> getImages();

    @GetMapping("/images/{id}")
    public String getImageByName(@PathVariable Long id);
}