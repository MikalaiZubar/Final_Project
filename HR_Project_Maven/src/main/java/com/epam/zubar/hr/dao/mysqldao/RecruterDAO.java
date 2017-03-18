package com.epam.zubar.hr.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.zubar.hr.dao.AbstractDAO;
import com.epam.zubar.hr.entity.Recruter;
import com.epam.zubar.hr.exception.HRProjectDAOException;

/**
 * Contains methods allowing to extract information about Recruiters,
 * add new and update existent Recruiters.
 * @author Mikalay Zubar
 *
 */
public class RecruterDAO extends AbstractDAO<Recruter> {

    private static final String FIND_ALL_RECRUTERS = "SELECT * FROM recruiter";
    private static final String INSERT_RECRUTER = "INSERT INTO recruiter (users_user_id, recruiter_name, "
            + "recruiter_phone, recruiter_email) values(?,?,?,?)";
    private static final String UPDATE_RECRUTER = "UPDATE recruiter SET recruiter_name=?, "
            + "recruiter_phone=?, recruiter_email=? WHERE users_user_id=?";
    private static final String FIND_RECRUTER_BY_ID = "SELECT * FROM recruiter WHERE users_user_id=?";

    public RecruterDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Recruter> findAll() throws HRProjectDAOException {
        List<Recruter> recruters = new ArrayList<>();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(FIND_ALL_RECRUTERS);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                recruters.add(0, new Recruter(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4)));
            }
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error", e);
        }finally{
            close(ps);
        }
        return recruters;
    }


    @Override
    public boolean insert(Recruter entity) throws HRProjectDAOException {
        PreparedStatement ps = null;
        boolean isInserted = false;
        try{
            ps = connection.prepareStatement(INSERT_RECRUTER);
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getPhone());
            ps.setString(4, entity.getEmail());
            ps.executeUpdate();
            isInserted = true;
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error", e);
        }finally{
            close(ps);
        }
        return isInserted;
    }

    @Override
    public boolean update(Recruter entity, Object id) throws HRProjectDAOException {
        PreparedStatement ps = null;
        boolean isUpdated = false;
        try{
            ps = connection.prepareStatement(UPDATE_RECRUTER);
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getPhone());
            ps.setString(4, entity.getEmail());
            ps.setInt(5, (int)id);
            ps.executeUpdate();
            isUpdated = true;
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error", e);
        }finally{
            close(ps);
        }
        return isUpdated;
    }

    public Recruter findRecruterById(int id) throws HRProjectDAOException{
        PreparedStatement ps = null;
        Recruter recruter = null;
        try{
            ps = connection.prepareStatement(FIND_RECRUTER_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            recruter = new Recruter(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error!", e);
        }finally{
            close(ps);
        }
        return recruter;
    }

    @Override
    public boolean delete(Recruter entity) throws HRProjectDAOException {
        // TODO Auto-generated method stub
        return false;
    }

}