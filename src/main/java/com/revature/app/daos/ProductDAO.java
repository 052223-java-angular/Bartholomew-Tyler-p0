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

public class ProductDAO implements CrudDAO<Product> {
    @Override
    public void save(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void update(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Product findById(String id) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();) {
            String sql = "select * from products where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getBigDecimal("price"),
                        rs.getString("description"));
                return product;
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
        return null;
    }

    @Override
    public ArrayList<Product> findAll() {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();) {
            String sql = "select * from products";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getBigDecimal("price"),
                        rs.getString("description"));
                products.add(product);
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

    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<Product>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM products WHERE LOWER(name) LIKE ? ORDER BY name";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, "%" + name.toLowerCase() + "%");

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

    public List<String> findAllProductCategories() {
        List<String> categories = new ArrayList<String>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT DISTINCT category FROM products";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        categories.add(rs.getString("category"));
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

        return categories;
    }

    public List<Product> findByCategory(String category) {
        List<Product> products = new ArrayList<Product>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM products WHERE category = ? ORDER BY name";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, category);

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

    public List<Product> findByPriceRange(double lowerLimit, double upperLimit) {
        List<Product> products = new ArrayList<Product>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM products WHERE price BETWEEN ? and ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setDouble(1, lowerLimit);
                ps.setDouble(2, upperLimit);
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
