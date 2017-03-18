package com.epam.zubar.hr.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.zubar.hr.dao.AbstractDAO;
import com.epam.zubar.hr.entity.User;
import com.epam.zubar.hr.exception.HRProjectDAOException;

/**
 * Contains methods allowing to extract information about Users,
 * add new and update existent Users.
 * @author Mikalay Zubar
 *
 */
public class UserDAO extends AbstractDAO<User> {
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE user_id=?";
    private static final String INSERT_USER = "INSERT INTO users (user_login, user_password, user_role, user_status) VALUES(?,?,?,?)";
    private static final String UPDATE_USER = "UPDATE users SET user_login=?, user_password=?, user_role=?, user_status=? WHERE user_id=?";
    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE user_login=?";
    private static final String UPDATE_USER_PASSWORD = "UPDATE users SET user_password=? WHERE user_id=?";
    private static final String DELETE_ENTITY = "UPDATE users SET user_status=? "
            + " WHERE user_id=?";
    private static final String STATUS_B = "blocked";
    private static final String STATUS_A = "active";

    public UserDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> findAll() throws HRProjectDAOException {
        List<User> users = new ArrayList<>();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5)));
            }
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error", e);
        }finally{
            close(ps);
        }
        return users;
    }

    @Override
    public boolean delete(User user) throws HRProjectDAOException {
        boolean isDeleted = false;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(DELETE_ENTITY);
            ps.setString(1, STATUS_B);
            ps.setInt(2, user.getId());
            ps.executeUpdate();
            isDeleted = true;
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error", e);
        }finally{
            close(ps);
        }
        return isDeleted;
    }

    @Override
    public boolean insert(User user) throws HRProjectDAOException {
        PreparedStatement ps = null;
        boolean isInserted = false;
        try {
            ps = connection.prepareStatement(INSERT_USER);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setString(4, user.getStatus());
            ps.executeUpdate();
            isInserted = true;
        } catch (SQLException e){
            throw new HRProjectDAOException("Database error", e);
        }finally{
            close(ps);
        }
        return isInserted;
    }

    @Override
    public boolean update(User user, Object id) throws HRProjectDAOException {
        boolean isUpdated = false;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(UPDATE_USER);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setString(4, user.getStatus());
            ps.setInt(5, (int)id);
            ps.executeUpdate();
            isUpdated = true;
        } catch (SQLException e) {
            throw new HRProjectDAOException("Database error", e);
        }finally{
            close(ps);
        }
        return isUpdated;
    }

    public User findUserById(int id) throws HRProjectDAOException{
        User user = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SELECT_USER_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5));
            }
        } catch (SQLException e) {
            throw new HRProjectDAOException("Database error", e);
        }finally{
            close(ps);
        }
        return user;
    }

    public User findUserByLogin(String login) throws HRProjectDAOException{
        User user = null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(SELECT_USER_BY_LOGIN);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5));
            }
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error", e);
        }finally{
            close(ps);
        }
        return user;
    }

    public boolean updateUserPassword(String newPass, int id) throws HRProjectDAOException{
        boolean isUpdated = false;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(UPDATE_USER_PASSWORD);
            ps.setString(1, newPass);
            ps.setInt(2, id);
            ps.executeUpdate();
            isUpdated = true;
        }catch (SQLException e) {
            throw new HRProjectDAOException("Database error", e);
        }finally{
            close(ps);
        }
        return isUpdated;
    }

    public boolean activate(User user) throws HRProjectDAOException {
        boolean isActivated = false;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(DELETE_ENTITY);
            ps.setString(1, STATUS_A);
            ps.setInt(2, user.getId());
            ps.executeUpdate();
            isActivated = true;
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error", e);
        }finally{
            close(ps);
        }
        return isActivated;
    }
}
