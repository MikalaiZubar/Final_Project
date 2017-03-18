package com.epam.zubar.hr.command.candidate;

import java.util.ArrayList;
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
import com.epam.zubar.hr.logic.VacancyLogic;

/**
 * Displays vacancy info for the candidate.
 * @author Mikalay Zubar
 *
 */
public class DisplayVacancyCommand  implements ICommand{

    private static final Logger LOGGER = LogManager.getLogger(DisplayVacancyCommand.class);
    private static final String VAC_ID_PARAM = "vac_id";
    private static final String VCC_RESULT = "refused";

    @Override
    public String execute(HttpServletRequest request) {
        String url = null;
        String vacId = request.getParameter(VAC_ID_PARAM);
        int id = Integer.parseInt(vacId);
        VacancyLogic vl = new VacancyLogic();
        List<VacCandConnector> vccList = null;
        VacCandConnectorLogic vccl = new VacCandConnectorLogic();
        try {
            Vacancy vacancy = vl.findVacancyById(id);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            session.setAttribute("vacancy", vacancy);
            vccList = vccl.findAllVacByCandId(user.getId());
            List<Integer> idList = new ArrayList<>();
            List<String> statusList = new ArrayList<>();
            for(VacCandConnector vcc: vccList){
                idList.add(vcc.getVacancyId());
                statusList.add(vcc.getResult());
            }
            if(vccList.isEmpty()){
                request.setAttribute("no_vacancy", true);
            }
            if(idList.contains(id)){
                VacCandConnector vcc = vccl.findVCCByUserAndVac(user, id);
                if(vcc.getResult().equals(VCC_RESULT)){
                    request.setAttribute("refused_vacancy", true);  //checks vac was assigned but refused by cand
                }else{
                    request.setAttribute("no_vacancy", false);
                }
            }else{
                request.setAttribute("no_vacancy", true);
            }
            url = BUNDLE.getString("vacancy_page");
        } catch (HRProjectLogicException e) {
            LOGGER.error(e);
        }
        return url;
    }

}
