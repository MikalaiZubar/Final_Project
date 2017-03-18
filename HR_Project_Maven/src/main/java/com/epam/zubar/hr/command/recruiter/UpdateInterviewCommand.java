package com.epam.zubar.hr.command.recruiter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.zubar.hr.command.ICommand;
import com.epam.zubar.hr.entity.Candidate;
import com.epam.zubar.hr.entity.Interview;
import com.epam.zubar.hr.entity.Recruter;
import com.epam.zubar.hr.entity.Vacancy;
import com.epam.zubar.hr.exception.HRProjectLogicException;
import com.epam.zubar.hr.logic.CandidateLogic;
import com.epam.zubar.hr.logic.InterviewLogic;
import com.epam.zubar.hr.utils.MailSender;

/**
 * Let's recruiter to update interview data.
 * @author Mikalay Zubar
 *
 */
public class UpdateInterviewCommand implements ICommand{

    private static final Logger LOGGER = LogManager.getLogger(UpdateInterviewCommand.class);
    private static final String INT_DATE_PARAM = "int_date";
    private static final String INT_TIME_PARAM = "int_time";
    private static final String STATUS_PARAM = "int_status";
    private static final String NUMBER_PARAM = "int_number";
    private static final String COMMENT_PARAM = "int_comment";

    @Override
    public String execute(HttpServletRequest request) {
        String url = null;
        HttpSession session = request.getSession();
        Interview interview = (Interview) session.getAttribute("interview");
        String id = interview.getDate();
        try{
            Interview newInterview = createInterview(request, session);
            InterviewLogic il = new InterviewLogic();
            il.updateInterview(newInterview, id);
            CandidateLogic cl = new CandidateLogic();
            Candidate candidate = cl.findCandidateById(newInterview.getCandidateId());
            MailSender.sendMessage(candidate);      //sending email - interview status changed
            url = BUNDLE.getString("recruter_page");
        }catch (HRProjectLogicException e){
            LOGGER.error(e);;
        }
        return url;
    }

    private Interview createInterview(HttpServletRequest request, HttpSession session) {
        Candidate candidate = (Candidate) session.getAttribute("candidate");
        int candidateId = candidate.getId();
        Recruter recruter = (Recruter) session.getAttribute("recruiter");
        int recruterId = recruter.getId();
        Vacancy vacancy = (Vacancy) session.getAttribute("the_vacancy");
        int vacancyId = vacancy.getId();
        String date = request.getParameter(INT_DATE_PARAM);
        String time = request.getParameter(INT_TIME_PARAM);
        String dateTime = date + " " + time;
        String status = request.getParameter(STATUS_PARAM);
        String number = request.getParameter(NUMBER_PARAM);
        String comment = request.getParameter(COMMENT_PARAM);
        return new Interview(dateTime, candidateId, recruterId, status, number, comment, vacancyId);

    }


}

