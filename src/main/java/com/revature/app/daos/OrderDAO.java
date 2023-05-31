package com.revature.app.daos;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.revature.app.models.Order;
import com.revature.app.models.OrderProduct;
import com.revature.app.utils.ConnectionFactory;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * The OrderDAO class handles database operations for Orders.
 * It implements the CrudDAO interface.
 */
public class OrderDAO implements CrudDAO<Order> {

    /**
     * Saves an Order to the data source.
     *
     * @param order the Order to be saved
     */
    @Override
    public void save(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    /**
     * Updates an Order to the data source.
     *
     * @param order the Order to be updated
     */
    @Override
    public void update(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    /**
     * Deletes an Order from the data source.
     *
     * @param id the id of the Order to be deleted
     */
    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    /**
     * Retrieves an Order by id
     *
     * @param id the id of the Order to retrieve
     */
    @Override
    public Order findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    /**
     * Retrieves Orders associated to a User
     *
     * @param user_id the id of the associated User
     */
    public List<Order> findUsersOrders(String user_id) {
        List<Order> orders = new ArrayList<Order>();
        String sql = "select * from orders where user_id = ?";
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, user_id);
            try (ResultSet rs = preparedStatement.executeQuery();) {
                while (rs.next()) {
                    String orderId = rs.getString("id");
                    Order order = new Order(orderId,
                            user_id,
                            rs.getBigDecimal("amount"),
                            rs.getTimestamp("created_at").toString(),
                            getOrderProductsByOrderId(orderId));
                    orders.add(order);
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
        return orders;
    }

    /**
     * Retrieves OrderProducts associated to an Order
     *
     * @param orderId the id of the associated Order
     */
    private List<OrderProduct> getOrderProductsByOrderId(String orderId) {
        List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
        String sql = "select * from orderproducts where order_id = ?";
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, orderId);
            try (ResultSet rs = preparedStatement.executeQuery();) {
                while (rs.next()) {
                    OrderProduct orderProduct = new OrderProduct(
                            rs.getString("id"),
                            orderId,
                            rs.getString("product_id"),
                            rs.getInt("quantity"));
                    orderProducts.add(orderProduct);
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
        return orderProducts;
    }

    /**
     * Retrieves all Orders
     *
     */
    @Override
    public List<Order> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    /**
     * Saves an Order to the data source.
     *
     * @param user_id the id of the associated User
     * @param amount  the total amount of the order
     */
    public Order save(String user_id, BigDecimal amount) {
        String id = UUID.randomUUID().toString();
        String sql = "INSERT INTO orders (id, user_id, amount) VALUES (?,?,?)";
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setString(2, user_id);
            ps.setBigDecimal(3, amount);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        Order order = new Order(id, user_id, amount, null, getOrderProductsByOrderId(id));
        return order;
    }

    /**
     * Associates OrderProducts to an Order
     *
     * @param order      the id of the associated Order
     * @param product_id the id of the associated Product
     * @param quantity   how much of the Product to associate with the Order
     */
    public void addOrderProducts(String order_id, String product_id, int quantity) {
        String id = UUID.randomUUID().toString();
        String sql = "INSERT INTO orderproducts (id, order_id, product_id, quantity) VALUES (?,?,?,?)";
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setString(2, order_id);
            ps.setString(3, product_id);
            ps.setInt(4, quantity);
            ps.executeUpdate();
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
