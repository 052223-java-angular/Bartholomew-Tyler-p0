package com.revature.app.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.revature.app.models.Cart;
import com.revature.app.models.CartProduct;
import com.revature.app.models.User;
import com.revature.app.utils.ConnectionFactory;

import lombok.AllArgsConstructor;

/**
 * The CartDAO class handles database operations for Carts.
 * It implements the CrudDAO interface.
 */
@AllArgsConstructor
public class CartDAO implements CrudDAO<Cart> {
    private UserDAO userDAO;
    private ProductDAO productDAO;

    /**
     * Saves a Cart to the data source.
     *
     * @param cart the Cart to be saved
     */
    @Override
    public void save(Cart cart) {
        String sql = "INSERT INTO carts(id, user_id) VALUES (?, ?)";
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, UUID.randomUUID().toString());
            preparedStatement.setString(2, cart.getUser().getId());
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

    /**
     * Updates a Cart from the data source.
     *
     * @param cart the Cart to be updated
     */
    @Override
    public void update(Cart cart) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    /**
     * Deletes a Cart from the data source.
     *
     * @param id the id of the Cart to be deleted
     */
    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    /**
     * Retrieves a Cart by id
     *
     * @param id the id of the Cart to retrieve
     */
    @Override
    public Cart findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    /**
     * Retrieves all Carts
     *
     */
    @Override
    public List<Cart> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    /**
     * Retrieves CartProducts associated to a Cart
     *
     * @param cartId the id of the associated Cart
     */
    public List<CartProduct> getCartProductsFromCartId(String cartId) {
        List<CartProduct> cartProducts = new ArrayList<>();
        String sql = "SELECT * FROM cartproducts WHERE cart_id = ?";
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, cartId);
            try (ResultSet rs = preparedStatement.executeQuery();) {
                while (rs.next()) {
                    CartProduct cartProduct = new CartProduct(
                            rs.getString("id"),
                            cartId,
                            productDAO.findById(rs.getString("product_id")),
                            rs.getInt("quantity"));
                    cartProducts.add(cartProduct);
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
        return cartProducts;
    }

    /**
     * Retrieves a Cart using the id of the associated User
     *
     * @param userId the id of the associated User
     */
    public Cart getCartFromUserId(String userId) {
        User user = userDAO.findById(userId);
        String sql = "SELECT * FROM carts WHERE user_id = ?";
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, userId);
            try (ResultSet rs = preparedStatement.executeQuery();) {
                if (rs.next()) {
                    Cart cart = new Cart(
                            rs.getString("id"),
                            user,
                            getCartProductsFromCartId(rs.getString("id")));
                    return cart;
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
        return null;
    }

    /**
     * Associates a Product to a Cart
     *
     * @param cartId    the id of the Cart
     * @param productId the id of the Product
     * @param quantity  how much of the Product to add
     */
    public void addProductToCart(String cartId, String productId, int quantity) {
        String sql = "INSERT INTO cartproducts(id, cart_id, product_id, quantity) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, UUID.randomUUID().toString());
            preparedStatement.setString(2, cartId);
            preparedStatement.setString(3, productId);
            preparedStatement.setInt(4, quantity);
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

    /**
     * Removes the association between a Cart and a Product
     *
     * @param cartId    the id of the Cart
     * @param productId the id of the Product
     */
    public void removeProductFromCart(String cartId, String productId) {
        String sql = "DELETE FROM cartproducts WHERE cart_id = ? and product_id = ?";
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, cartId);
            preparedStatement.setString(2, productId);
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

    /**
     * Updates the quantity of the amount of a Product within a Cart
     *
     * @param cartProductId   the id of the Cart
     * @param updatedQuantity the id of the Product
     */
    public void updateQuantityOfProduct(String cartProductId, int updatedQuantity) {
        String sql = "UPDATE cartproducts SET quantity = ? WHERE id = ?";
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, updatedQuantity);
            preparedStatement.setString(2, cartProductId);
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
}
