package com.epam.zubar.hr.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Redirects user to a first page after authorization
 * depending on users 'role'.
 * @author Mikalay Zubar
 *
 */

public class GoFirstPageCommand implements ICommand{

    private static final String ADMIN_ROLE = "admin";
    private static final String CANDIDATE_ROLE = "candidate";
    private static final String RECRUITER_ROLE = "recrutor";

    @Override
    public String execute(HttpServletRequest request) {
        String url = null;
        String role = request.getParameter("user_role");
        switch(role){
            case ADMIN_ROLE:
                url = BUNDLE.getString("admin_page");
                break;
            case CANDIDATE_ROLE:
                url = BUNDLE.getString("candidate_page");
                break;
            case RECRUITER_ROLE:
                url = BUNDLE.getString("recruter_page");
                break;
            default:
                url = BUNDLE.getString("welcome");
        }
        return url;
    }

}

