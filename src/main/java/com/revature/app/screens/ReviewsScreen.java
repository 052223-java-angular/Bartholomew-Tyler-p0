package com.revature.app.screens;

import java.util.List;
import java.util.Scanner;

import com.revature.app.models.Product;
import com.revature.app.models.Review;
import com.revature.app.models.User;
import com.revature.app.services.ReviewService;
import com.revature.app.services.UserService;
import com.revature.app.utils.StringHelper;

import lombok.AllArgsConstructor;

/**
 * The ReviewsScreen class handles what information is presented to the user
 * when it
 * is navigated to. It implements the IScreen interface.
 */
@AllArgsConstructor
public class ReviewsScreen implements IScreen {
    private final ReviewService reviewService;
    private final UserService userService;
    private final Product product;

    // Overrides IScreen's default start method
    @Override
    public void start(Scanner scan) {
        String message = "";
        // Creates a list of product reviews based off of that product's id
        List<Review> reviews = reviewService.getReviewsByProductId(product.getId());

        main: {
            // while loop to hold the necessary information for the user to see
            while (true) {
                clearScreen();
                System.out.println("Reviews for:");
                System.out.println("-----------------------------------------------------");
                System.out.println(product.getName());
                System.out.println("-----------------------------------------------------");
                System.out.println();

                if (!message.isEmpty()) {
                    System.out.println(message + "\n");
                }
                // Runs printReviews method to display the reviews
                printReviews(reviews);

                System.out.print("\nPress x to exit: ");
                String input = scan.nextLine();
                // Handles the option to exit or check for invalid inputs
                switch (input.toLowerCase()) {
                    case "x":
                        break main;
                    default:
                        message = "Invalid input!";
                        continue;
                }
            }
        }
    }

    // Handles displaying all of the reviews through a for loop, if that don't
    // exist, it lets the user know
    private void printReviews(List<Review> reviews) {
        if (reviews.size() > 0) {
            for (Review review : reviews) {
                printReview(review);
                System.out.println();
            }
        } else {
            System.out.println("No reviews yet!");
        }
    }

    // Handles the printing of the individual reviews as the go through the for loop
    // in printReviews
    private void printReview(Review review) {
        User user = userService.findById(review.getUser_id());
        System.out.println("👤 " + user.getUsername());
        System.out.println(getStarString(review.getRating()));
        StringHelper.wrapAndDisplay(review.getComment());
        System.out.println();
    }

    // Displays the star rating of the review in the printReview method
    private String getStarString(int amount) {
        String stars = "";
        for (int i = 0; i < amount; i++) {
            stars = stars + "⭐";
        }
        return stars;
    }

    // Clears the screen at the start of the while loop to display all the necessary
    // information
    // later in the loop
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
