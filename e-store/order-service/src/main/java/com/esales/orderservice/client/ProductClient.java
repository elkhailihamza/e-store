package com.esales.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    ResponseEntity<ProductResponse> getProductById(@PathVariable Long id);

    @PutMapping("/api/products/{id}/stock/reduce")
    ResponseEntity<Boolean> reduceStock(@PathVariable Long id, @RequestParam int quantity);

    record ProductResponse(
            Long id,
            String name,
            String description,
            BigDecimal price,
            String imageUrl,
            Integer stockQuantity,
            CategoryDto category,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

    record CategoryDto(
            Long id,
            String name,
            String description
    ) {}
}