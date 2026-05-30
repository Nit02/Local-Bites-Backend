package com.foodnfun.backend.dto;

import jakarta.validation.constraints.NotNull;

public class PaymentDTO {

    private Long   id;

    @NotNull(message = "Order ID is required")
    private Long   orderId;

    @NotNull(message = "Payment method is required")
    private String paymentMethod;   // UPI | CARD | COD

    private String status;
    private String paymentDate;

    public Long   getId()                           { return id; }
    public void   setId(Long id)                    { this.id = id; }
    public Long   getOrderId()                      { return orderId; }
    public void   setOrderId(Long orderId)          { this.orderId = orderId; }
    public String getPaymentMethod()                { return paymentMethod; }
    public void   setPaymentMethod(String m)        { this.paymentMethod = m; }
    public String getStatus()                       { return status; }
    public void   setStatus(String status)          { this.status = status; }
    public String getPaymentDate()                  { return paymentDate; }
    public void   setPaymentDate(String d)          { this.paymentDate = d; }
}
