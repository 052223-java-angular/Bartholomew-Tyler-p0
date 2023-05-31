package com.revature.app.screens;

import java.util.Scanner;

import com.revature.app.models.Product;
import com.revature.app.services.ReviewService;
import com.revature.app.services.RouterService;
import com.revature.app.utils.Session;
import com.revature.app.utils.StringHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.AllArgsConstructor;

/**
 * The CreateReviewScreen class handles what information is presented to the
 * user when it
 * is navigated to. It implements the IScreen interface.
 */
@AllArgsConstructor
public class CreateReviewScreen implements IScreen {
    private final RouterService routerService;
    private final ReviewService reviewService;
    private final Product product;
    private Session session;
    private static final Logger logger = LogManager.getLogger(CreateReviewScreen.class);

    @Override
    public void start(Scanner scan) {
        int minimumRating = 1;
        int maxmiumRating = 5;
        String RATING_ERROR_MSG = "Rating must be an integer between " + minimumRating + " and " + maxmiumRating;
        String message = "";

        main: {
            while (true) {
                logger.info("Navigated to CreateReviewScreen");
                clearScreen();
                System.out.println("Create Review for:");
                System.out.println("-----------------------------------------------------");
                System.out.println(product.getName());
                System.out.println("-----------------------------------------------------");

                if (!message.isEmpty()) {
                    System.out.println(message);
                }

                System.out.print(
                        "\nNumber of stars between " + minimumRating + " and " + maxmiumRating + " (x to cancel): ");
                String rating = scan.nextLine();

                if (rating.equalsIgnoreCase("x")) {
                    break;
                }

                if (!StringHelper.isNumeric(rating)) {
                    message = RATING_ERROR_MSG;
                    continue;
                }

                double ratingDouble = Double.parseDouble(rating);

                if (!StringHelper.isInteger(ratingDouble)) {
                    message = RATING_ERROR_MSG;
                    continue;
                }

                int ratingInt = (int) ratingDouble;

                if (ratingInt < minimumRating || maxmiumRating < ratingInt) {
                    message = RATING_ERROR_MSG;
                    continue;
                }

                System.out.print("Add a written review (x to cancel): ");
                String review = scan.nextLine();

                if (review.equalsIgnoreCase("x")) {
                    continue;
                }

                addReview: {
                    while (true) {
                        System.out.print("Confirm add review? (y/n): ");
                        String input = scan.nextLine();

                        switch (input.toLowerCase()) {
                            case "y":
                                reviewService.createReview(session.getId(), product.getId(), ratingInt, review);
                                routerService.navigate("/reviews", scan, product);
                                break main;
                            case "n":
                                break addReview;
                            default:
                                System.out.println("Invalid option!");
                                continue;
                        }
                    }
                }
            }
        }

    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
