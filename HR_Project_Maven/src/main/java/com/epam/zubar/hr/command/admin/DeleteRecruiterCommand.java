package com.epam.zubar.hr.command.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.zubar.hr.command.ICommand;
import com.epam.zubar.hr.entity.Recruter;
import com.epam.zubar.hr.entity.User;
import com.epam.zubar.hr.exception.HRProjectLogicException;
import com.epam.zubar.hr.logic.RecruterLogic;
import com.epam.zubar.hr.logic.UserLogic;

/**
 * Class is invoked when administrator tries to "delete" recruiter,
 * actually makes recruiter's status blocked.
 * @author Mikalay Zubar
 *
 */
public class DeleteRecruiterCommand implements ICommand{
    private static final Logger LOGGER = LogManager.getLogger(DeleteRecruiterCommand.class);
    private static final String STATUS_A = "active";

    @Override
    public String execute(HttpServletRequest request) {
        String url = null;
        String recId = request.getParameter("rec_id");
        int id = Integer.parseInt(recId);
        UserLogic ul = new UserLogic();
        try{
            User user = ul.findUserById(id);
            ul.deleteUser(user);
            RecruterLogic rl = new RecruterLogic();
            List<Recruter> recList = rl.getRecrutersList();
            List<Recruter> blockList = new ArrayList<>();
            List<Recruter> activeList = new ArrayList<>();
            for(Recruter r: recList){
                User u = ul.findUserById(r.getId());
                if(u.getStatus().equals(STATUS_A)){
                    activeList.add(r);
                }else{
                    blockList.add(r);
                }
            }
            HttpSession session = request.getSession();
            session.setAttribute("active_list", activeList);
            session.setAttribute("blocked_list", blockList);
            url = BUNDLE.getString("admin_page");
        }catch (HRProjectLogicException e){
            LOGGER.error(e);
        }
        return url;
    }

}

