package org.project.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.project.userservice.dto.CartDto;
import org.project.userservice.service.CartService;
import org.project.userservice.vm.CartVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @PostMapping("/save")
    public ResponseEntity<CartVM> save(@RequestBody CartDto request) {
        return ResponseEntity.ok(cartService.saveToCart(request));
    }
}
