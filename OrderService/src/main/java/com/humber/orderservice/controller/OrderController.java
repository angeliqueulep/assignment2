package com.humber.orderservice.controller;

import com.humber.orderservice.DTO.OrderDTO;
import com.humber.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    private StreamBridge streamBridge;
    private Logger logger = LoggerFactory.getLogger(OrderController.class);


    // Get all orders
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Create new order
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDto) {
        try {
            OrderDTO newOrder = orderService.createOrder(orderDto);
            logger.info("Sending request to Email Server");
            streamBridge.send("sendPlaceorder-out-0", newOrder);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get a specific order by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        OrderDTO order = orderService.findOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update an order
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDto) {
        try {
            OrderDTO updatedOrder = orderService.updateOrder(id, orderDto);
            if (updatedOrder != null) {
                return ResponseEntity.ok(updatedOrder);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Delete an order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        boolean isDeleted = orderService.deleteOrder(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
