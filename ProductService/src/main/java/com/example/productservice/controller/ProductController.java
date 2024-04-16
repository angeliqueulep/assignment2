package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@AllArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    // Fetch all products
    @GetMapping("list")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    //Create a new product
    @PostMapping("create")
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    //Delete a product
    @DeleteMapping("delete/{id}")
    public void deleteProduct(@PathVariable int id){
        productRepository.deleteById(id);
    }

    //Update a product
    @PutMapping("{id}/update")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product){
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
