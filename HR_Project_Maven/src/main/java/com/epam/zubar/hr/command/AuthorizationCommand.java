package com.epam.zubar.hr.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.zubar.hr.entity.Candidate;
import com.epam.zubar.hr.entity.Interview;
import com.epam.zubar.hr.entity.Recruter;
import com.epam.zubar.hr.entity.User;
import com.epam.zubar.hr.entity.VacCandConnector;
import com.epam.zubar.hr.entity.Vacancy;
import com.epam.zubar.hr.exception.HRProjectLogicException;
import com.epam.zubar.hr.logic.CandidateLogic;
import com.epam.zubar.hr.logic.InterviewLogic;
import com.epam.zubar.hr.logic.RecruterLogic;
import com.epam.zubar.hr.logic.UserLogic;
import com.epam.zubar.hr.logic.VacCandConnectorLogic;
import com.epam.zubar.hr.logic.VacancyLogic;

/**
 * Class invoked when user tries to authorized in a system.
 * Based on a login and password data prepare right page to user
 * be redirected on.
 * @author Mikalay Zubar
 *
 */

public class AuthorizationCommand implements ICommand{

    private static final Logger LOGGER = LogManager.getLogger(AuthorizationCommand.class);
    private static final String AUTH_PARAM_LOGIN = "login_field";
    private static final String AUTH_PARAM_PASS = "password_field";
    private static final String CANDIDATE_ROLE = "candidate";
    private static final String RECRUITER_ROLE = "recrutor";
    private static final String ADMIN_ROLE = "admin";
    private static final String VACANCY_STATUS = "open";

    @Override
    public String execute(HttpServletRequest request) {
        String url = null;
        String login = request.getParameter(AUTH_PARAM_LOGIN);
        String password = request.getParameter(AUTH_PARAM_PASS);
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute("locale");
        UserLogic ul = new UserLogic();
        try {
            if(ul.checkUserData(login, password)){
                User user = ul.findUserByLogin(login);
                String role = user.getRole();
                switch(role){
                    case CANDIDATE_ROLE:
                        createCandidateData(request, session, user, language);
                        break;
                    case RECRUITER_ROLE:
                        createRecruterData(request, session, user, language);
                        break;
                    case ADMIN_ROLE:
                        createAdminData(request, session, user, language);
                        break;
                }
                url = findProperURL(user);
                session.setAttribute("user", user);
                session.setAttribute("is_user_in", true);
            }else{
                request.setAttribute("auth_failed", true); //for error div message
                url = BUNDLE.getString("welcome");
            }
        } catch (HRProjectLogicException e) {
            LOGGER.error(e);
        }
        return url;
    }

    //defines proper url depending on user's role
    private String findProperURL(User user) {
        String url = null;
        String role = user.getRole();
        switch(role){
            case CANDIDATE_ROLE:
                url = BUNDLE.getString("candidate_page");
                break;
            case RECRUITER_ROLE:
                url = BUNDLE.getString("recruter_page");
                break;
            case ADMIN_ROLE:
                url = BUNDLE.getString("admin_page");
                break;
            default:
                url = BUNDLE.getString("welcome");
        }
        return url;
    }

    private List<Vacancy> findActiveVacancies() throws HRProjectLogicException{
        VacancyLogic vl = new VacancyLogic();
        List<Vacancy> allVacancies = vl.getVacansiesList();
        List<Vacancy> activeVac = new ArrayList<>();
        for(Vacancy v: allVacancies){
            if(v.getStatus().equals(VACANCY_STATUS)){
                activeVac.add(v);
            }
        }
        return activeVac;
    }

    private List<Interview> findAssignedInterviews(User user) throws HRProjectLogicException{
        InterviewLogic il = new InterviewLogic();
        List<Interview> interviews = il.getInterviewesList();
        List<Interview> activList = new ArrayList<>();
        for(Interview i: interviews){
            if(/*i.getStatus().equals(INTERVIEW_STATUS) && */i.getCandidateId() == user.getId()){
                activList.add(i);
            }
        }
        return activList;
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

    private List<Recruter> findBlockedRecruiters() throws HRProjectLogicException{
        UserLogic ul = new UserLogic();
        RecruterLogic rl = new RecruterLogic();
        List<Recruter> blockedRecList = new ArrayList<>();
        String status = "blocked";
        List<Recruter> recruiters = rl.getRecrutersList();
        for(Recruter r: recruiters){
            User user = ul.findUserById(r.getId());
            if(user.getStatus().equals(status)){
                blockedRecList.add(r);
            }
        }
        return blockedRecList;
    }

    public void createCandidateData(HttpServletRequest request, HttpSession session,
                                    User user, String language) throws HRProjectLogicException{
        CandidateLogic cl = new CandidateLogic();
        VacCandConnectorLogic vccl = new VacCandConnectorLogic();
        Candidate candidate = cl.findCandidateById(user.getId());

        List<Vacancy> vacList = findActiveVacancies();
        List<Interview> intList = findAssignedInterviews(user);
        if(intList.isEmpty()){
            session.setAttribute("is_empty", true);
        }else{
            session.setAttribute("is_empty", false);
        }
        List<VacCandConnector> vccList = vccl.findAllNewVacByCandId(user.getId());
        if(vccList.isEmpty()){
            session.setAttribute("vcc_vacs_empty", true);
        }else{
            session.setAttribute("vcc_vacs_empty", false);
        }
        session.setAttribute("candidate", candidate);
        session.setAttribute("vacancies", vacList);
        session.setAttribute("interviews", intList);
        session.setAttribute("assigned_vacancies", vccList);
    }

    public void createRecruterData(HttpServletRequest request, HttpSession session,
                                   User user, String language) throws HRProjectLogicException{
        RecruterLogic rl = new RecruterLogic();
        VacancyLogic vl = new VacancyLogic();
        Recruter recruter = rl.findRecruterById(user.getId());
        List<Vacancy> vacancies  = vl.findVacanciesByRecId(recruter.getId());
        List<Vacancy> recVacancies = new ArrayList<>();
        for(Vacancy v: vacancies){
            if(v.getStatus().equals("open")){
                recVacancies.add(v);
            }
        }
        if(recVacancies.isEmpty()){
            session.setAttribute("is_empty_list", false); //look for F5!!!!
        }else{
            session.setAttribute("is_empty_list", true);
        }
        session.setAttribute("rec_vacancies", recVacancies);
        session.setAttribute("recruiter", recruter);
    }

    public void createAdminData(HttpServletRequest request, HttpSession session,
                                User user, String language) throws HRProjectLogicException{
        List<Recruter> activeList  = findActiveRecruiters();
        List<Recruter> blockedList = findBlockedRecruiters();

        if(activeList.isEmpty()){
            session.setAttribute("is_active_list", false);
        }else{
            session.setAttribute("is_active_list", true);
        }

        if(blockedList.isEmpty()){
            session.setAttribute("is_blocked_list", false);
        }else{
            session.setAttribute("is_blocked_list", true);
        }
        session.setAttribute("active_list", activeList);
        session.setAttribute("blocked_list", blockedList);
    }


}

