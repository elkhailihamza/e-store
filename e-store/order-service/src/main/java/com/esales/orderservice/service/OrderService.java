package com.esales.orderservice.service;

import com.esales.orderservice.dto.OrderRequest;
import com.esales.orderservice.dto.OrderResponse;
import com.esales.orderservice.model.Order;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest);

    OrderResponse getOrderById(Long id);

    OrderResponse getOrderByOrderNumber(String orderNumber);

    List<OrderResponse> getAllOrders();

    List<OrderResponse> getOrdersByUserId(Long userId);

    List<OrderResponse> getOrdersByStatus(Order.OrderStatus status);

    OrderResponse updateOrderStatus(Long id, Order.OrderStatus status);

    void cancelOrder(Long id);
}
