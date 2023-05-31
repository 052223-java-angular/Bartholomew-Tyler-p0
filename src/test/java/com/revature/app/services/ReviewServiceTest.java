package com.revature.app.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.app.daos.ReviewDAO;
import com.revature.app.models.Review;

/**
 * The ReviewServiceTest class, it handles all testing of the ReviewService
 * 
 */
public class ReviewServiceTest {
    /**
     * Here we mock our UserDAO and UserService
     * in order to test their associated methods
     */
    @Mock
    private ReviewDAO reviewDAO;

    private ReviewService reviewService;

    /**
     * setUp creates the environment for tests to be run
     */
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        reviewService = new ReviewService(reviewDAO);
    }

    /**
     * testCreateReview tests the capability of reviewService's
     * createReview method and it's ability to persist
     */
    @Test
    public void testCreateReview() {
        String userId = UUID.randomUUID().toString();
        String productId = UUID.randomUUID().toString();
        int rating = 3;
        String comment = "This product is absolutely stellar!";
        doNothing().when(reviewDAO).save(any(Review.class));
        reviewService.createReview(userId, productId, rating, comment);
        verify(reviewDAO, times(1)).save(any(Review.class));
    }

    /**
     * testGetReviewByProductId tests the capibility of reviewService's
     * getReviewsByProductId method to retrieve the necessary reviews from the
     * database and return them.
     */
    @Test
    public void testGetReviewsByProductId() {
        String productId = UUID.randomUUID().toString();
        List<Review> sampleReviews = new ArrayList<>();
        sampleReviews.add(new Review(UUID.randomUUID().toString(), 3, "", UUID.randomUUID().toString(), productId));
        when(reviewDAO.getReviewsByProductId(sampleReviews.get(0).getProduct_id())).thenReturn(sampleReviews);
        List<Review> returnReviews = reviewService.getReviewsByProductId(productId);
        assertEquals(sampleReviews, returnReviews);
    }

    /**
     * testGetByUserIdAndProductId tests the capibility of reviewService's method
     * getByUserIdAndProductId to retrieve a review based off of it's User and
     * Product ids.
     */
    @Test
    public void testGetByUserIdAndProductId() {
        String userId = UUID.randomUUID().toString();
        String productId = UUID.randomUUID().toString();
        when(reviewDAO.getByUserIdAndProductId(userId, productId)).thenReturn(Optional.empty());
        Optional<Review> reviewOpt = reviewService.getByUserIdAndProductId(userId, productId);
        assertEquals(Optional.empty(), reviewOpt);
    }
}
