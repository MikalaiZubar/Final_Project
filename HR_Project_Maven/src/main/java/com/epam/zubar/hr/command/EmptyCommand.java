package com.epam.zubar.hr.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Is invoked when no 'action' parameter is specified in
 * the request object. It redirects the request to default page.
 * @author Mikalay Zubar
 *
 */
public class EmptyCommand implements ICommand{

    public static final String PARAM_LANG = "lang";
    public static final String DEFAULT_LANG = "eng";

    /*
     * If there is no command - returns user to welcome page.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String root = BUNDLE.getString("welcome");
        String lang = request.getParameter(PARAM_LANG);

        if(lang == null){
            lang = DEFAULT_LANG;
        }
        request.setAttribute("locale", lang);
        return root;
    }


}

