package com.epam.zubar.hr.logic;

    import java.sql.Connection;
    import java.util.ArrayList;
    import java.util.List;

    import com.epam.zubar.hr.dao.AbstractDAO;
    import com.epam.zubar.hr.dao.daofactory.AbstractDAOFactory;
    import com.epam.zubar.hr.dao.daofactory.FactoryType;
    import com.epam.zubar.hr.dao.mysqldao.InterviewDAO;
    import com.epam.zubar.hr.db.ConnectionPool;
    import com.epam.zubar.hr.entity.Interview;
    import com.epam.zubar.hr.exception.HRProjectDAOException;
    import com.epam.zubar.hr.exception.HRProjectLogicException;
/**
 * contains various methods that use DAO layer to
 * retrieve information  from a database, add or update
 * data, etc. These methods will be further used by Command layer.
 * @author Mikalay Zubar
 *
 */
public class InterviewLogic {

    private ConnectionPool pool;
    private Connection connection;
    private AbstractDAOFactory factory;

    public InterviewLogic(){
        pool = ConnectionPool.getPool();
    }

    public List<Interview> getInterviewesList() throws HRProjectLogicException{
        List<Interview> interviewes = new ArrayList<>();
        AbstractDAO<Interview> dao = initDAOFactory().getInterviewDAO();
        try {
            interviewes = dao.findAll();
        } catch (HRProjectDAOException e) {
            throw new HRProjectLogicException(
                    "Error. Unable to retrieve the list of Interviewes!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return interviewes;
    }

    public Interview findInterviewById(String id) throws HRProjectLogicException{
        Interview interview = null;
        InterviewDAO dao = (InterviewDAO) initDAOFactory().getInterviewDAO();
        try{
            interview = dao.findInterviewById(id);
        }catch (HRProjectDAOException e) {
            throw new HRProjectLogicException(
                    "Error. Unable to retrieve an Interview!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return interview;
    }

    public boolean updateInterview(Interview interview, String id) throws HRProjectLogicException{
        boolean isUpdated = false;
        InterviewDAO dao = (InterviewDAO) initDAOFactory().getInterviewDAO();
        try{
            isUpdated = dao.update(interview, id);
        }catch (HRProjectDAOException e) {
            throw new HRProjectLogicException(
                    "Error. Unable to update an Interview!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return isUpdated;
    }

    public boolean addNewInterview(Interview interview) throws HRProjectLogicException{
        boolean isAdded = false;
        InterviewDAO dao = (InterviewDAO) initDAOFactory().getInterviewDAO();
        try{
            isAdded = dao.insert(interview);
        }catch (HRProjectDAOException e) {
            throw new HRProjectLogicException(
                    "Error. Unable to add an Interview!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return isAdded;
    }

    public List<Interview> findInterviewsByRecId(int id) throws HRProjectLogicException{
        List<Interview> interviews = new ArrayList<>();
        InterviewDAO dao = (InterviewDAO) initDAOFactory().getInterviewDAO();
        try{
            interviews = dao.findInterviewsByRecId(id);
        }catch (HRProjectDAOException e) {
            throw new HRProjectLogicException(
                    "Error. Unable to retrieve an Interviews list!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return interviews;
    }

    public List<Interview> findInterviewsByVacId(int id) throws HRProjectLogicException{
        List<Interview> interviews = new ArrayList<>();
        InterviewDAO dao = (InterviewDAO) initDAOFactory().getInterviewDAO();
        try{
            interviews = dao.findInterviewsByVacId(id);
        }catch (HRProjectDAOException e) {
            throw new HRProjectLogicException(
                    "Error. Unable to retrieve an Interviews list!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return interviews;
    }

    // initializes connection and DAO factory
    private AbstractDAOFactory initDAOFactory() {
        connection = pool.getConnection();
        factory = AbstractDAOFactory.getDAOFactory(connection, FactoryType.MYSQL);
        return factory;
    }

}
