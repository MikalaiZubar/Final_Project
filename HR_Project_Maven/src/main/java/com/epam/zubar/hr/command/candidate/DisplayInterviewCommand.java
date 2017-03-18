package com.epam.zubar.hr.command.candidate;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.zubar.hr.command.ICommand;
import com.epam.zubar.hr.entity.Interview;
import com.epam.zubar.hr.entity.Recruter;
import com.epam.zubar.hr.exception.HRProjectLogicException;
import com.epam.zubar.hr.logic.RecruterLogic;
/**
 * Displays particular interview info to the candidate.
 * @author Mikalay Zubar
 *
 */
public class DisplayInterviewCommand implements ICommand{

    private static final Logger LOGGER = LogManager.getLogger(DisplayInterviewCommand.class);
    private static final String REC_ID = "rec_id";
    private static final String DATE = "date";

    @Override
    public String execute(HttpServletRequest request) {
        String url = null;
        String id = request.getParameter(REC_ID);
        int recruterId = Integer.parseInt(id);
        String date = request.getParameter(DATE);
        HttpSession session = request.getSession();
        try{
            List<Interview> interviews = (List<Interview>) session.getAttribute("interviews");
            Interview interview = null;
            for(Interview i : interviews){  //looking for particular interview
                if(i.getDate().equals(date)){
                    interview = i;
                }
            }
            RecruterLogic rl = new RecruterLogic();
            Recruter recruter = rl.findRecruterById(recruterId);
            request.setAttribute("recruter", recruter);
            request.setAttribute("interview", interview);
            url = BUNDLE.getString("cand_interview_page");
        }catch (HRProjectLogicException e) {
            LOGGER.error(e);
        }
        return url;

    }

}
