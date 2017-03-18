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
 * Let's candidate to assign an interesting vacancy.
 * @author Mikalay Zubar
 *
 */
public class AssignVacancyCommand implements ICommand{

    private static final Logger LOGGER = LogManager.getLogger(AssignVacancyCommand.class);
    private static final String VCC_RESULT = "in progress";


    @Override
    public String execute(HttpServletRequest request) {
        String url = null;
        HttpSession session = request.getSession();
        Vacancy vacancy = (Vacancy) session.getAttribute("vacancy");
        User user = (User) session.getAttribute("user");
        boolean vccListEmpty = (boolean) session.getAttribute("vcc_vacs_empty");
        String dateOfSign = getCurrentDate();
        VacCandConnector vcc = new VacCandConnector(vacancy.getId(), user.getId(), dateOfSign, VCC_RESULT);
        VacCandConnectorLogic vccl = new VacCandConnectorLogic();
        try{
            vccl.addNewVCC(vcc);
            List<VacCandConnector> vccList = vccl.findAllNewVacByCandId(user.getId());
            if(!vccList.isEmpty()){
                vccListEmpty = false;
            }
            session.setAttribute("assigned_vacancies", vccList);
            session.setAttribute("vcc_vacs_empty", vccListEmpty);
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

