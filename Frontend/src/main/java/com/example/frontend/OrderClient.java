package com.example.frontend;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("order-service")
public interface OrderClient {
    @GetMapping("orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders();

    @PostMapping("orders")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO);
}