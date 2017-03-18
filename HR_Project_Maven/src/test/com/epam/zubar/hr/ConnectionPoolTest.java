package com.epam.zubar.hr;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.epam.zubar.hr.db.ConnectionPool;

/**
 * Test is used for testing the correct
 * initialization of ConnectionPool and correct operation of getConnection()
 * method.
 * @author Mikalay Zubar
 *
 */
public class ConnectionPoolTest {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolTest.class);

    /**
     * Tests the correct initialization of ConnectionPool
     */
    @Test
    public void connectionPoolInitTest() {
        ConnectionPool pool = ConnectionPool.getPool();
        LOGGER.info("The ConnectionPool has been initialized: " + (pool != null));
        Assert.assertNotNull(pool);
    }

    /**
     * Tests the correctness of getPool() method.
     */
    @Test
    public void getConnectionTest() {
        ConnectionPool pool = ConnectionPool.getPool();
        Connection connection = pool.getConnection();
        LOGGER.info("Connection has been taken from pool: " + (connection != null));
        Assert.assertNotNull(connection);
    }
}
