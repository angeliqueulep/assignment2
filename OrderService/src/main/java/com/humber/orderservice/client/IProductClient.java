package com.humber.orderservice.client;

import com.humber.orderservice.DTO.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("product-service")
public interface IProductClient {

    @GetMapping("/products/{id}")
    ProductDTO getProductById(@PathVariable("id") Long id);

    @PutMapping("/products/{id}/updateStock")
    ProductDTO updateStock(@PathVariable("id") Long id, @RequestParam("stock") int stock);

}
