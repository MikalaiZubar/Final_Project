package com.epam.zubar.hr.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.epam.zubar.hr.dao.AbstractDAO;
import com.epam.zubar.hr.dao.daofactory.AbstractDAOFactory;
import com.epam.zubar.hr.dao.daofactory.FactoryType;
import com.epam.zubar.hr.dao.mysqldao.VacancyDAO;
import com.epam.zubar.hr.db.ConnectionPool;
import com.epam.zubar.hr.entity.Vacancy;
import com.epam.zubar.hr.exception.HRProjectDAOException;
import com.epam.zubar.hr.exception.HRProjectLogicException;

/**
 * contains various methods that use DAO layer to
 * retrieve information  from a database, add or update
 * data, etc. These methods will be further used by Command layer.
 * @author Mikalay Zubar
 *
 */
public class VacancyLogic {
    private ConnectionPool pool;
    private Connection connection;
    private AbstractDAOFactory factory;

    public VacancyLogic(){
        pool = ConnectionPool.getPool();
    }

    public boolean deleteVacancy(Vacancy vacancy) throws HRProjectLogicException{
        boolean isDeleted = false;
        AbstractDAO<Vacancy> dao = initDAOFactory().getVacancyDAO();
        try{
            isDeleted = dao.delete(vacancy);
        } catch (HRProjectDAOException e) {
            throw new HRProjectLogicException("Error. Unable to delete Vacancy!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return isDeleted;
    }

    public List<Vacancy> getVacansiesList() throws HRProjectLogicException{
        List<Vacancy> vacancies = new ArrayList<>();
        AbstractDAO<Vacancy> dao = initDAOFactory().getVacancyDAO();
        try {
            vacancies = dao.findAll();
        } catch (HRProjectDAOException e) {
            throw new HRProjectLogicException(
                    "Error. Unable to retrieve the list of Vacancies!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return vacancies;
    }

    public boolean addNewVacancy(Vacancy vacancy) throws HRProjectLogicException{
        boolean isAdded = false;
        AbstractDAO<Vacancy> dao = initDAOFactory().getVacancyDAO();
        try{
            isAdded = dao.insert(vacancy);
        }catch(HRProjectDAOException e) {
            throw new HRProjectLogicException("Error. Unable to add new Vacancy!", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return isAdded;
    }

    public boolean updateVacancy(Vacancy vacancy, int id) throws HRProjectLogicException{
        boolean isUpdated = false;
        AbstractDAO<Vacancy> dao = initDAOFactory().getVacancyDAO();
        try{
            isUpdated = dao.update(vacancy, id);
        }catch(HRProjectDAOException e) {
            throw new HRProjectLogicException("Error. Unable to update the Vacancy!", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return isUpdated;
    }

    public List<Vacancy> findVacancyByName(String name) throws HRProjectLogicException{
        List<Vacancy> vacancies = new ArrayList<>();
        VacancyDAO dao = (VacancyDAO) initDAOFactory().getVacancyDAO();
        try{
            vacancies = dao.selectVacancyByName(name);
        }catch (HRProjectDAOException e) {
            throw new HRProjectLogicException(
                    "Error. Unable to retrieve the list of Vacancies - called: " + name, e);
        }finally{
            pool.releaseConnection(connection);
        }
        return vacancies;
    }

    public Vacancy findVacancyById(int id) throws HRProjectLogicException{
        Vacancy vacancy = null;
        VacancyDAO dao = (VacancyDAO) initDAOFactory().getVacancyDAO();
        try{
            vacancy = dao.selectVacancyById(id);
        }catch (HRProjectDAOException e) {
            throw new HRProjectLogicException(
                    "Error. Unable to retrieve the Vacancy", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return vacancy;
    }

    public List<Vacancy> findVacanciesByRecId(int id) throws HRProjectLogicException{
        List<Vacancy> vacList = new ArrayList<>();
        VacancyDAO dao = (VacancyDAO) initDAOFactory().getVacancyDAO();
        try{
            vacList = dao.selectVacancyByRecruterId(id);
        }catch (HRProjectDAOException e) {
            throw new HRProjectLogicException(
                    "Error. Unable to retrieve the Vacancies list", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return vacList;
    }

    public List<Vacancy> findOpenVacanciesByRecId(int id) throws HRProjectLogicException{
        List<Vacancy> vacList = new ArrayList<>();
        VacancyDAO dao = (VacancyDAO) initDAOFactory().getVacancyDAO();
        try{
            vacList = dao.selectOpenVacancyByRecruterId(id);
        }catch (HRProjectDAOException e) {
            throw new HRProjectLogicException(
                    "Error. Unable to retrieve the Vacancies list", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return vacList;
    }


    // initializes connection and DAO factory
    private AbstractDAOFactory initDAOFactory() {
        connection = pool.getConnection();
        factory = AbstractDAOFactory.getDAOFactory(connection, FactoryType.MYSQL);
        return factory;
    }
}

