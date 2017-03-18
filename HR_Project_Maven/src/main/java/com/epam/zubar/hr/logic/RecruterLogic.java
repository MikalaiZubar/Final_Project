package com.epam.zubar.hr.logic;

    import java.sql.Connection;
    import java.util.ArrayList;
    import java.util.List;

    import com.epam.zubar.hr.dao.AbstractDAO;
    import com.epam.zubar.hr.dao.daofactory.AbstractDAOFactory;
    import com.epam.zubar.hr.dao.daofactory.FactoryType;
    import com.epam.zubar.hr.dao.mysqldao.RecruterDAO;
    import com.epam.zubar.hr.db.ConnectionPool;
    import com.epam.zubar.hr.entity.Recruter;
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
public class RecruterLogic {


    private ConnectionPool pool;
    private Connection connection;
    private AbstractDAOFactory factory;

    public RecruterLogic(){
        pool = ConnectionPool.getPool();
    }

    public List<Recruter> getRecrutersList() throws HRProjectLogicException{
        List<Recruter> recruters = new ArrayList<>();
        AbstractDAO<Recruter> dao = initDAOFactory().getRecruterDAO();
        try {
            recruters = dao.findAll();
        } catch (HRProjectDAOException e) {
            throw new HRProjectLogicException(
                    "Error. Unable to retrieve the list of Users!", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return recruters;
    }

    public boolean updateRecruter(Recruter recruter, int id) throws HRProjectLogicException{
        boolean isUpdated = false;
        AbstractDAO<Recruter> dao = initDAOFactory().getRecruterDAO();
        try{
            isUpdated = dao.update(recruter, id);
        }catch(HRProjectDAOException e) {
            throw new HRProjectLogicException("Error. Unable to update Recruter's data!", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return isUpdated;
    }

    public boolean addNewRecruter(Recruter recruter) throws HRProjectLogicException{
        boolean isAdded = false;
        UserLogic ul = new UserLogic();
        User user = ul.findUserById(recruter.getId());
        if(user == null){
            return isAdded;
        }
        AbstractDAO<Recruter> dao = initDAOFactory().getRecruterDAO();
        try{
            isAdded = dao.insert(recruter);

        }catch(HRProjectDAOException e) {
            throw new HRProjectLogicException("Error. Unable to add new Recruter!", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return isAdded;
    }

    public Recruter findRecruterById(int id) throws HRProjectLogicException{
        Recruter recruter = null;
        RecruterDAO dao = (RecruterDAO) initDAOFactory().getRecruterDAO();
        try{
            recruter = dao.findRecruterById(id);
        }catch (HRProjectDAOException e){
            throw new HRProjectLogicException("Error. Unable to retrieve recruter.", e);
        }finally{
            pool.releaseConnection(connection);
        }
        return recruter;
    }

    // initializes connection and DAO factory
    private AbstractDAOFactory initDAOFactory() {
        connection = pool.getConnection();
        factory = AbstractDAOFactory.getDAOFactory(connection, FactoryType.MYSQL);
        return factory;
    }
}
