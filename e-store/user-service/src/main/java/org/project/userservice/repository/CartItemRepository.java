package org.project.userservice.repository;

import org.project.userservice.dto.CartDto;
import org.project.userservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<Cart, Long> {
}
