package com.humber.orderservice.client;

import com.humber.orderservice.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("product-service")
public interface IProductClient {

    @GetMapping("/product/list")
    public List<Product> getProducts();
}
