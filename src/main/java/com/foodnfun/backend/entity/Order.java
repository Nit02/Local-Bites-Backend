package com.foodnfun.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double totalAmount;

    // PENDING | CONFIRMED | PREPARING | OUT_FOR_DELIVERY | DELIVERED | CANCELLED
    @Column(nullable = false)
    private String status = "PENDING";

    private LocalDateTime orderDate;

    // ── Getters & Setters ────────────────────────────────────────────────────
    public Long          getId()                        { return id; }
    public void          setId(Long id)                 { this.id = id; }
    public User          getUser()                      { return user; }
    public void          setUser(User user)             { this.user = user; }
    public Food          getFood()                      { return food; }
    public void          setFood(Food food)             { this.food = food; }
    public int           getQuantity()                  { return quantity; }
    public void          setQuantity(int q)             { this.quantity = q; }
    public double        getTotalAmount()               { return totalAmount; }
    public void          setTotalAmount(double t)       { this.totalAmount = t; }
    public String        getStatus()                    { return status; }
    public void          setStatus(String status)       { this.status = status; }
    public LocalDateTime getOrderDate()                 { return orderDate; }
    public void          setOrderDate(LocalDateTime d)  { this.orderDate = d; }
}
