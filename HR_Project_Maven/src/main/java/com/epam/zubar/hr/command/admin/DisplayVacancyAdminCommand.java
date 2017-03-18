package com.epam.zubar.hr.command.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.zubar.hr.command.ICommand;
import com.epam.zubar.hr.entity.Recruter;
import com.epam.zubar.hr.entity.Vacancy;
import com.epam.zubar.hr.exception.HRProjectLogicException;
import com.epam.zubar.hr.logic.RecruterLogic;
import com.epam.zubar.hr.logic.VacancyLogic;
/**
 * Displays concrete vacancy info.
 * @author Mikalay Zubar
 *
 */
public class DisplayVacancyAdminCommand implements ICommand{
    private static final Logger LOGGER = LogManager.getLogger(DisplayVacancyAdminCommand.class);
    private static final String VAC_ID_PARAM = "vac_id";
    private static final String REC_ID_PARAM = "rec_id";

    @Override
    public String execute(HttpServletRequest request) {
        String url = null;
        String vacId = request.getParameter(VAC_ID_PARAM);
        int vacancyId = Integer.parseInt(vacId);
        String recId = request.getParameter(REC_ID_PARAM);
        int recruiterId = Integer.parseInt(recId);
        try{
            RecruterLogic rl = new RecruterLogic();
            List<Recruter> recruters = rl.getRecrutersList();
            List<Recruter> toRemove = new ArrayList<>();
            for(Recruter r: recruters){
                if(r.getId() == recruiterId){
                    toRemove.add(r);			//creating list for removing
                }
            }
            recruters.removeAll(toRemove);
            VacancyLogic vl = new VacancyLogic();
            Vacancy vacancy = vl.findVacancyById(vacancyId);
            request.setAttribute("rec_list", recruters);
            request.setAttribute("vacancy", vacancy);
            url = BUNDLE.getString("admin_vac_page");
        }catch (HRProjectLogicException e){
            LOGGER.error(e);
        }
        return url;
    }


}
