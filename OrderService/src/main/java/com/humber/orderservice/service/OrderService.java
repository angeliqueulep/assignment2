package com.humber.orderservice.service;

import com.humber.orderservice.DTO.OrderDTO;
import com.humber.orderservice.DTO.OrderItemDTO;
import com.humber.orderservice.DTO.ProductDTO;
import com.humber.orderservice.client.IProductClient;
import com.humber.orderservice.entity.Order;
import com.humber.orderservice.entity.OrderItem;
import com.humber.orderservice.repository.OrderItemRepository;
import com.humber.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private IProductClient productClient;

    // Creating new order
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderDate(orderDTO.getOrderDate());

        List<OrderItem> orderItems = orderDTO.getOrderItems().stream()
                .map(dto -> {
                    OrderItem item = new OrderItem();
                    ProductDTO product = productClient.getProductById(dto.getProductId());
                    if (product.getStock() < dto.getQuantity()) {
                        throw new IllegalStateException("Insufficient stock for product " + dto.getProductId());
                    }
                    item.setOrder(order);
                    item.setProductId(dto.getProductId());
                    item.setQuantity(dto.getQuantity());
                    item.setPrice(product.getPrice());
                    return item;
                }).collect(Collectors.toList());

        double totalPrice = orderItems.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);

        orderItems.forEach(item -> productClient.updateStock(item.getProductId(), -item.getQuantity()));

        Order savedOrder = orderRepository.save(order);
        return mapToDto(savedOrder);
    }

    private OrderDTO mapToDto(Order order) {
        List<OrderItemDTO> itemDTOs = order.getOrderItems().stream().map(item -> {
            ProductDTO productDTO = productClient.getProductById(item.getProductId());
            return new OrderItemDTO(item.getId(), order.getId(), productDTO, item.getQuantity(), item.getPrice());
        }).collect(Collectors.toList());

        return new OrderDTO(order.getId(), order.getOrderDate(), order.getTotalPrice(), itemDTOs);
    }

    // update order
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Order not found"));

        List<OrderItem> orderItems = orderDTO.getOrderItems().stream()
                .map(dto -> {
                    OrderItem item = new OrderItem();
                    ProductDTO product = productClient.getProductById(dto.getProductId());
                    if (product.getStock() < dto.getQuantity()) {
                        throw new IllegalStateException("Insufficient stock for product " + dto.getProductId());
                    }
                    item.setOrder(order);
                    item.setProductId(dto.getProductId());
                    item.setQuantity(dto.getQuantity());
                    item.setPrice(product.getPrice());
                    return item;
                }).collect(Collectors.toList());

        double totalPrice = orderItems.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);

        orderItems.forEach(item -> productClient.updateStock(item.getProductId(), -item.getQuantity()));

        Order savedOrder = orderRepository.save(order);
        return mapToDto(savedOrder);
    }

    // delete order
    public boolean deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Order not found"));

        order.getOrderItems().forEach(item -> productClient.updateStock(item.getProductId(), item.getQuantity()));
        orderRepository.delete(order);
        return true;
    }

    // find order by id
    public OrderDTO findOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Order not found"));
        return mapToDto(order);
    }

    // get all orders
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
