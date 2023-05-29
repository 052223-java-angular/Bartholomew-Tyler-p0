package com.revature.app.daos;
import java.util.List;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.revature.app.models.Order;
import com.revature.app.utils.ConnectionFactory;
import java.io.IOError;
import java.io.IOException;
import java.sql.SQLException;

public class OrderDAO implements CrudDAO<Order>{

    @Override
    public void save(Order obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void update(String id) {
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
            while(rs.next()) {
                Order order = new Order
                (rs.getString("id"),
                rs.getString("name"),
                rs.getBigDecimal("amount"),
                rs.getString("description"));
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
    
}
