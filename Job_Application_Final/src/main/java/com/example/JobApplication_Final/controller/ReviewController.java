package com.example.JobApplication_Final.controller;

import com.example.JobApplication_Final.model.Review;
import com.example.JobApplication_Final.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        List<Review> reviews = reviewService.findReviewByCompanyId(companyId);

        if (!reviews.isEmpty()) {
            return ResponseEntity.ok(reviews);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> addReview(@PathVariable Long companyId, @RequestBody Review review) {
        if (reviewService.addReview(companyId, review))
            return ResponseEntity.status(HttpStatus.CREATED).body(review);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(review);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewId(@PathVariable Long companyId, @PathVariable Long reviewId) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewById(companyId, reviewId));
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        if (reviewService.deleteReviewById(companyId, reviewId))
            return ResponseEntity.ok().body("Review Deleted");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company Not Found. So review could not be deleted");
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {
        if (reviewService.updateReviewById(companyId, reviewId, review)) {
            return ResponseEntity.status(HttpStatus.OK).body(review);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
