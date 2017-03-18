package com.epam.zubar.hr.command.candidate;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.zubar.hr.command.ICommand;
import com.epam.zubar.hr.entity.Candidate;
import com.epam.zubar.hr.entity.User;
import com.epam.zubar.hr.exception.HRProjectLogicException;
import com.epam.zubar.hr.logic.CandidateLogic;
import com.epam.zubar.hr.logic.UserLogic;
import com.epam.zubar.hr.utils.PasswordHasher;

import static com.epam.zubar.hr.utils.Validator.validateLogin;
import static com.epam.zubar.hr.utils.Validator.validatePassword;

/**
 * Adds a new candidate.
 * @author Mikalay Zubar
 *
 */
public class AddCandidateCommand implements ICommand{

    private static final Logger LOGGER = LogManager.getLogger(AddCandidateCommand.class);
    private static final String USER_PARAM_LOGIN = "login";
    private static final String USER_PARAM_PASS = "pass1";
    private static final String USER_PARAM_PASSCONFIRM = "pass2";
    private static final String CAND_PARAM_NAME = "name";
    private static final String CAND_PARAM_SURNAME = "surname";
    private static final String CAND_PARAM_PHONE = "phone";
    private static final String CAND_PARAM_EMAIL = "email";
    private static final String CAND_PARAM_BIRTHDATE = "birthdate";
    private static final String CAND_PARAM_SEX = "sex";
    private static final String CAND_PARAM_INFO = "desc";
    private static final String ROLE = "candidate";
    private static final String STATUS = "active";

    @Override
    public String execute(HttpServletRequest request) {
        String url = BUNDLE.getString("add_candidate"); // change to candidate page
        String phone = request.getParameter(CAND_PARAM_PHONE);

        if(phone != null){
            String login = request.getParameter(USER_PARAM_LOGIN);
            String pass = request.getParameter(USER_PARAM_PASS);
            String passConf = request.getParameter(USER_PARAM_PASSCONFIRM);
            String hashedpass = PasswordHasher.generateSecuredPassHash(passConf);
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
                        String name = request.getParameter(CAND_PARAM_NAME);
                        String surname = request.getParameter(CAND_PARAM_SURNAME);
                        String email = request.getParameter(CAND_PARAM_EMAIL);
                        String birthDate = request.getParameter(CAND_PARAM_BIRTHDATE);
                        String sex = request.getParameter(CAND_PARAM_SEX);
                        String info = request.getParameter(CAND_PARAM_INFO);
                        Candidate candidate = new Candidate(id, name, surname, email, sex, birthDate, phone, info, null);
                        boolean isAdded = addNewCandidate(candidate);
                        if(isAdded){
                            request.setAttribute("success", true);
                            LOGGER.info("New candidate " + candidate.getFirstName() + " "+
                                    candidate.getLastName()	+ " added successfully");
                        }
                        request.setAttribute("candidate_added", true);
                    }else{
                        request.setAttribute("validation_failed", true);
                    }
                }else{
                    request.setAttribute("login_is_busy", true);
                }
            } catch (HRProjectLogicException e) {
                LOGGER.error(e);

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



    /* adds new candidate to database*/
    private boolean addNewCandidate(Candidate candidate) throws HRProjectLogicException{
        //add some validation logic
        CandidateLogic cl = new CandidateLogic();
        return cl.addNewCandidate(candidate);
    }
}
