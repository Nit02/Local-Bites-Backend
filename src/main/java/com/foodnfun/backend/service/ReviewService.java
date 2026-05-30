package com.foodnfun.backend.service;

import com.foodnfun.backend.dto.ReviewDTO;
import java.util.List;

public interface ReviewService {
    ReviewDTO createReview(ReviewDTO reviewDTO);
    List<ReviewDTO> getReviewsByVendor(Long vendorId);
    List<ReviewDTO> getReviewsByUser(Long userId);
    void deleteReview(Long id);
}
