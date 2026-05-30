package com.foodnfun.backend.controller;

import com.foodnfun.backend.dto.ReviewDTO;
import com.foodnfun.backend.response.ApiResponse;
import com.foodnfun.backend.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@Tag(name = "Reviews")
@SecurityRequirement(name = "bearerAuth")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    @Operation(summary = "Submit a review for a vendor")
    public ResponseEntity<ApiResponse<ReviewDTO>> createReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Review submitted", reviewService.createReview(reviewDTO)));
    }

    @GetMapping("/vendor/{vendorId}")
    @Operation(summary = "Get all reviews for a vendor (public)")
    public ResponseEntity<ApiResponse<List<ReviewDTO>>> getByVendor(@PathVariable Long vendorId) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Reviews fetched", reviewService.getReviewsByVendor(vendorId)));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all reviews by a user")
    public ResponseEntity<ApiResponse<List<ReviewDTO>>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Reviews fetched", reviewService.getReviewsByUser(userId)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a review")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Review deleted", null));
    }
}
