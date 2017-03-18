package com.epam.zubar.hr.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.zubar.hr.command.ICommand;
import com.epam.zubar.hr.entity.Interview;
import com.epam.zubar.hr.entity.Recruter;
import com.epam.zubar.hr.entity.User;
import com.epam.zubar.hr.entity.Vacancy;
import com.epam.zubar.hr.exception.HRProjectLogicException;
import com.epam.zubar.hr.logic.InterviewLogic;
import com.epam.zubar.hr.logic.RecruterLogic;
import com.epam.zubar.hr.logic.UserLogic;
import com.epam.zubar.hr.logic.VacancyLogic;

/**
 * Displays to admin recruiters info, also gives info about recruiters
 * interviews and vacancies.
 * @author Mikalay Zubar
 *
 */
public class DisplayRecruiterCommand implements ICommand{

    private static final Logger LOGGER = LogManager.getLogger(DisplayRecruiterCommand.class);
    private static final String REC_ID_PARAM = "rec_id";
    private static final String STATUS_A = "active";

    @Override
    public String execute(HttpServletRequest request) {
        String url = null;
        String recId = request.getParameter(REC_ID_PARAM);
        int id = Integer.parseInt(recId);
        try{
            RecruterLogic rl = new RecruterLogic();
            UserLogic ul = new UserLogic();
            HttpSession session = request.getSession();
            InterviewLogic il = new InterviewLogic();
            VacancyLogic vl = new VacancyLogic();
            Recruter recruter = rl.findRecruterById(id);
            User user = ul.findUserById(id);
            List<Interview> interviews = il.findInterviewsByRecId(id);
            if(interviews.isEmpty()){
                request.setAttribute("are_interviews", true);
            }
            List<Vacancy> vacancies = vl.findVacanciesByRecId(id);
            if(vacancies.isEmpty() ){
                request.setAttribute("are_vacancies", true);
                if(user.getStatus().equals(STATUS_A)){
                    request.setAttribute("could_be_deleted", true);
                }
            }
            if(user.getStatus().equals("blocked")){
                request.setAttribute("could_be_restored", true);
            }
            session.setAttribute("recruiter", recruter);
            request.setAttribute("interviews", interviews);
            request.setAttribute("vacancies", vacancies);
            url = BUNDLE.getString("admim_rec_page");
        }catch (HRProjectLogicException e) {
            LOGGER.error(e);
        }
        return url;
    }

}

