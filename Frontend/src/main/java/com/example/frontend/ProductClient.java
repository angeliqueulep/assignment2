package com.example.frontend;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("product-service")
public interface ProductClient {
    @GetMapping("products")
    public ResponseEntity<List<ProductDTO>> getAllProducts();
//
//    @GetMapping("/images/{id}")
//    public String getImageByName(@PathVariable Long id);
}