package com.epam.zubar.hr.command.recruiter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.zubar.hr.command.ICommand;
import com.epam.zubar.hr.entity.Candidate;
import com.epam.zubar.hr.entity.Interview;
import com.epam.zubar.hr.entity.VacCandConnector;
import com.epam.zubar.hr.entity.Vacancy;
import com.epam.zubar.hr.exception.HRProjectLogicException;
import com.epam.zubar.hr.logic.CandidateLogic;
import com.epam.zubar.hr.logic.InterviewLogic;
import com.epam.zubar.hr.logic.VacCandConnectorLogic;
import com.epam.zubar.hr.logic.VacancyLogic;

/**
 * Displays vacancy info recruiter.
 * @author Mikalay Zubar
 *
 */
public class DisplayVacancyForRecCommand implements ICommand{

    private static final Logger LOGGER = LogManager.getLogger(DisplayVacancyForRecCommand.class);
    private static final String VAC_ID_PARAM = "vac_id";

    @Override
    public String execute(HttpServletRequest request) {
        String url = null;
        HttpSession session = request.getSession();
        String id = request.getParameter(VAC_ID_PARAM);
        int vacancyId = Integer.parseInt(id);
        VacCandConnectorLogic vccl = new VacCandConnectorLogic();
        VacancyLogic vl = new VacancyLogic();
        CandidateLogic cl = new CandidateLogic();
        InterviewLogic il = new InterviewLogic();
        try{
            Vacancy vacancy = vl.findVacancyById(vacancyId);
            request.setAttribute("recuiter_vac", vacancy);
            List<Candidate> candidates = new ArrayList<>();
            List<VacCandConnector> vccList = vccl.findAllCandByVacId(vacancyId);
            for(VacCandConnector vcc: vccList){
                Candidate c = cl.findCandidateById(vcc.getCandidateId());
                candidates.add(c);
            }
            List<Interview> interviews = il.findInterviewsByVacId(vacancyId);
            if(interviews.isEmpty()){
                request.setAttribute("is_empty", true);
            }else{
                request.setAttribute("is_empty", false);
            }
            if(candidates.isEmpty()){
                request.setAttribute("no_candidates", true);
            }else{
                request.setAttribute("no_candidates", false);
            }
            if(candidates.isEmpty() || candidates.size()==interviews.size()){ //all possible candidates assigned
                request.setAttribute("can_assign", false);
            }else{
                request.setAttribute("can_assign", true);
            }
            session.setAttribute("cand_for_vac", candidates);
            session.setAttribute("the_vacancy", vacancy);
            request.setAttribute("interviews_on_vac", interviews);
            url = BUNDLE.getString("recruiter_vac_page");
        }catch (HRProjectLogicException e) {
            LOGGER.error(e);
        }
        return url;
    }


}

