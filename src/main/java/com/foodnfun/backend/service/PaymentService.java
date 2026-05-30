package com.foodnfun.backend.service;

import com.foodnfun.backend.dto.PaymentDTO;

public interface PaymentService {
    PaymentDTO processPayment(PaymentDTO paymentDTO);
    PaymentDTO getPaymentByOrderId(Long orderId);
}
