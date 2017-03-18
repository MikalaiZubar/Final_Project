package com.epam.zubar.hr.command.recruiter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.zubar.hr.command.ICommand;
import com.epam.zubar.hr.entity.Candidate;
import com.epam.zubar.hr.entity.Interview;
import com.epam.zubar.hr.exception.HRProjectLogicException;
import com.epam.zubar.hr.logic.CandidateLogic;
import com.epam.zubar.hr.logic.InterviewLogic;

/**
 * Displays interview info for recruiter.
 * @author Mikalay Zubar
 *
 */
public class ShowInterviewCommand implements ICommand{

    private static final Logger LOGGER = LogManager.getLogger(ShowInterviewCommand.class);
    private static final String DATE_PARAM = "date";
    private static final String CAND_ID_PARAM = "cand_id";

    @Override
    public String execute(HttpServletRequest request) {
        String url = null;
        String date = request.getParameter(DATE_PARAM);
        String candId = request.getParameter(CAND_ID_PARAM);
        int candidateId = Integer.parseInt(candId);
        InterviewLogic il = new InterviewLogic();
        CandidateLogic cl = new CandidateLogic();
        HttpSession session = request.getSession();
        try{
            Interview interview = il.findInterviewById(date);
            Candidate candidate = cl.findCandidateById(candidateId);
            session.setAttribute("interview", interview);
            session.setAttribute("candidate", candidate);
            url = BUNDLE.getString("rec_interview_page");

        }catch (HRProjectLogicException e){
            LOGGER.error(e);		}
        return url;
    }

}
