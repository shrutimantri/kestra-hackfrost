package com.frosty.riders.review.service.service;

import com.frosty.riders.review.service.Exception.NotFoundException;
import com.frosty.riders.review.service.entity.Review;
import com.frosty.riders.review.service.model.request.ReviewRequest;
import com.frosty.riders.review.service.model.response.FailureResponse;
import com.frosty.riders.review.service.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review findById(UUID id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(FailureResponse.builder()
                        .errorMessage("Review not found")
                        .build()));
    }

    public void createReview(ReviewRequest reviewRequest) {
        Review review = Review.fromReviewRequest(reviewRequest);
        reviewRepository.save(review);
    }

}
