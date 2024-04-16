package com.example.frontend.controllers;


import com.example.frontend.Image;
import com.example.frontend.ImageClient;
import com.example.frontend.ProductClient;
import com.example.frontend.ProductDTO;
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

    @GetMapping("")
    public String getHomePage(Model model) {
        //model.addAttribute("products", productClient.getAllProducts());
        return "index";
    }

}
