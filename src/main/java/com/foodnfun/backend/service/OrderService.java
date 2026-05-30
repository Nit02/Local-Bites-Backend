package com.foodnfun.backend.service;

import com.foodnfun.backend.dto.OrderDTO;
import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getOrdersByUser(Long userId);
    OrderDTO updateOrderStatus(Long id, String status);
    void cancelOrder(Long id);
}
