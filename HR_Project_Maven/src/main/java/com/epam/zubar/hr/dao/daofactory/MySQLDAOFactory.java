package com.epam.zubar.hr.dao.daofactory;

import java.sql.Connection;

import com.epam.zubar.hr.dao.AbstractDAO;
import com.epam.zubar.hr.dao.mysqldao.CandidateDAO;
import com.epam.zubar.hr.dao.mysqldao.InterviewDAO;
import com.epam.zubar.hr.dao.mysqldao.RecruterDAO;
import com.epam.zubar.hr.dao.mysqldao.UserDAO;
import com.epam.zubar.hr.dao.mysqldao.VacCandConnectorDAO;
import com.epam.zubar.hr.dao.mysqldao.VacancyDAO;
import com.epam.zubar.hr.entity.Candidate;
import com.epam.zubar.hr.entity.Interview;
import com.epam.zubar.hr.entity.Recruter;
import com.epam.zubar.hr.entity.User;
import com.epam.zubar.hr.entity.VacCandConnector;
import com.epam.zubar.hr.entity.Vacancy;

/**
 * Class  contains methods that create MySQLDAO entities.
 * This is a specific implementation of AbstractDAOFactory that generates
 * classes that allow to work with local MySQL database.
 * @author Mikalay Zubar
 *
 */
public class MySQLDAOFactory extends AbstractDAOFactory{

    private Connection connection;

    public MySQLDAOFactory(Connection connectin){
        this.connection = connectin;
    }

    @Override
    public AbstractDAO<User> getUserDAO() {
        return new UserDAO(connection);
    }

    @Override
    public AbstractDAO<Candidate> getCandidateDAO() {
        return new CandidateDAO(connection);
    }

    @Override
    public AbstractDAO<Recruter> getRecruterDAO() {
        return new RecruterDAO(connection);
    }

    @Override
    public AbstractDAO<Vacancy> getVacancyDAO() {
        return new VacancyDAO(connection);
    }

    @Override
    public AbstractDAO<Interview> getInterviewDAO() {
        return new InterviewDAO(connection);
    }

    @Override
    public AbstractDAO<VacCandConnector> getVacCandConnectorDAO() {
        return new VacCandConnectorDAO(connection);
    }


}
