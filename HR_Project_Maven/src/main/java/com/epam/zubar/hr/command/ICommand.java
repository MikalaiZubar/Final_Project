package com.epam.zubar.hr.command;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
/**
 * Is an interface that will be implemented by all
 * Command classes that process data received from JSPs.
 * @author Mikalay Zubar
 *
 */
public interface ICommand {
    ResourceBundle BUNDLE = ResourceBundle.getBundle("property/jsproots");

    String execute(HttpServletRequest request);


}

