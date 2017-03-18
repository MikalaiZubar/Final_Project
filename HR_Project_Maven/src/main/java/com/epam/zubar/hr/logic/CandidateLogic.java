package com.epam.zubar.hr.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.epam.zubar.hr.dao.AbstractDAO;
import com.epam.zubar.hr.dao.daofactory.AbstractDAOFactory;
import com.epam.zubar.hr.dao.daofactory.FactoryType;
import com.epam.zubar.hr.dao.mysqldao.CandidateDAO;
import com.epam.zubar.hr.db.ConnectionPool;
import com.epam.zubar.hr.entity.Candidate;
import com.epam.zubar.hr.entity.User;
import com.epam.zubar.hr.exception.HRProjectDAOException;
import com.epam.zubar.hr.exception.HRProjectLogicException;

/**
 * contains various methods that use DAO layer to
 * retrieve information  from a database, add or update
 * data, etc. These methods will be further used by Command layer.
 * @author Mikalay Zubar
 *
 */
public class CandidateLogic {

    private ConnectionPool pool;
    private Connection connection;
    private AbstractDAOFactory factory;

    public CandidateLogic(){
        pool = ConnectionPool.getPool();
    }

    public List<Candidate> getCandidatesList() throws HRProjectLogicException{
        List<Candidate> candidates = new ArrayList<>();
        AbstractDAO<Candidate> dao = initDAOFactory().getCandidateDAO();
        try {
            candidates = dao.findAll();
        } catch (HRProjectDAOException e) {
            throw new HRProjectLogicException(
                    "Error. Unable to retrieve the list of Candidates!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return candidates;
    }

    public Candidate findCandidateByLogin(String login) throws HRProjectLogicException{
        UserLogic ul = new UserLogic();
        User user = ul.findUserByLogin(login);
        Candidate candidate = null;
        CandidateDAO dao = (CandidateDAO) initDAOFactory().getCandidateDAO();
        try{
            candidate = dao.findCandidateById(user.getId());
        }catch (HRProjectDAOException e) {
            throw new HRProjectLogicException(
                    "Error. Unable to retrieve a Candidate!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return candidate;
    }

    public Candidate findCandidateById(int id) throws HRProjectLogicException{
        Candidate candidate = null;
        CandidateDAO dao = (CandidateDAO) initDAOFactory().getCandidateDAO();
        try{
            candidate = dao.findCandidateById(id);
        }catch (HRProjectDAOException e) {
            throw new HRProjectLogicException(
                    "Error. Unable to retrieve a Candidate!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return candidate;
    }

    public boolean updateCandidate(Candidate candidate, int id) throws HRProjectLogicException{
        boolean isUpdated = false;
        AbstractDAO<Candidate> dao = initDAOFactory().getCandidateDAO();
        try{
            isUpdated = dao.update(candidate, id);
        }catch(HRProjectDAOException e) {
            throw new HRProjectLogicException("Error. Unable to update Candidates's data!", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return isUpdated;
    }

    public boolean addNewCandidate(Candidate candidate) throws HRProjectLogicException{
        boolean isAdded = false;
        UserLogic ul = new UserLogic();
        User user = ul.findUserById(candidate.getId());
        if(user == null){
            return isAdded;
        }
        AbstractDAO<Candidate> dao = initDAOFactory().getCandidateDAO();
        try{
            isAdded = dao.insert(candidate);

        }catch(HRProjectDAOException e) {
            throw new HRProjectLogicException("Error. Unable to add new Candidate!", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return isAdded;
    }



    // initializes connection and DAO factory
    private AbstractDAOFactory initDAOFactory() {
        connection = pool.getConnection();
        factory = AbstractDAOFactory.getDAOFactory(connection, FactoryType.MYSQL);
        return factory;
    }
}
