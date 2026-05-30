package com.foodnfun.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public class OrderDTO {

    private Long          id;
    @NotNull(message = "User ID is required")
    private Long          userId;
    @NotNull(message = "Food ID is required")
    private Long          foodId;
    @Positive(message = "Quantity must be positive")
    private int           quantity;
    private double        totalAmount;
    private String        status;
    private LocalDateTime orderDate;

    public Long          getId()                        { return id; }
    public void          setId(Long id)                 { this.id = id; }
    public Long          getUserId()                    { return userId; }
    public void          setUserId(Long userId)         { this.userId = userId; }
    public Long          getFoodId()                    { return foodId; }
    public void          setFoodId(Long foodId)         { this.foodId = foodId; }
    public int           getQuantity()                  { return quantity; }
    public void          setQuantity(int quantity)      { this.quantity = quantity; }
    public double        getTotalAmount()               { return totalAmount; }
    public void          setTotalAmount(double t)       { this.totalAmount = t; }
    public String        getStatus()                    { return status; }
    public void          setStatus(String status)       { this.status = status; }
    public LocalDateTime getOrderDate()                 { return orderDate; }
    public void          setOrderDate(LocalDateTime d)  { this.orderDate = d; }
}
