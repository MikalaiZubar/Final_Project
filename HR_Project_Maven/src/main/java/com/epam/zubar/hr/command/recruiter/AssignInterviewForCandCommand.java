package com.epam.zubar.hr.command.recruiter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.zubar.hr.command.ICommand;
import com.epam.zubar.hr.entity.Interview;
import com.epam.zubar.hr.entity.Recruter;
import com.epam.zubar.hr.entity.Vacancy;
import com.epam.zubar.hr.exception.HRProjectLogicException;
import com.epam.zubar.hr.logic.InterviewLogic;

/**
 * Let's recruiter to assign an interview for a candidate.
 * @author Mikalay Zubar
 *
 */
public class AssignInterviewForCandCommand implements ICommand{

    private static final Logger LOGGER = LogManager.getLogger(AssignInterviewForCandCommand.class);
    private static final String CAND_PARAM = "rec_cand";
    private static final String DATE_PARAM = "int_date";
    private static final String TIME_PARAM = "time";
    private static final String COMMENT_PARAM = "comment";
    private static final String STATUS = "assigned";
    private static final String NUMBER = "first";
    private static final int DATE_LENGTH = 16;

    @Override
    public String execute(HttpServletRequest request) {
        String url = null;
        String candId = request.getParameter(CAND_PARAM);
        int candidateId = Integer.parseInt(candId);
        HttpSession session = request.getSession();
        Vacancy vacancy = (Vacancy) session.getAttribute("the_vacancy");
        int vacancyId = vacancy.getId();
        Recruter recruiter = (Recruter) session.getAttribute("recruiter");
        int recruiterId = recruiter.getId();
        String date = request.getParameter(DATE_PARAM);
        String time = request.getParameter(TIME_PARAM);
        String dateTime = date + " " + time;
        String comment = request.getParameter(COMMENT_PARAM);
        Interview interview = new Interview(dateTime, candidateId, recruiterId, STATUS, NUMBER, comment, vacancyId);
        try{
            InterviewLogic il = new InterviewLogic();
            boolean isBusy = checkInterviewData(dateTime);
            if(isBusy){
                request.setAttribute("date_is_busy", true);
                url = BUNDLE.getString("assign_interview");
            }else{
                il.addNewInterview(interview);
                url = BUNDLE.getString("recruter_page");
            }
        }catch (HRProjectLogicException e) {
            LOGGER.error(e);
        }
        return url;
    }

    public boolean checkInterviewData(String date) throws HRProjectLogicException{
        boolean isBusy = false;
        InterviewLogic il = new InterviewLogic();
        List<Interview> interviews = il.getInterviewesList();
        for(Interview i: interviews){
            if(date.equals(i.getDate().substring(0, DATE_LENGTH))){
                isBusy = true;
                break;
            }
        }
        return isBusy;
    }

}
