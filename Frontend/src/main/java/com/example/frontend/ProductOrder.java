package com.example.frontend;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {
    @Id
    @GeneratedValue()
    private long id;

    private long productId;
    private int quantity;

    private String productName;
    private String productDescription;
    private double productPrice;
}
