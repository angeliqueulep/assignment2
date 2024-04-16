package org.example.adminservice.client;

import org.example.adminservice.DTO.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("order-service")
public interface OrderClient {

    @GetMapping("/orders")
    public List<OrderDTO> getAllOrders();

    @GetMapping("/orders/{id}")
    OrderDTO getOrderById(@PathVariable("id") Long id);
}
