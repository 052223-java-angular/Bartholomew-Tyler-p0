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

@AllArgsConstructor
public class ReviewsScreen implements IScreen {
    private final ReviewService reviewService;
    private final UserService userService;
    private final Product product;

    @Override
    public void start(Scanner scan) {
        String message = "";

        List<Review> reviews = reviewService.getReviewsByProductId(product.getId());
        main: {
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

                printReviews(reviews);

                System.out.print("\nPress x to exit: ");
                String input = scan.nextLine();
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

    private void printReview(Review review) {
        User user = userService.findById(review.getUser_id());
        System.out.println("👤 " + user.getUsername());
        System.out.println(getStarString(review.getRating()));
        StringHelper.wrapAndDisplay(review.getComment());
    }

    private String getStarString(int amount) {
        String stars = "";
        for (int i = 0; i < amount; i++) {
            stars = stars + "⭐";
        }
        return stars;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
