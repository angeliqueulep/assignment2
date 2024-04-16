package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@AllArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    // Fetch all products
    @GetMapping
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    //Create a new product
    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    // Get Product by ID
    @GetMapping("{id}")
    public Product getProductById(@PathVariable Long id){
        return productRepository.findById(id).orElse(null);
    }

    //Delete a product
    @DeleteMapping("delete/{id}")
    public void deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
    }

    //Update a product
    @PutMapping("{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null){
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStock(product.getStock());
            existingProduct.setImageId(product.getImageId());
            return productRepository.save(existingProduct);
        }
        return null;
    }

}
