package com.humber.orderservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Long id;
    private Long orderId;
    private ProductDTO product;
    private int quantity;
    private double price;

    public Long getProductId() {
        return product.getId();
    }
}
