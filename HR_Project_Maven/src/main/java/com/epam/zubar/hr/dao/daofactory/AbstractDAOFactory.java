package com.epam.zubar.hr.dao.daofactory;

import java.sql.Connection;

import com.epam.zubar.hr.dao.AbstractDAO;
import com.epam.zubar.hr.entity.Candidate;
import com.epam.zubar.hr.entity.Interview;
import com.epam.zubar.hr.entity.Recruter;
import com.epam.zubar.hr.entity.User;
import com.epam.zubar.hr.entity.VacCandConnector;
import com.epam.zubar.hr.entity.Vacancy;

/**
 * Abstract class contains abstract methods that should create DAO entities
 * (such as MemberDAO, IssueDAO, ProjectDAO, BuildDAO, etc.).
 * Specific implementations (i.e. specific variants of DAO
 * objects that allow to work with a given database) are
 * presented by the sub-classes. The class also contains method that returns
 * different types of DAOFactory sub-classes.
 * @author Mikalay Zubar
 *
 */
public abstract class AbstractDAOFactory {

    /*
     * Returns the instance of DAOFactory basing on a type of database
     * used in the application.
     */
    public static AbstractDAOFactory getDAOFactory(Connection connection, FactoryType factoryType){
        switch(factoryType){
            case MYSQL:
                return new MySQLDAOFactory(connection);
            default:
                throw new EnumConstantNotPresentException(FactoryType.class, factoryType.name());
        }
    }


    /*
     * Each method returns the DAO instance. The specific implementation of DAO
     * class depends on the type of DAOFactory
     */
    public abstract AbstractDAO<User> getUserDAO();

    public abstract AbstractDAO<Candidate> getCandidateDAO();

    public abstract AbstractDAO<Recruter> getRecruterDAO();

    public abstract AbstractDAO<Vacancy> getVacancyDAO();

    public abstract AbstractDAO<Interview> getInterviewDAO();

    public abstract AbstractDAO<VacCandConnector> getVacCandConnectorDAO();
}