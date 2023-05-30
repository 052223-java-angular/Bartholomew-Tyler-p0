package com.revature.app.services;

import java.util.List;
import java.util.Optional;

import com.revature.app.daos.ReviewDAO;
import com.revature.app.models.Review;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReviewService {
    private final ReviewDAO reviewDAO;

    public void createReview(String userId, String productId, int rating, String comment) {
        reviewDAO.save(new Review(null, rating, comment, userId, productId));
    }

    public List<Review> getReviewsByProductId(String productId) {
        return reviewDAO.getReviewsByProductId(productId);
    }

    public Optional<Review> getByUserIdAndProductId(String userId, String productId) {
        return reviewDAO.getByUserIdAndProductId(userId, productId);
    }
}
