package com.example.frontend.controllers;


import com.example.frontend.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// @RestController
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class ApiController {
    private final ImageClient imageClient;
    private final ProductClient productClient;
    private final OrderClient orderClient;


    private final ProductOrderRepository productOrderRepository;

    @GetMapping("images")
    public List<Image> getImages(){
        return imageClient.getImages();
    }


    @PostMapping("addToCart")
    public ResponseEntity addProductToCart(@RequestBody ProductOrder productOrder){
        try {
            ProductOrder savedOrder = productOrderRepository.save(productOrder);
            System.out.println("Added product to cart! ProductID: " + savedOrder.getId());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Failed to create image: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("listCart")
    public List<ProductOrder> getCartItems() {
        return productOrderRepository.findAll();
    }

    @GetMapping("images/{id}")
    public String getImageByName(@PathVariable Long id){
        return imageClient.getImageByName(id);
    }

    @GetMapping("products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return productClient.getAllProducts();
    }

    @PostMapping("createOrder")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        return orderClient.createOrder(orderDTO);
    }

}
