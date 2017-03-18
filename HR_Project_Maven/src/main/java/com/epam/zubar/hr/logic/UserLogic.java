package com.epam.zubar.hr.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.epam.zubar.hr.dao.AbstractDAO;
import com.epam.zubar.hr.dao.daofactory.AbstractDAOFactory;
import com.epam.zubar.hr.dao.daofactory.FactoryType;
import com.epam.zubar.hr.dao.mysqldao.UserDAO;
import com.epam.zubar.hr.db.ConnectionPool;
import com.epam.zubar.hr.entity.User;
import com.epam.zubar.hr.exception.HRProjectDAOException;
import com.epam.zubar.hr.exception.HRProjectLogicException;
import com.epam.zubar.hr.utils.PasswordHasher;
import com.epam.zubar.hr.utils.Validator;

/**
 * contains various methods that use DAO layer to
 * retrieve information  from a database, add or update
 * data, etc. These methods will be further used by Command layer.
 * @author Mikalay Zubar
 *
 */
public class UserLogic {

    private ConnectionPool pool;
    private Connection connection;
    private AbstractDAOFactory factory;

    public UserLogic(){
        pool = ConnectionPool.getPool();
    }

    public boolean deleteUser(User user) throws HRProjectLogicException{
        boolean isDeleted = false;
        AbstractDAO<User> dao = initDAOFactory().getUserDAO();
        try{
            isDeleted = dao.delete(user);
        }catch (HRProjectDAOException e){
            throw new HRProjectLogicException("Error. Unable to delete User!", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return isDeleted;
    }

    public List<User> getUsersList() throws HRProjectLogicException{
        List<User> users = new ArrayList<>();
        AbstractDAO<User> userDao = initDAOFactory().getUserDAO();
        try {
            users = userDao.findAll();
        } catch (HRProjectDAOException e) {
            throw new HRProjectLogicException(
                    "Error. Unable to retrieve the list of Users!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return users;
    }

    public User findUserByLogin(String login) throws HRProjectLogicException{
        User user = null;
        UserDAO userDao = (UserDAO) initDAOFactory().getUserDAO();
        try{
            user = userDao.findUserByLogin(login);
        }catch(HRProjectDAOException e){
            throw new HRProjectLogicException("Error. Unable to retrieve a User!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return user;
    }
    public User findUserById(int id) throws HRProjectLogicException{
        User user = null;
        UserDAO userDao = (UserDAO) initDAOFactory().getUserDAO();
        try{
            user = userDao.findUserById(id);
        }catch(HRProjectDAOException e){
            throw new HRProjectLogicException("Error. Unable to retrieve a User!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return user;
    }

    public boolean updateUserPassword(String password, int userId) throws HRProjectLogicException{
        boolean isUpdated = false;
        UserDAO userDao = (UserDAO) initDAOFactory().getUserDAO();
        try{
            String hashedPass = PasswordHasher.generateSecuredPassHash(password);
            isUpdated = userDao.updateUserPassword(hashedPass, userId);
        }catch (HRProjectDAOException e){
            throw new HRProjectLogicException("Error. Unable to update User's password!", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return isUpdated;
    }


    // Method checks the correctness of User security data (login/password).

    public boolean checkUserData(String login, String password) throws HRProjectLogicException{
        boolean isCorrect = false;
        User user = null;
        UserDAO userDao = (UserDAO) initDAOFactory().getUserDAO();
        try{
            user = userDao.findUserByLogin(login);
            if(user != null && Validator.validatePassword(password)
                    && PasswordHasher.checkPassword(password, user.getPassword())){
                isCorrect = true;
            }
        }catch (HRProjectDAOException e){
            throw new HRProjectLogicException("Error. Unable to retrieve a User!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return isCorrect;
    }

    public boolean updateUser(User user, int userId) throws HRProjectLogicException{
        boolean isUpdated = false;
        AbstractDAO<User> dao = initDAOFactory().getUserDAO();
        try{
            isUpdated = dao.update(user, userId);
        }catch(HRProjectDAOException e) {
            throw new HRProjectLogicException("Error. Unable to update User's data!", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return isUpdated;
    }

    public boolean addNewUser(User user) throws HRProjectLogicException{
        boolean isAdded = false;
        AbstractDAO<User> dao = initDAOFactory().getUserDAO();
        try{
            isAdded = dao.insert(user);
        }catch(HRProjectDAOException e) {
            throw new HRProjectLogicException("Error. Unable to insert User's data!", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return isAdded;
    }

    public boolean activateUser(User user) throws HRProjectLogicException{
        boolean isActivated = false;
        UserDAO dao = (UserDAO) initDAOFactory().getUserDAO();
        try{
            isActivated = dao.activate(user);
        }catch (HRProjectDAOException e){
            throw new HRProjectLogicException("Error. Unable to activate User!", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return isActivated;
    }

    // initializes connection and DAO factory
    private AbstractDAOFactory initDAOFactory() {
        connection = pool.getConnection();
        factory = AbstractDAOFactory.getDAOFactory(connection, FactoryType.MYSQL);
        return factory;
    }

}

