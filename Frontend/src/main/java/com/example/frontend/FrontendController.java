package com.example.frontend;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping("/")
public class FrontendController {
    private final ImageClient imageClient;
    private final ProductClient productClient;

    @GetMapping("")
    public String getHomePage(Model model) {
        //model.addAttribute("products", productClient.getAllProducts());
        return "index";
    }

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
