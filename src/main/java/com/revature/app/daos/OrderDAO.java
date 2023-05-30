package com.revature.app.daos;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.revature.app.models.Order;
import com.revature.app.utils.ConnectionFactory;
import java.io.IOError;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class OrderDAO implements CrudDAO<Order> {

    @Override
    public void save(Order obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void update(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Order findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public List<Order> findUsersOrders(String user_id) {
        List<Order> orders = new ArrayList<Order>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();) {
            String sql = "select * from orders where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getString("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("amount"),
                        rs.getString("date"));
                orders.add(order);
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
        return orders;
    }

    @Override
    public List<Order> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    public Order save(String user_id, String date, BigDecimal amount) {
        String id = UUID.randomUUID().toString();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "INSERT INTO orders (id, user_id, amount) VALUES (?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, id);
                ps.setString(2, user_id);
                // ps.setString(3, date);
                ps.setBigDecimal(3, amount);
                ps.executeUpdate();
                
            }
            

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
            
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        Order order = new Order(user_id, user_id, amount, date);
        return order;
    }

    public void addOrderProducts(String order_id, String product_id, int quantity) {
        String id = UUID.randomUUID().toString();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "INSERT INTO orderproducts (id, order_id, product_id, quantity) VALUES (?,?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, id);
                ps.setString(2, order_id);
                ps.setString(3, product_id);
                ps.setInt(4, quantity);
                ps.executeUpdate();
                
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
