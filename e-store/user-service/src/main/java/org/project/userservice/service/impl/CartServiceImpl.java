package org.project.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.project.userservice.dto.CartDto;
import org.project.userservice.service.CartService;
import org.project.userservice.vm.CartVM;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    @Override
    public CartVM saveToCart(CartDto cartRequest) {
        return null;
    }

    @Override
    public CartVM getCartById(Long id) {
        return null;
    }

    @Override
    public CartVM updateCart(CartDto cartRequest, Long id) {
        return null;
    }

    @Override
    public void deleteCart(Long id) {

    }
}
