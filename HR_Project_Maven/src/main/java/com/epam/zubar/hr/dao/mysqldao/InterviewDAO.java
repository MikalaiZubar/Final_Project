package com.epam.zubar.hr.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.zubar.hr.dao.AbstractDAO;
import com.epam.zubar.hr.entity.Interview;
import com.epam.zubar.hr.exception.HRProjectDAOException;

/**
 * Contains methods allowing to extract information about Interviews,
 * add new and update existent Interviews.
 * @author Mikalay Zubar
 *
 */
public class InterviewDAO extends AbstractDAO<Interview> {

    private static final String FIND_ALL_INTERVIEWS = "SELECT * FROM interview";
    private static final String ADD_NEW_INTERVIEW = "INSERT INTO interview (interview_Date, "
            + "candidate_id, recruiter_id, interview_status, interview_number, interview_comment, "
            + "vacancy_id) values(?,?,?,?,?,?,?)";
    private static final String UPDATE_INTERVIEW = "UPDATE interview SET interview_Date=?, candidate_id=?, "
            + "recruiter_id=?, interview_status=?, interview_number=?, interview_comment=?, "
            + "vacancy_id=? WHERE interview_Date=?";
    private static final String SELECT_INTERVIEW_BY_DATE = "SELECT * FROM interview WHERE interview_Date=?";
    private static final String FIND_INTERVIEW_BY_REC_ID = "SELECT * FROM interview WHERE recruiter_id=?";
    private static final String FIND_INTERVIEW_BY_VAC_ID = "SELECT * FROM interview WHERE vacancy_id=?";

    public InterviewDAO(Connection connection) {
        super(connection);
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<Interview> findAll() throws HRProjectDAOException {
        ArrayList<Interview> interviews = new ArrayList<>();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(FIND_ALL_INTERVIEWS);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                interviews.add(new Interview(rs.getString(1), rs.getInt(2),
                        rs.getInt(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7)));
            }
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error", e);
        }finally {
            close(ps);
        }
        return interviews;
    }


    @Override
    public boolean insert(Interview entity) throws HRProjectDAOException{
        boolean isInserted = false;
        PreparedStatement ps = null;
        try{
            ps=connection.prepareStatement(ADD_NEW_INTERVIEW);
            ps.setString(1, entity.getDate());
            ps.setInt(2, entity.getCandidateId());
            ps.setInt(3, entity.getRecruterId());
            ps.setString(4, entity.getStatus());
            ps.setString(5, entity.getNumber());
            ps.setString(6, entity.getComment());
            ps.setInt(7, entity.getVacancyId());
            ps.executeUpdate();
            isInserted = true;
        }catch(SQLException e){
            throw new HRProjectDAOException("Database error!", e);
        }finally{
            close(ps);
        }
        return isInserted;
    }

    @Override
    public boolean update(Interview entity, Object id) throws HRProjectDAOException {
        boolean isUpdated = false;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(UPDATE_INTERVIEW);
            ps.setString(1, entity.getDate());
            ps.setInt(2, entity.getCandidateId());
            ps.setInt(3, entity.getRecruterId());
            ps.setString(4, entity.getStatus());
            ps.setString(5, entity.getNumber());
            ps.setString(6, entity.getComment());
            ps.setInt(7, entity.getVacancyId());
            ps.setString(8, (String)id);
            ps.executeUpdate();
            isUpdated = true;
        }catch(SQLException e){
            throw new HRProjectDAOException("Database error!", e);
        }finally{
            close(ps);
        }
        return isUpdated;
    }

    public Interview findInterviewById(String id) throws HRProjectDAOException{
        Interview interview = null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(SELECT_INTERVIEW_BY_DATE);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            interview = new Interview(rs.getString(1), rs.getInt(2), rs.getInt(3),
                    rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
        }catch(SQLException e){
            throw new HRProjectDAOException("Database error!", e);
        }finally{
            close(ps);
        }
        return interview;
    }

    public List<Interview> findInterviewsByRecId(int id) throws HRProjectDAOException{
        List<Interview> intList = new ArrayList<>();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(FIND_INTERVIEW_BY_REC_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                intList.add(new Interview(rs.getString(1), rs.getInt(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
            }
        }catch(SQLException e){
            throw new HRProjectDAOException("Database error!", e);
        }finally{
            close(ps);
        }
        return intList;
    }

    public List<Interview> findInterviewsByVacId(int id) throws HRProjectDAOException{
        List<Interview> intList = new ArrayList<>();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(FIND_INTERVIEW_BY_VAC_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                intList.add(new Interview(rs.getString(1), rs.getInt(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
            }
        }catch(SQLException e){
            throw new HRProjectDAOException("Database error!", e);
        }finally{
            close(ps);
        }
        return intList;
    }

    @Override
    public boolean delete(Interview entity) throws HRProjectDAOException {
        // TODO Auto-generated method stub
        return false;
    }

}
