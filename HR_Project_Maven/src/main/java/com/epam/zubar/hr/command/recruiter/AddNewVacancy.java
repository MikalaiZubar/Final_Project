package com.epam.zubar.hr.command.recruiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.zubar.hr.command.ICommand;
import com.epam.zubar.hr.entity.Recruter;
import com.epam.zubar.hr.entity.Vacancy;
import com.epam.zubar.hr.exception.HRProjectLogicException;
import com.epam.zubar.hr.logic.VacancyLogic;

/**
 * Invoked when recruiter tries to add a new vacancy.
 * @author Mikalay Zubar
 *
 */
public class AddNewVacancy implements ICommand{

    private static final Logger LOGGER = LogManager.getLogger(AddNewVacancy.class);
    private static final String NAME_PARAM = "vac_name";
    private static final String MIN_SALARY_PARAM = "min_salary";
    private static final String MAX_SALARY_PARAM = "max_salary";
    private static final String INFO_PARAM = "vac_info";
    private static final String STATUS = "open";


    @Override
    public String execute(HttpServletRequest request) {
        String url = null;
        String name = request.getParameter(NAME_PARAM);
        String info = request.getParameter(INFO_PARAM);
        Double minSalary = Double.parseDouble(request.getParameter(MIN_SALARY_PARAM));
        Double maxSalary = Double.parseDouble(request.getParameter(MAX_SALARY_PARAM));
        String date = getCurrentDate();
        String status = STATUS;
        HttpSession session = request.getSession();
        Recruter recruiter = (Recruter) session.getAttribute("recruiter");
        int id = recruiter.getId();
        Vacancy vacancy = new Vacancy(date, name, info, minSalary, maxSalary, status, id);
        try{
            VacancyLogic vl = new VacancyLogic();
            boolean isAdded = vl.addNewVacancy(vacancy);
            if(isAdded){
                request.setAttribute("success", true);
                LOGGER.info("New vacancy added successfully " + vacancy.getName());
            }
            List<Vacancy> vacList = vl.findOpenVacanciesByRecId(id);
            session.setAttribute("rec_vacancies", vacList);
            session.setAttribute("is_empty_list", true);
            url = BUNDLE.getString("recruter_page");
        }catch (HRProjectLogicException e){
            LOGGER.error(e);
        }
        return url;
    }

    //Supplementary method sets current date of assignment
    private String getCurrentDate(){
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(d);
        return date;
    }
}
