package com.example.productservice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Controller
@RequestMapping("product")
public class ProductController {
    private final ImageClient imageClient;
    private final ProductRepository productRepository;

    @GetMapping("")
    public String getHomePage(Model model) {
        //model.addAttribute("products", productRepository.findAll());
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

    @GetMapping("list")
    public List<Product> getProducts(){
        //get the movies from database and return them
        return productRepository.findAll();
    }


}
