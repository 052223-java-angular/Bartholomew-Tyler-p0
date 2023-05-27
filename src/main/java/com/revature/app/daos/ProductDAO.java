package com.revature.app.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.app.models.Product;
import com.revature.app.utils.ConnectionFactory;

public class ProductDAO {
    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<Product>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM products WHERE LOWER(name) LIKE ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, "%" + name + "%");

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Create a new User object and populate it with data from the result set
                        products.add(new Product(
                                rs.getString("id"),
                                rs.getString("name"),
                                rs.getString("category"),
                                rs.getBigDecimal("price"),
                                rs.getString("description")));
                    }
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

        return products;
    }
}
