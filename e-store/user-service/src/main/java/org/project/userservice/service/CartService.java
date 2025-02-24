package org.project.userservice.service;

import org.project.userservice.dto.CartDto;
import org.project.userservice.vm.CartVM;

public interface CartService {
    CartVM saveToCart(CartDto cartRequest);
    CartVM getCartById(Long id);
    CartVM updateCart(CartDto cartRequest, Long id);
    void deleteCart(Long id);

}
