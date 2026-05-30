package com.foodnfun.backend.service.impl;

import com.foodnfun.backend.dto.OrderDTO;
import com.foodnfun.backend.entity.Food;
import com.foodnfun.backend.entity.Order;
import com.foodnfun.backend.entity.User;
import com.foodnfun.backend.exception.ResourceNotFoundException;
import com.foodnfun.backend.repository.FoodRepository;
import com.foodnfun.backend.repository.OrderRepository;
import com.foodnfun.backend.repository.UserRepository;
import com.foodnfun.backend.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository  userRepository;
    private final FoodRepository  foodRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            UserRepository userRepository,
                            FoodRepository foodRepository) {
        this.orderRepository = orderRepository;
        this.userRepository  = userRepository;
        this.foodRepository  = foodRepository;
    }

    @Override
    public OrderDTO createOrder(OrderDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Food food = foodRepository.findById(dto.getFoodId())
                .orElseThrow(() -> new ResourceNotFoundException("Food not found"));

        Order order = new Order();
        order.setUser(user);
        order.setFood(food);
        order.setQuantity(dto.getQuantity());
        order.setTotalAmount(food.getPrice() * dto.getQuantity());
        order.setStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());

        return mapToDTO(orderRepository.save(order));
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        return mapToDTO(orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id)));
    }

    @Override
    public List<OrderDTO> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId).stream().map(this::mapToDTO).toList();
    }

    @Override
    public OrderDTO updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        order.setStatus(status.toUpperCase());
        return mapToDTO(orderRepository.save(order));
    }

    @Override
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        order.setStatus("CANCELLED");
        orderRepository.save(order);
    }

    private OrderDTO mapToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setFoodId(order.getFood().getId());
        dto.setQuantity(order.getQuantity());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        dto.setOrderDate(order.getOrderDate());
        return dto;
    }
}
