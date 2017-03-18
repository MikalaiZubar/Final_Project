package com.epam.zubar.hr.command.admin;

import static com.epam.zubar.hr.utils.Validator.validateLogin;
import static com.epam.zubar.hr.utils.Validator.validatePassword;

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
import com.epam.zubar.hr.utils.PasswordHasher;

/**
 * Class is invoked when administrator tries to add a new recruiter
 * in a system.
 * @author Mikalay Zubar
 *
 */
public class AddRecruiterCommand implements ICommand{

    private static final Logger LOGGER = LogManager.getLogger(AddRecruiterCommand.class);
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "pass1";
    private static final String CONFIRM_PARAM = "pass2";
    private static final String NAME_PARAM = "name";
    private static final String SURNAME_PARAM = "surname";
    private static final String PHONE_PARAM = "phone";
    private static final String EMAIL_PARAM = "email";
    private static final String ROLE = "recrutor";
    private static final String STATUS = "active";


    @Override
    public String execute(HttpServletRequest request) {
        String url = BUNDLE.getString("admin_page");
        String email = request.getParameter(EMAIL_PARAM);

        if(email != null){
            String login = request.getParameter(LOGIN_PARAM);
            String pass = request.getParameter(PASSWORD_PARAM);
            String passConf = request.getParameter(CONFIRM_PARAM);
            String hashedpass = PasswordHasher.generateSecuredPassHash(passConf);
            HttpSession session = request.getSession();
            try {
                if(checkLoginFree(request, login) && pass.equals(passConf)){
                    String role = ROLE;
                    String status = STATUS;
                    if(validateLogin(login) && validatePassword(pass)){
                        UserLogic ul = new UserLogic();
                        User user = new User(login, hashedpass, role, status);
                        ul.addNewUser(user);
                        user = ul.findUserByLogin(login);
                        int id = user.getId();
                        String name = request.getParameter(NAME_PARAM);
                        String surname = request.getParameter(SURNAME_PARAM);
                        String phone = request.getParameter(PHONE_PARAM);
                        String fullName = name + " " + surname;
                        Recruter recruter = new Recruter(id, fullName, phone, email);
                        boolean isAdded = addNewRecruiter(recruter);
                        if(isAdded){
                            request.setAttribute("success", true);
                            LOGGER.info("New recruiter added successfully " + recruter.getName());
                        }
                        List<Recruter> activeList = findActiveRecruiters();
                        session.setAttribute("active_list", activeList);
                    }else{
                        request.setAttribute("validation_failed", true);
                    }

                }else{
                    request.setAttribute("login_is_busy", true);
                }
            } catch (HRProjectLogicException e) {
                LOGGER.error(e);
                //add error page
            }

        }else{
            request.setAttribute("form_not_filled", true);
        }

        return url;
    }

    /* checks if the entered login is free */
    private boolean checkLoginFree(HttpServletRequest request,
                                   String login) throws HRProjectLogicException {
        UserLogic ul = new UserLogic();
        User user = ul.findUserByLogin(login);
        return (user == null);
    }

    private boolean addNewRecruiter(Recruter recruter) throws HRProjectLogicException{
        //add some validation logic
        RecruterLogic rl = new RecruterLogic();
        return rl.addNewRecruter(recruter);
    }

    private List<Recruter> findActiveRecruiters() throws HRProjectLogicException{
        UserLogic ul = new UserLogic();
        RecruterLogic rl = new RecruterLogic();
        List<Recruter> activRecList = new ArrayList<>();
        String status = "active";
        List<Recruter> recruiters = rl.getRecrutersList();
        for(Recruter r: recruiters){
            User user = ul.findUserById(r.getId());
            if(user.getStatus().equals(status)){
                activRecList.add(r);
            }
        }
        return activRecList;
    }



}
