package com.foodnfun.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MenuItemDTO {

    private Long   id;

    @NotBlank(message = "Item name is required")
    private String name;

    @Positive(message = "Price must be positive")
    private Double price;

    @NotNull(message = "Vendor ID is required")
    private Long   vendorId;

    public Long   getId()                     { return id; }
    public void   setId(Long id)              { this.id = id; }
    public String getName()                   { return name; }
    public void   setName(String name)        { this.name = name; }
    public Double getPrice()                  { return price; }
    public void   setPrice(Double price)      { this.price = price; }
    public Long   getVendorId()               { return vendorId; }
    public void   setVendorId(Long vendorId)  { this.vendorId = vendorId; }
}
