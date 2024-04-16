package com.example.frontend.controllers;


import com.example.frontend.Image;
import com.example.frontend.ImageClient;
import com.example.frontend.ProductClient;
import com.example.frontend.ProductDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


// @RestController
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class ApiController {
    private final ImageClient imageClient;
    private final ProductClient productClient;

    @GetMapping("images")
    public List<Image> getImages(){
        return imageClient.getImages();
    }

    @GetMapping("images/{id}")
    public String getImageByName(@PathVariable Long id){
        return imageClient.getImageByName(id);
    }

    @GetMapping("products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return productClient.getAllProducts();
    }

}
