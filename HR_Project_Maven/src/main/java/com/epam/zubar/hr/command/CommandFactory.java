package com.epam.zubar.hr.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.epam.zubar.hr.command.admin.ActivateCommand;
import com.epam.zubar.hr.command.admin.AddRecruiterCommand;
import com.epam.zubar.hr.command.admin.DeleteRecruiterCommand;
import com.epam.zubar.hr.command.admin.DisplayRecruiterCommand;
import com.epam.zubar.hr.command.admin.DisplayVacancyAdminCommand;
import com.epam.zubar.hr.command.admin.RedirectVacToNewRecCommand;
import com.epam.zubar.hr.command.candidate.AddCandidateCommand;
import com.epam.zubar.hr.command.candidate.AssignVacancyCommand;
import com.epam.zubar.hr.command.candidate.DisclaimVacancyCommand;
import com.epam.zubar.hr.command.candidate.DisplayInterviewCommand;
import com.epam.zubar.hr.command.candidate.DisplayVacancyCommand;
import com.epam.zubar.hr.command.candidate.ReAssingVacancy;
import com.epam.zubar.hr.command.recruiter.AddNewVacancy;
import com.epam.zubar.hr.command.recruiter.AssignInterviewForCandCommand;
import com.epam.zubar.hr.command.recruiter.AssignNewInterviewCommand;
import com.epam.zubar.hr.command.recruiter.CloseVacancyCommand;
import com.epam.zubar.hr.command.recruiter.DisplayVacancyForRecCommand;
import com.epam.zubar.hr.command.recruiter.ShowInterviewCommand;
import com.epam.zubar.hr.command.recruiter.UpdateInterviewCommand;

/**
 * Returns a proper ICommand class depending on the
 * value of "action" request parameter. The factory is constructed using
 * Singleton design pattern (allows to create only 1 instance of factory).
 * @author Mikalay Zubar
 *
 */
public class CommandFactory {

    private Map<String, ICommand> commandMap = new HashMap<>();

    private CommandFactory(){
        commandMap.put("changelang", new ChangeLanguageCommand());
        commandMap.put("addCand", new AddCandidateCommand());
        commandMap.put("authorization", new AuthorizationCommand());
        commandMap.put("showVacancy", new DisplayVacancyCommand());
        commandMap.put("assignVac", new AssignVacancyCommand());
        commandMap.put("disclaimVac", new DisclaimVacancyCommand());
        commandMap.put("reAssignVac", new ReAssingVacancy());
        commandMap.put("showInterview", new DisplayInterviewCommand());
        commandMap.put("showRecVacancy", new DisplayVacancyForRecCommand());
        commandMap.put("addNewVacancy", new AddNewVacancy());
        commandMap.put("assignAnInterview", new AssignNewInterviewCommand());
        commandMap.put("addNewInterview", new AssignInterviewForCandCommand());
        commandMap.put("showRecInterview", new ShowInterviewCommand());
        commandMap.put("closeVacancy", new CloseVacancyCommand());
        commandMap.put("updateInterview", new UpdateInterviewCommand());
        commandMap.put("addRecruiter", new AddRecruiterCommand());
        commandMap.put("showRecruiter", new DisplayRecruiterCommand());
        commandMap.put("showVacancyAdmin", new DisplayVacancyAdminCommand());
        commandMap.put("redirectVacancy", new RedirectVacToNewRecCommand());
        commandMap.put("goFirstPage", new GoFirstPageCommand());
        commandMap.put("logOut", new LogOutCommand());
        commandMap.put("deleteRecruiter", new DeleteRecruiterCommand());
        commandMap.put("activateRecruiter", new ActivateCommand());
    }

    /**
     * Private class that contains static field where CommandFactory object is
     * created. Such schema prevents creation of 2 or more CommandFactory
     * objects in a multithread environment
     * (Singleton design pattern)
     */
    private static class CommandFactoryHolder {
        private static final CommandFactory commandFactory = new CommandFactory();
    }

    /**
     * Returns the only instance of CommandFactory
     */
    public static CommandFactory getInstance() {
        return CommandFactoryHolder.commandFactory;
    }

    /**
     * Returns a proper ICommand class depending on the value of "action"
     * request parameter.
     */
    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = null;
        String action = request.getParameter("action");
        command = commandMap.get(action);
        if (command == null) {
            command = new EmptyCommand();
        }
        return command;
    }
}

