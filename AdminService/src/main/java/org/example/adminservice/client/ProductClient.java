package org.example.adminservice.client;

import org.example.adminservice.DTO.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("product-service")
public interface ProductClient {

    @GetMapping("/products")
    public List<ProductDTO> getAllProducts();

    @GetMapping("/products/{id}")
    ProductDTO getProductById(@PathVariable("id") Long id);

    @PostMapping("/products")
    ProductDTO createProduct(@RequestBody ProductDTO productDTO);

    @PutMapping("/products/{id}")
    ProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO);

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable("id") Long id);

}
