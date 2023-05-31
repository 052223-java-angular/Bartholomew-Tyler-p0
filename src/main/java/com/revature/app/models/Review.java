package com.revature.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Review class acts as a model for a Review left by a user
 * 
 * @param id         Acts as the primary key and identifier
 * @param rating     Represents star rating the product was given
 * @param comment    A comment about the product
 * @param user_id    The id of the user who left the review
 * @param product_id The id of the product being reviewed
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Review {
    private String id;
    private int rating;
    private String comment;
    private String user_id;
    private String product_id;
}
