package com.epam.zubar.hr.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.epam.zubar.hr.dao.AbstractDAO;
import com.epam.zubar.hr.dao.daofactory.AbstractDAOFactory;
import com.epam.zubar.hr.dao.daofactory.FactoryType;
import com.epam.zubar.hr.dao.mysqldao.VacCandConnectorDAO;
import com.epam.zubar.hr.db.ConnectionPool;
import com.epam.zubar.hr.entity.User;
import com.epam.zubar.hr.entity.VacCandConnector;
import com.epam.zubar.hr.exception.HRProjectDAOException;
import com.epam.zubar.hr.exception.HRProjectLogicException;

/**
 * contains various methods that use DAO layer to
 * retrieve information  from a database, add or update
 * data, etc. These methods will be further used by Command layer.
 * @author Mikalay Zubar
 *
 */
public class VacCandConnectorLogic {

    private ConnectionPool pool;
    private Connection connection;
    private AbstractDAOFactory factory;

    public VacCandConnectorLogic(){
        pool = ConnectionPool.getPool();
    }

    public List<VacCandConnector> findAllVacByCandId(int id) throws HRProjectLogicException{
        List<VacCandConnector> vccList = new ArrayList<>();
        VacCandConnectorDAO dao = (VacCandConnectorDAO) initDAOFactory().getVacCandConnectorDAO();
        try{
            vccList = dao.findAllVacByCandId(id);
        }catch (HRProjectDAOException e){
            throw new HRProjectLogicException("Error. Unable to retrieve the list of Candidat's vacancies!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return vccList;
    }

    public List<VacCandConnector> findAllNewVacByCandId(int id) throws HRProjectLogicException{
        List<VacCandConnector> vccList = new ArrayList<>();
        VacCandConnectorDAO dao = (VacCandConnectorDAO) initDAOFactory().getVacCandConnectorDAO();
        try{
            vccList = dao.findNewVacByCandId(id);
        }catch (HRProjectDAOException e){
            throw new HRProjectLogicException("Error. Unable to retrieve the list of Candidat's vacancies!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return vccList;
    }

    public List<VacCandConnector> findAllCandByVacId(int id) throws HRProjectLogicException{
        List<VacCandConnector> vccList = new ArrayList<>();
        VacCandConnectorDAO dao = (VacCandConnectorDAO) initDAOFactory().getVacCandConnectorDAO();
        try{
            vccList = dao.findAllCandByVacId(id);
        }catch (HRProjectDAOException e){
            throw new HRProjectLogicException("Error. Unable to retrieve the list of Candidat's vacancies!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return vccList;
    }

    public boolean updateVCC(VacCandConnector vcc, int id) throws HRProjectLogicException{
        boolean isUpdated = false;
        AbstractDAO<VacCandConnector> dao = initDAOFactory().getVacCandConnectorDAO();
        try{
            isUpdated = dao.update(vcc, id);
        }catch (HRProjectDAOException e){
            throw new HRProjectLogicException("Error. Unable to update VCC.", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return isUpdated;
    }

    public boolean addNewVCC(VacCandConnector vcc) throws HRProjectLogicException{
        boolean isAdded = false;
        AbstractDAO<VacCandConnector> dao = initDAOFactory().getVacCandConnectorDAO();
        try{
            isAdded = dao.insert(vcc);
        }catch (HRProjectDAOException e){
            throw new HRProjectLogicException("Error. Unable to add new VCC.", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return isAdded;
    }

    public VacCandConnector findVCCByUserAndVac(User user, int id) throws HRProjectLogicException{
        VacCandConnector vcc = null;
        VacCandConnectorDAO dao = (VacCandConnectorDAO) initDAOFactory().getVacCandConnectorDAO();
        try{
            vcc = dao.findVCCByUserAndVacId(user, id);
        }catch (HRProjectDAOException e){
            throw new HRProjectLogicException("Error. Unable to find a VCC.", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return vcc;
    }


    // initializes connection and DAO factory
    private AbstractDAOFactory initDAOFactory() {
        connection = pool.getConnection();
        factory = AbstractDAOFactory.getDAOFactory(connection, FactoryType.MYSQL);
        return factory;
    }
}

