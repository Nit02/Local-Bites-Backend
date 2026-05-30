package com.foodnfun.backend.service.impl;

import com.foodnfun.backend.dto.ReviewDTO;
import com.foodnfun.backend.entity.Review;
import com.foodnfun.backend.entity.User;
import com.foodnfun.backend.entity.Vendor;
import com.foodnfun.backend.exception.ResourceNotFoundException;
import com.foodnfun.backend.repository.ReviewRepository;
import com.foodnfun.backend.repository.UserRepository;
import com.foodnfun.backend.repository.VendorRepository;
import com.foodnfun.backend.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository  reviewRepository;
    private final UserRepository    userRepository;
    private final VendorRepository  vendorRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             UserRepository userRepository,
                             VendorRepository vendorRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository   = userRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public ReviewDTO createReview(ReviewDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Vendor vendor = vendorRepository.findById(dto.getVendorId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));

        Review review = new Review();
        review.setUser(user);
        review.setVendor(vendor);
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());

        return mapToDTO(reviewRepository.save(review));
    }

    @Override
    public List<ReviewDTO> getReviewsByVendor(Long vendorId) {
        return reviewRepository.findByVendorId(vendorId).stream().map(this::mapToDTO).toList();
    }

    @Override
    public List<ReviewDTO> getReviewsByUser(Long userId) {
        return reviewRepository.findByUserId(userId).stream().map(this::mapToDTO).toList();
    }

    @Override
    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Review not found with id: " + id);
        }
        reviewRepository.deleteById(id);
    }

    private ReviewDTO mapToDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setUserId(review.getUser().getId());
        dto.setVendorId(review.getVendor().getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        return dto;
    }
}
