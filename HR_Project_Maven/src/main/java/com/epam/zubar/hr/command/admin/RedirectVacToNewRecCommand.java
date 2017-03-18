package com.epam.zubar.hr.command.admin;

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
import com.epam.zubar.hr.logic.VacancyLogic;

/**
 * Gets opportunity to admin to reassign vacancy to another recruiter
 * in case of illness or retirement of the recruiter.
 * @author Mikalay Zubar
 *
 */
public class RedirectVacToNewRecCommand implements ICommand{

    private static final Logger LOGGER = LogManager.getLogger(RedirectVacToNewRecCommand.class);
    private static final String VACANCY_ID_PARAM = "vac_id";
    private static final String RECRUITER_ID_PARAM = "rec_id";

    @Override
    public String execute(HttpServletRequest request) {
        String url = null;
        String vacId = request.getParameter(VACANCY_ID_PARAM);
        int vacancyId = Integer.parseInt(vacId);
        String recId = request.getParameter(RECRUITER_ID_PARAM);
        int recruiterId = Integer.parseInt(recId);
        try{
            VacancyLogic vl = new VacancyLogic();
            Vacancy vacancy = vl.findVacancyById(vacancyId);
            vacancy.setRecruterId(recruiterId);
            vl.updateVacancy(vacancy, vacancyId);
            InterviewLogic il = new InterviewLogic();
            List<Interview> interviews = il.findInterviewsByVacId(vacancyId); //finding all interviews for the vacancy
            if(!interviews.isEmpty()){
                for(Interview i: interviews){
                    i.setRecruterId(recruiterId);			//reassigning interview to a new recruiter
                    il.updateInterview(i, i.getDate());   //updating interview in DB
                }
            }
            HttpSession session = request.getSession();
            Recruter recruter = (Recruter) session.getAttribute("recruiter");
            List<Interview> recInts = il.findInterviewsByRecId(recruter.getId());
            request.setAttribute("interviews", recInts);
            List<Vacancy> recVacs = vl.findVacanciesByRecId(recruter.getId());
            if(recVacs.isEmpty()){
                request.setAttribute("could_be_deleted", true);
            }
            request.setAttribute("vacancies", recVacs);
            url = BUNDLE.getString("admim_rec_page");
        }catch (HRProjectLogicException e){
            LOGGER.error(e);
        }
        return url;
    }

}