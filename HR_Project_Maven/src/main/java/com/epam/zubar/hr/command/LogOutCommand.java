package com.epam.zubar.hr.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Invalidates session and redirects user to a welcome page.
 * @author Mikalay Zubar
 *
 */
public class LogOutCommand implements ICommand{

    @Override
    public String execute(HttpServletRequest request) {
        String url = BUNDLE.getString("welcome");
        HttpSession session = request.getSession();
        session.invalidate();
        return url;
    }

}
