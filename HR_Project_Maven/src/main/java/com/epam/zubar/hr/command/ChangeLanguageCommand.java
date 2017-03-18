package com.epam.zubar.hr.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * Command to chose preferred language.
 * @author Mikalay Zubar
 *
 */

public class ChangeLanguageCommand implements ICommand{

    private static final String PARAM_LANG = "lang";

    @Override
    public String execute(HttpServletRequest request) {
        String url = BUNDLE.getString("welcome");
        String language = request.getParameter(PARAM_LANG);
        //request.setAttribute("locale", language);
        HttpSession session = request.getSession();
        session.setAttribute("locale", language);
        return url;
    }

}
