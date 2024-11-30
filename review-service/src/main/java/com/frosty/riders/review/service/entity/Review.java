package com.frosty.riders.review.service.entity;

import com.frosty.riders.review.service.model.request.ReviewRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID reviewId;

    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "review", nullable = false)
    private String review;

    public static Review fromReviewRequest(ReviewRequest reviewRequest) {
        return Review.builder()
                .productId(reviewRequest.getProductId())
                .customerId(reviewRequest.getCustomerId())
                .rating(reviewRequest.getRating())
                .review(reviewRequest.getReview())
                .build();
    }

}
