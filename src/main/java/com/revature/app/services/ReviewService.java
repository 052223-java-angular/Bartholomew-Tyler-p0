package com.revature.app.services;

import java.util.List;
import java.util.Optional;

import com.revature.app.daos.ReviewDAO;
import com.revature.app.models.Review;

import lombok.AllArgsConstructor;

/**
 * The ReviewService class handles the business logic behind all of the
 * necessary actions
 * that need to be taken regarding product reviews.
 */
@AllArgsConstructor
public class ReviewService {
    private final ReviewDAO reviewDAO;

    // createreview takes in the parameters needed to create a review and does so
    // through the save method in reviewDAO
    public void createReview(String userId, String productId, int rating, String comment) {
        reviewDAO.save(new Review(null, rating, comment, userId, productId));
    }

    // getReviewsByProductId takes in the productId and returns all of the reviews
    // of the product through the getReviewsByProductId in
    // the reviewDAO
    public List<Review> getReviewsByProductId(String productId) {
        return reviewDAO.getReviewsByProductId(productId);
    }

    // getByUserIdAndProductId takes in userId and productId to return a review if
    // it exists, else it will return an empty
    // review
    public Optional<Review> getByUserIdAndProductId(String userId, String productId) {
        return reviewDAO.getByUserIdAndProductId(userId, productId);
    }
}
