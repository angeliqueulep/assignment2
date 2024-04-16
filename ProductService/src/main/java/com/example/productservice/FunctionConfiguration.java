package com.example.productservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class FunctionConfiguration {
    private final Logger logger =
            LoggerFactory.getLogger(FunctionConfiguration.class);

    @Bean
    Function<OrderDTO, Boolean> placeorder(){
        return (OrderDTO orders)->{
            List<OrderItemDTO> orderItems = orders.getOrderItems();

            List<String> productIdsList = orderItems.stream()
                    .map(OrderItemDTO::getProductId)
                    .map(String::valueOf)
                    .collect(Collectors.toList());

            // Compile product IDs into a string for each element in the list
            String compiledProductIdsList = String.join(", ", productIdsList);
            logger.info("An order has been placed for productIDs: " + compiledProductIdsList);
            return true;
        };
    }

}
