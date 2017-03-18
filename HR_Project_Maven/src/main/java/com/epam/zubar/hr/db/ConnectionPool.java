package com.epam.zubar.hr.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Provides a thread-safe pool of connections to MySQL database.
 * The pool is constructed using Singleton design pattern
 * (allows to create only 1 instance of pool). Database properties are stored in
 * a separate file. The size of the pool is limited. It contains methods for
 * extracting and releasing DB connections.
 * @author Mikalay Zubar
 *
 */
public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final String PROPERTIES_DIR = "property/database";
    private static ResourceBundle rb;
    private int poolSize;
    private ArrayBlockingQueue<Connection> connectionsPool;

    // Constructor is made private to implement Singleton design pattern
    // initializes a thread-safe queue of connections
    private ConnectionPool() {
        try {
            rb = ResourceBundle.getBundle(PROPERTIES_DIR);
            this.poolSize = Integer.parseInt(rb.getString("db.poolsize"));
            this.connectionsPool = new ArrayBlockingQueue<>(poolSize);
            fillPool();
        } catch (MissingResourceException ex) {
            LOGGER.error(ex);
            throw new RuntimeException("Missing " + PROPERTIES_DIR + " dir", ex);
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new RuntimeException("Database connection failure", ex);
        }
    }

    /*
     * Private class that contains static field where ConnectionPool object is
     * initialized. Such schema prevents creation of 2 or more ConnectionPool
     * objects in a multithread environment
     *
     * (Singleton design pattern)
     */
    private static class ConnectionPoolHolder {
        private static final ConnectionPool POOL = new ConnectionPool();
    }

    public static ConnectionPool getPool(){
        return ConnectionPoolHolder.POOL;
    }

    private void fillPool() throws SQLException{
        for(int i = 0; i < poolSize; i++){
            connectionsPool.add(openConnection());
        }
    }

    private Connection openConnection() throws SQLException {
        Connection cn = null;
        try {
            Class.forName(rb.getString("db.driver"));
            String url = rb.getString("db.url");
            String user = rb.getString("db.user");
            String pass = rb.getString("db.password");
            cn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e);
        }
        return cn;
    }

    public Connection getConnection(){
        Connection cn = null;
        try {
            // takes a connection from pool
            // waits if needed for the element to become available
            cn = connectionsPool.take();
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
        return cn;
    }

    public boolean releaseConnection(Connection cn){
        boolean isReleased = false;
        try {
            if(cn != null){
                connectionsPool.put(cn);
                isReleased = true;
            }
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
        return isReleased;
    }

    public boolean closeAllConnections(){
        boolean areClosed = false;
        try {
            for(Connection cn: connectionsPool){
                cn.close();
            }
            areClosed = true;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return areClosed;
    }


}

