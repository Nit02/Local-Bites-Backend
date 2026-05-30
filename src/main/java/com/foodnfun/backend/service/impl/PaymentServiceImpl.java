package com.foodnfun.backend.service.impl;

import com.foodnfun.backend.dto.PaymentDTO;
import com.foodnfun.backend.entity.Order;
import com.foodnfun.backend.entity.Payment;
import com.foodnfun.backend.exception.ResourceNotFoundException;
import com.foodnfun.backend.repository.OrderRepository;
import com.foodnfun.backend.repository.PaymentRepository;
import com.foodnfun.backend.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository   orderRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository   = orderRepository;
    }

    @Override
    public PaymentDTO processPayment(PaymentDTO dto) {
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + dto.getOrderId()));

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setStatus("SUCCESS");
        payment.setPaymentDate(LocalDateTime.now());

        // Also mark order as CONFIRMED
        order.setStatus("CONFIRMED");
        orderRepository.save(order);

        Payment saved = paymentRepository.save(payment);
        return mapToDTO(saved);
    }

    @Override
    public PaymentDTO getPaymentByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found for order: " + orderId));
    }

    private PaymentDTO mapToDTO(Payment p) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(p.getId());
        dto.setOrderId(p.getOrder().getId());
        dto.setPaymentMethod(p.getPaymentMethod());
        dto.setStatus(p.getStatus());
        dto.setPaymentDate(p.getPaymentDate() != null ? p.getPaymentDate().toString() : null);
        return dto;
    }
}
