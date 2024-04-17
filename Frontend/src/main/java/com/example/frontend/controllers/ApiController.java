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
import java.util.stream.Collectors;


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

    @GetMapping("images/{id}")
    public String getImageByName(@PathVariable Long id){
        return imageClient.getImageByName(id);
    }

    @GetMapping("products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> allProducts = productClient.getAllProducts();

        List<ProductDTO> productsInStock = allProducts.stream()
                .filter(product -> product.getStock() > 0)
                .collect(Collectors.toList());

        return ResponseEntity.ok(productsInStock);
    }

    @PostMapping("createOrder")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        return orderClient.createOrder(orderDTO);
    }

    @GetMapping("orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return orderClient.getAllOrders();
    }


    @PostMapping("addToCart")
    public ResponseEntity<?> addProductToCart(@RequestBody ProductOrder productOrder){
        try {
            ProductDTO product = productClient.getProductById(productOrder.getProductId());

            assert product != null;
            productOrder.setProductName(product.getName());
            productOrder.setProductDescription(product.getDescription());
            productOrder.setProductPrice(product.getPrice());

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

    @PostMapping("clearCart")
    public ResponseEntity<?> clearCart() {
        productOrderRepository.deleteAll();
        return ResponseEntity.ok().build();
    }



}
