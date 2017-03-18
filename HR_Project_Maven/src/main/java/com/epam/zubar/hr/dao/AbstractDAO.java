package com.epam.zubar.hr.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.epam.zubar.hr.entity.Entity;
import com.epam.zubar.hr.exception.HRProjectDAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * is a generic class that contains methods allowing to operate with a database
 * (extract information, update or add data). The specific implementation of these methods depends
 * on a type of database used (e.g. MySQL database, XML, etc.).
 * @author Mikalay Zubar
 *
 * @param <T>
 */
public abstract class AbstractDAO <T extends Entity> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

    protected Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract List<T> findAll() throws HRProjectDAOException;

    public abstract boolean delete(T entity) throws HRProjectDAOException;

    public abstract boolean insert(T entity) throws HRProjectDAOException;

    public abstract boolean update(T entity, Object id) throws HRProjectDAOException;

    public void close(Statement st){

        try {
            if(st != null){
                st.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to close statement", e);
        }

    }
}