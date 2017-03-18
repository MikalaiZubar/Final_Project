package com.epam.zubar.hr.command.recruiter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.zubar.hr.command.ICommand;
import com.epam.zubar.hr.entity.Candidate;

/**
 * Redirects recruiter to assign interview form.
 * @author Mikalay Zubar
 *
 */
public class AssignNewInterviewCommand implements ICommand{


    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Candidate> candidates = (List<Candidate>) session.getAttribute("cand_for_vac");
        request.setAttribute("cands_for_assign", candidates);
        String 	url = BUNDLE.getString("assign_interview");

        return url;
    }

}
