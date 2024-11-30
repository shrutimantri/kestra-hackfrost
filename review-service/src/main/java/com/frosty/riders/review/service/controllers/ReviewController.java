package com.frosty.riders.review.service.controllers;

import com.frosty.riders.review.service.entity.Review;
import com.frosty.riders.review.service.model.request.ReviewRequest;
import com.frosty.riders.review.service.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> addReview(@RequestBody @Valid ReviewRequest review) {
        reviewService.createReview(review);
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
    }

}
