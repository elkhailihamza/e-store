package org.project.userservice.repository;

import org.project.userservice.entity.Cart;
import org.project.userservice.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByIdIn(List<Long> itemIds);}
