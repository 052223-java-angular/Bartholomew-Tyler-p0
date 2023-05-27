package com.revature.app.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import com.revature.app.models.Product;
import com.revature.app.utils.ConnectionFactory;
import java.io.IOError;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO implements CrudDAO<Product> {
    
    @Override
    public void save(Product product) {
                // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
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
    public Product findById(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }


    @Override
    public ArrayList<Product> findAll() {
        
        ArrayList<Product> products = new ArrayList<>();
        try(Connection connection = ConnectionFactory.getInstance().getConnection();) {
           
            String sql = "select * from products";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
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



}

