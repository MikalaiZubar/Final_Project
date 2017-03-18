package com.epam.zubar.hr.controller;

import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.zubar.hr.command.CommandFactory;
import com.epam.zubar.hr.command.ICommand;
import com.epam.zubar.hr.db.ConnectionPool;
/**
 * Is a Servlet implementation class that controls communication between
 * Command interface and JSPs. It calls the proper Command class and then
 * redirects the request to the proper JSP.
 * @author Mikalay Zubar
 *
 */

@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 5339018926300404145L;
    private static final Logger LOGGER = LogManager.getLogger(ControllerServlet.class);

    public ControllerServlet() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Performs a set of actions when a HTTP GET method is used
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = processRequest(request, response);
        // forwarding the request to a proper JSP
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Performs a set of actions when a HTTP POST method is used
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = processRequest(request, response);
        //avoiding of form resubmitting post-redirect-get pattern
        if(request.getAttribute("success") == null){
            request.getRequestDispatcher(url).forward(request, response);
        } else{
            response.sendRedirect(url);
            request.setAttribute("success", null);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getPool().closeAllConnections();

        // manually deregister MySQL JDBC drivers to avoid Tomcat warnings
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        Enumeration<Driver> drivers = DriverManager.getDrivers();

        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver.getClass().getClassLoader() == cl) {
                try {
                    LOGGER.info("Deregistering JDBC driver: " + driver);
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException ex) {
                    LOGGER.error("Error deregistering JDBC driver " + driver, ex);
                }
            } else {
                LOGGER.trace("JDBC driver " + driver
                        + "doesn't belong to this ClassLoader");
            }
        }

    }

    protected String processRequest(HttpServletRequest request,
                                    HttpServletResponse response) throws ServletException, IOException {
        ICommand command = CommandFactory.getInstance().getCommand(request);
        String url = command.execute(request);
        // putting the reconstructed ControllerServlet URL into request
        request.setAttribute("base_url", request.getRequestURL().toString());
        return url;
    }
}
