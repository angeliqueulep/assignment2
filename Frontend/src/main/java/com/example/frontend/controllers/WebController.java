package com.example.frontend.controllers;


import com.example.frontend.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// @RestController
@AllArgsConstructor
@Controller
@RequestMapping("/")
public class WebController {
    private final ImageClient imageClient;
    private final ProductClient productClient;
    private final ProductOrderRepository productOrderRepository;

    @GetMapping("")
    public String getHomePage(Model model) {
        //model.addAttribute("products", productClient.getAllProducts());
        return "index";
    }
    @GetMapping("view-product/{id}")
    public String getProductPage(@PathVariable Long id, Model model) {
        model.addAttribute("product", productClient.getProductById(id).getBody());
        return "product";
    }
    @GetMapping("view-cart")
    public String getProductPage(Model model) {
        model.addAttribute("cartproducts",cartProducts);
        return "cart";
    }
}
