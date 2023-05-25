package com.revature.app.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import com.revature.app.models.User;
import com.revature.app.utils.ConnectionFactory;
import java.io.IOError;
import java.io.IOException;
import java.sql.SQLException;

public class UserDAO implements CrudDAO<User> {
    
    @Override
    public void save(User obj) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "INSERT INTO users (id, username, password) VALUES (?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, obj.getId());
                ps.setString(2, obj.getUsername());
                ps.setString(3, obj.getPassword());
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

    public User loginByUsernameAndPassword(String username, String password) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "INSERT INTO users (id, username, password) VALUES (?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, obj.getId());
                ps.setString(2, obj.getUsername());
                ps.setString(3, obj.getPassword());
 
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

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
}
