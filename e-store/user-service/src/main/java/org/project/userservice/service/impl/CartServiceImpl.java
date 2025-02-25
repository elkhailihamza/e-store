package org.project.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.project.userservice.dto.CartDto;
import org.project.userservice.dto.CartItemRequest;
import org.project.userservice.entity.Cart;
import org.project.userservice.entity.CartItem;
import org.project.userservice.entity.User;
import org.project.userservice.mapper.CartMapper;
import org.project.userservice.repository.CartItemRepository;
import org.project.userservice.repository.CartRepository;
import org.project.userservice.repository.UserRepository;
import org.project.userservice.security.CustomUserDetails;
import org.project.userservice.service.CartService;
import org.project.userservice.shared.exception.ItemNotFoundException;
import org.project.userservice.vm.CartVM;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final CartMapper cartMapper;

    private static final String STOCK_SERVICE_URL = "http://localhost:8222/products/p/add/{productId}";

    @Override
    public CartVM saveToCart(CartDto cartRequest) {
        User user = getAuthenticatedUser();
        Cart cart = cartRepository.findByUserId(user.getId()).orElse(new Cart(null, user, new ArrayList<>()));
        checkAndAddCartItems(cart, cartRequest);

        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toVm(savedCart);
    }

    @Override
    public CartVM getCartById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Panier non trouvé !"));
        return cartMapper.toVm(cart);
    }

    @Override
    public CartVM updateCart(CartDto cartRequest, Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Panier non trouvé !"));
        List<Long> itemIds = cart.getItems().stream()
                .map(CartItem::getId)
                .collect(Collectors.toList());
        cartItemRepository.deleteAllByIdIn(itemIds);

        cart.getItems().clear();

        cartMapper.updateCartFromDto(cartRequest, cart);
        checkAndAddCartItems(cart, cartRequest);

        Cart updatedCart = cartRepository.save(cart);
        return cartMapper.toVm(updatedCart);
    }

    @Override
    public void deleteCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Panier non trouvé !"));
        cartRepository.delete(cart);
    }

    private void checkAndAddCartItems(Cart cart, CartDto cartRequest) {
        List<CartItemRequest> validItems = new ArrayList<>();
        for (CartItemRequest item : cartRequest.getItems()) {
            if (checkProductAvailability(item.getProduitId())) {
                validItems.add(item);
            }
        }
        cartRequest.setItems(validItems);
    }

    private boolean checkProductAvailability(Long produitId) {
        try {
            String url = "http://localhost:8222/products/p/add/" + produitId;
            Boolean response = restTemplate.postForObject(url, null, Boolean.class);
            return response != null && response;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la vérification du stock pour le produit ID: " + produitId, e);
        }
    }



    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof CustomUserDetails)) {
            throw new IllegalStateException("Authentication principal is not de type CustomUserDetails");
        }
        CustomUserDetails userDetails = (CustomUserDetails) principal;
        return userRepository.findById(userDetails.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
    }
}
