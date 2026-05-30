package com.foodnfun.backend.dto;

import jakarta.validation.constraints.*;

public class ReviewDTO {

    private Long   id;

    @NotNull(message = "User ID is required")
    private Long   userId;

    @NotNull(message = "Vendor ID is required")
    private Long   vendorId;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private int    rating;

    private String comment;

    public Long   getId()                     { return id; }
    public void   setId(Long id)              { this.id = id; }
    public Long   getUserId()                 { return userId; }
    public void   setUserId(Long userId)      { this.userId = userId; }
    public Long   getVendorId()               { return vendorId; }
    public void   setVendorId(Long vendorId)  { this.vendorId = vendorId; }
    public int    getRating()                 { return rating; }
    public void   setRating(int rating)       { this.rating = rating; }
    public String getComment()                { return comment; }
    public void   setComment(String comment)  { this.comment = comment; }
}
