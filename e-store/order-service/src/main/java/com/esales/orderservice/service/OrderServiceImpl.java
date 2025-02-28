package com.esales.orderservice.service;

import com.esales.orderservice.client.ProductClient;
import com.esales.orderservice.dto.OrderRequest;
import com.esales.orderservice.dto.OrderResponse;
import com.esales.orderservice.model.Order;
import com.esales.orderservice.model.OrderItem;
import com.esales.orderservice.repository.OrderItemRepository;
import com.esales.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductClient productClient;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
        String orderNumber = generateOrderNumber();

        Order order = Order.builder()
                .userId(orderRequest.getUserId())
                .orderNumber(orderNumber)
                .totalAmount(BigDecimal.ZERO)
                .status(Order.OrderStatus.CREATED)
                .shippingAddress(orderRequest.getShippingAddress())
                .billingAddress(orderRequest.getBillingAddress())
                .createdAt(LocalDateTime.now())
                .build();

        Order savedOrder = orderRepository.save(order);

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItem> orderItems = orderRequest.getOrderItems().stream()
                .map(itemRequest -> {
                    ProductClient.ProductResponse product = productClient.getProductById(itemRequest.getProductId()).getBody();

                    boolean stockReduced = productClient.reduceStock(itemRequest.getProductId(), itemRequest.getQuantity()).getBody();
                    if (!stockReduced) {
                        throw new RuntimeException("Insufficient stock for product: " + product.name());
                    }

                    BigDecimal itemTotal = product.price().multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

                    return OrderItem.builder()
                            .order(savedOrder)
                            .productId(itemRequest.getProductId())
                            .productName(product.name())
                            .unitPrice(product.price())
                            .quantity(itemRequest.getQuantity())
                            .totalPrice(itemTotal)
                            .build();
                })
                .collect(Collectors.toList());

        totalAmount = orderItems.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        savedOrder.setTotalAmount(totalAmount);
        savedOrder.setOrderItems(orderItems);
        orderRepository.save(savedOrder);

        kafkaTemplate.send("order-events", "ORDER_CREATED", mapToOrderResponse(savedOrder));

        return mapToOrderResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        return mapToOrderResponse(order);
    }

    @Override
    public OrderResponse getOrderByOrderNumber(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new RuntimeException("Order not found with number: " + orderNumber));

        return mapToOrderResponse(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(this::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);

        return orders.stream()
                .map(this::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> getOrdersByStatus(Order.OrderStatus status) {
        List<Order> orders = orderRepository.findByStatus(status);

        return orders.stream()
                .map(this::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderResponse updateOrderStatus(Long id, Order.OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());

        Order updatedOrder = orderRepository.save(order);

        kafkaTemplate.send("order-events", "ORDER_STATUS_UPDATED", mapToOrderResponse(updatedOrder));

        return mapToOrderResponse(updatedOrder);
    }

    @Override
    @Transactional
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        if (order.getStatus() == Order.OrderStatus.SHIPPED ||
                order.getStatus() == Order.OrderStatus.DELIVERED) {
            throw new RuntimeException("Cannot cancel order that has been shipped or delivered");
        }

        order.setStatus(Order.OrderStatus.CANCELLED);
        order.setUpdatedAt(LocalDateTime.now());

        Order cancelledOrder = orderRepository.save(order);

        kafkaTemplate.send("order-events", "ORDER_CANCELLED", mapToOrderResponse(cancelledOrder));
    }

    private String generateOrderNumber() {
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private OrderResponse mapToOrderResponse(Order order) {
        List<OrderResponse.OrderItemResponse> orderItems = order.getOrderItems().stream()
                .map(item -> OrderResponse.OrderItemResponse.builder()
                        .id(item.getId())
                        .productId(item.getProductId())
                        .productName(item.getProductName())
                        .unitPrice(item.getUnitPrice())
                        .quantity(item.getQuantity())
                        .totalPrice(item.getTotalPrice())
                        .build())
                .collect(Collectors.toList());

        return OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .userId(order.getUserId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .shippingAddress(order.getShippingAddress())
                .billingAddress(order.getBillingAddress())
                .paymentId(order.getPaymentId())
                .orderItems(orderItems)
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}