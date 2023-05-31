package com.revature.app.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.revature.app.models.Review;
import com.revature.app.utils.ConnectionFactory;

public class ReviewDAO implements CrudDAO<Review> {
    /**
     * The ReviewDAO class handles setting up the sql querys related to CRUD actions
     * involving he Review model. It implements the CrudDAO interface.
     */
    @Override
    public void save(Review review) {
        String sql = "INSERT INTO reviews(id, rating, comment, user_id, product_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, UUID.randomUUID().toString());
            preparedStatement.setInt(2, review.getRating());
            preparedStatement.setString(3, review.getComment());
            preparedStatement.setString(4, review.getUser_id());
            preparedStatement.setString(5, review.getProduct_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Review obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Review findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Review> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    public List<Review> getReviewsByProductId(String productId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews WHERE product_id = ?";
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, productId);
            try (ResultSet rs = preparedStatement.executeQuery();) {
                while (rs.next()) {
                    Review review = new Review(
                            rs.getString("id"),
                            rs.getInt("rating"),
                            rs.getString("comment"),
                            rs.getString("user_id"),
                            rs.getString("product_id"));
                    reviews.add(review);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return reviews;
    }

    public Optional<Review> getByUserIdAndProductId(String userId, String productId) {
        String sql = "SELECT * FROM reviews WHERE user_id = ? and product_id = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ps.setString(2, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Create a new User object and populate it with data from the result set
                    Review review = new Review(
                            rs.getString("id"),
                            rs.getInt("rating"),
                            rs.getString("comment"),
                            rs.getString("user_id"),
                            rs.getString("product_id"));
                    return Optional.of(review);
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
