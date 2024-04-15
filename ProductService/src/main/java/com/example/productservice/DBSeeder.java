package com.example.productservice;


import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DBSeeder implements CommandLineRunner {
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Product> products = List.of(Product.builder().name("Jacket").description("A stylish and warm jacket").imageId(1L).price(99.99).stock(100).build(),
                Product.builder().name("Coat").description("A classic coat for all occasions").imageId(2L).price(149.99).stock(75).build(),
                Product.builder().name("Hoodie").description("A comfortable and casual hoodie").imageId(3L).price(49.99).stock(150).build()
        );
        productRepository.saveAll(products);

        productRepository.findAll().forEach((product ->
                System.out.println(product.getId() + " " + product.getName())
        ));
    }
}
