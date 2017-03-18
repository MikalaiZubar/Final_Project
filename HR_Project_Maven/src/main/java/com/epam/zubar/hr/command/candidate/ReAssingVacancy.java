package com.epam.zubar.hr.command.candidate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.zubar.hr.command.ICommand;
import com.epam.zubar.hr.entity.User;
import com.epam.zubar.hr.entity.VacCandConnector;
import com.epam.zubar.hr.entity.Vacancy;
import com.epam.zubar.hr.exception.HRProjectLogicException;
import com.epam.zubar.hr.logic.VacCandConnectorLogic;

/**
 * Let's candidate an opportunity to reassign refused vacancy.
 * @author Mikalay Zubar
 *
 */
public class ReAssingVacancy implements ICommand{

    private static final Logger LOGGER = LogManager.getLogger(ReAssingVacancy.class);
    private static final String VCC_RESULT = "in progress";

    @Override
    public String execute(HttpServletRequest request) {
        String url = null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Vacancy vacancy = (Vacancy) session.getAttribute("vacancy");
        String dateOfSign = getCurrentDate();
        VacCandConnector vcc = new VacCandConnector(vacancy.getId(), user.getId(), dateOfSign, VCC_RESULT);
        VacCandConnectorLogic vccl = new VacCandConnectorLogic();
        try{
            vccl.updateVCC(vcc, user.getId());
            List<VacCandConnector> vccList = vccl.findAllNewVacByCandId(user.getId());
            session.setAttribute("assigned_vacancies", vccList);
            url = BUNDLE.getString("candidate_page");
        }catch (HRProjectLogicException e){
            e.printStackTrace();
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
