package com.epam.zubar.hr.dao.mysqldao;


import com.epam.zubar.hr.dao.AbstractDAO;
import com.epam.zubar.hr.entity.Candidate;
import com.epam.zubar.hr.exception.HRProjectDAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 * contains methods allowing to extract information about Candidates,
 * add new and update existent Candidates.
 * @author Mikalay Zubar
 *
 */
public class CandidateDAO extends AbstractDAO<Candidate> {

    private static final String FIND_ALL_CANDIDATES = "SELECT * FROM candidate";
    private static final String INSERT_CANDIDATE = "INSERT INTO candidate (users_user_id, cadidate_firstname, candidate_lastname,"
            + "candidate_email, candidate_sex, candidate_dateofbirth, candidate_phone, candidate_info, candidate_photo)"
            + "values(?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_CANDIDATE = "UPDATE candidate SET cadidate_firstname=?, candidate_lastname=?, "
            + "candidate_email=?, candidate_sex=?, candidate_ dateofbirth=?, candidate_ phone=?, "
            + "candidate_info=?, candidate_photo=? WHERE users_user_id=?";
    private static final String SELECT_CANDIDATE_BY_ID = "SELECT * FROM candidate WHERE users_user_id=?";

    public CandidateDAO(Connection connection) {
        super(connection);

    }

    @Override
    public List<Candidate> findAll() throws HRProjectDAOException {
        List<Candidate> candidates = new ArrayList<>();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(FIND_ALL_CANDIDATES);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                candidates.add(new Candidate(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9)));
            }
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error", e);
        }finally{
            close(ps);
        }
        return candidates;
    }


    @Override
    public boolean insert(Candidate entity) throws HRProjectDAOException {
        PreparedStatement ps = null;
        boolean isInserted = false;
        try{
            ps = connection.prepareStatement(INSERT_CANDIDATE);
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getFirstName());
            ps.setString(3, entity.getLastName());
            ps.setString(4, entity.getEmail());
            ps.setString(5, entity.getSex());
            ps.setString(6, entity.getBirthDate());
            ps.setString(7, entity.getPhone());
            ps.setString(8, entity.getInfo());
            ps.setString(9, entity.getPhoto());
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
    public boolean update(Candidate entity, Object id) throws HRProjectDAOException {
        PreparedStatement ps = null;
        boolean isUpdated = false;
        try{
            ps = connection.prepareStatement(UPDATE_CANDIDATE);
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getSex());
            ps.setString(5, entity.getBirthDate());
            ps.setString(6, entity.getPhone());
            ps.setString(7, entity.getInfo());
            ps.setString(8, entity.getPhoto());
            ps.setInt(9, (int) id);
            ps.executeUpdate();
            isUpdated = true;
        }catch(SQLException e){
            throw new HRProjectDAOException("Database error", e);
        }finally{
            close(ps);
        }
        return isUpdated;
    }

    public Candidate findCandidateById(int id) throws HRProjectDAOException {
        Candidate candidate = null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(SELECT_CANDIDATE_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                candidate = new Candidate(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9));
            }
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error", e);
        }finally{
            close(ps);
        }
        return candidate;
    }

    @Override
    public boolean delete(Candidate entity) throws HRProjectDAOException {
        // TODO Auto-generated method stub
        return false;
    }

}