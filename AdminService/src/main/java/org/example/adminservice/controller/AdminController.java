package org.example.adminservice.controller;

import org.example.adminservice.DTO.OrderDTO;
import org.example.adminservice.DTO.ProductDTO;
import org.example.adminservice.client.OrderClient;
import org.example.adminservice.client.ProductClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderClient orderClient;


    private StreamBridge streamBridge;
    public AdminController(StreamBridge streamBridge){
        this.streamBridge = streamBridge;
    }

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    // Product Endpoints
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productClient.getAllProducts());
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        logger.info("Creating product with data: " + productDTO.toString());
        ProductDTO createdProduct = productClient.createProduct(productDTO);

        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productClient.getProductById(id));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productClient.updateProduct(id, productDTO));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        // Kafka stream event
        ProductDTO product = productClient.getProductById(id);
        logger.info("Sending request to Delete Image for productID: " + id);
        streamBridge.send("senddelete-out-0.", product.getImageId());

        productClient.deleteProduct(id);

        return ResponseEntity.ok().build();
    }

    // Order Endpoints

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderClient.getAllOrders());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderClient.getOrderById(id));
    }
}
