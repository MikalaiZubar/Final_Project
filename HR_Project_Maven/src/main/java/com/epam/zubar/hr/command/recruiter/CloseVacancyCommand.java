package com.epam.zubar.hr.command.recruiter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.zubar.hr.command.ICommand;
import com.epam.zubar.hr.entity.Vacancy;
import com.epam.zubar.hr.exception.HRProjectLogicException;
import com.epam.zubar.hr.logic.VacancyLogic;

/**
 * Let's recruiter to close the vacancy, removes vacancy from active list.
 * @author Mikalay Zubar
 *
 */
public class CloseVacancyCommand implements ICommand{

    private static final Logger LOGGER = LogManager.getLogger(CloseVacancyCommand.class);
    private static final String VAC_ID_PARAM = "vac_id";

    @Override
    public String execute(HttpServletRequest request) {
        String url = null;
        String vacId = request.getParameter(VAC_ID_PARAM);
        int id = Integer.parseInt(vacId);
        try{
            VacancyLogic vl = new VacancyLogic();
            Vacancy vacancy = vl.findVacancyById(id);
            HttpSession session = request.getSession();
            List<Vacancy> recVacancies = (List<Vacancy>) session.getAttribute("rec_vacancies");
            recVacancies.remove(vacancy);
            vl.deleteVacancy(vacancy);
            session.setAttribute("rec_vacancies", recVacancies);
            session.setAttribute("is_empty_list", true);
            url = BUNDLE.getString("recruter_page");
        }catch (HRProjectLogicException e){
            LOGGER.error(e);
        }
        return url;
    }

}
