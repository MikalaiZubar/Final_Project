package com.epam.zubar.hr.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.zubar.hr.dao.AbstractDAO;
import com.epam.zubar.hr.entity.User;
import com.epam.zubar.hr.exception.HRProjectDAOException;
import com.epam.zubar.hr.entity.VacCandConnector;

/**
 * Contains methods allowing to extract information about Candidates - Vacancies,
 * relations add new and update existent relations.
 * @author Mikalay Zubar
 *
 */
public class VacCandConnectorDAO extends AbstractDAO<VacCandConnector>{

    private static final String FIND_NEW_VAC_BY_CAND_ID = "SELECT vacancy.vacancy_id, data_of_sign, vacancy_name, "
            + "result from vacancy, vac_m2m_cand, candidate where candidate.users_user_id=? "
            + "and vac_m2m_cand.candidate_id=? and vac_m2m_cand.vacancy_id=vacancy.vacancy_id "
            + "and vac_m2m_cand.result not like 'refused'";
    private static final String FIND_ALL_VAC_BY_CAND_ID = "SELECT vacancy.vacancy_id, data_of_sign, vacancy_name, "
            + "result from vacancy, vac_m2m_cand, candidate where candidate.users_user_id=? "
            + "and vac_m2m_cand.candidate_id=? and vac_m2m_cand.vacancy_id=vacancy.vacancy_id";
    private static final String FIND_ALL_CAND_BY_VAC_ID = "SELECT vacancy.vacancy_id, vacancy_name, users_user_id, "
            + "candidate_lastname, result from vacancy, vac_m2m_cand, candidate "
            + "where vacancy.vacancy_id=? and vac_m2m_cand.vacancy_id=? "
            + "and vac_m2m_cand.candidate_id=candidate.users_user_id ";
    private static final String ADD_NEW_VCC = "INSERT INTO vac_m2m_cand (vacancy_id, candidate_id, "
            + "data_of_sign, result) values (?,?,?,?)";
    private static final String UPDATE_VCC = "UPDATE vac_m2m_cand SET data_of_sign=?, result=? WHERE candidate_id=? "
            + "AND vacancy_id=?";
    private static final String FIND_VCC_BY_USER_VAC = "SELECT * from vac_m2m_cand where vacancy_id=? "
            + "AND candidate_id = ?";

    public VacCandConnectorDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<VacCandConnector> findAll() throws HRProjectDAOException {
        return null;
    }

    @Override
    public boolean delete(VacCandConnector entity) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean insert(VacCandConnector entity) throws HRProjectDAOException {
        boolean isInserted = false;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(ADD_NEW_VCC);
            ps.setInt(1, entity.getVacancyId());
            ps.setInt(2, entity.getCandidateId());
            ps.setString(3, entity.getVacDateOfSign());
            ps.setString(4, entity.getResult());
            ps.executeUpdate();
            isInserted = true;
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error!", e);
        }finally{
            close(ps);
        }
        return isInserted;
    }

    @Override
    public boolean update(VacCandConnector entity, Object id) throws HRProjectDAOException {
        boolean isUpdated = false;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(UPDATE_VCC);
            ps.setString(1, entity.getVacDateOfSign());
            ps.setString(2, entity.getResult());
            ps.setInt(3, (int) id); // candidat's id
            ps.setInt(4, entity.getVacancyId());
            ps.executeUpdate();
            isUpdated = true;
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error!", e);
        }finally{
            close(ps);
        }
        return isUpdated;
    }

    public List<VacCandConnector> findNewVacByCandId(int id) throws HRProjectDAOException{
        List<VacCandConnector> vccList = new ArrayList<>();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(FIND_NEW_VAC_BY_CAND_ID);
            ps.setInt(1, id);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                vccList.add(new VacCandConnector( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error!", e);
        }finally{
            close(ps);
        }
        return vccList;
    }

    public List<VacCandConnector> findAllVacByCandId(int id) throws HRProjectDAOException{
        List<VacCandConnector> vccList = new ArrayList<>();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(FIND_ALL_VAC_BY_CAND_ID);
            ps.setInt(1, id);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                vccList.add(new VacCandConnector(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error!", e);
        }finally{
            close(ps);
        }
        return vccList;
    }

    public List<VacCandConnector> findAllCandByVacId(int id) throws HRProjectDAOException{
        List<VacCandConnector> vccList = new ArrayList<>();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(FIND_ALL_CAND_BY_VAC_ID);
            ps.setInt(1, id);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                vccList.add(new VacCandConnector(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
            }
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error!", e);
        }finally{
            close(ps);
        }
        return vccList;
    }

    public VacCandConnector findVCCByUserAndVacId(User user, int id) throws HRProjectDAOException{
        VacCandConnector vcc = null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(FIND_VCC_BY_USER_VAC);
            ps.setInt(1, id);
            ps.setInt(2, user.getId());
            ResultSet rs = ps.executeQuery();
            rs.next();
            vcc = new VacCandConnector(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error!", e);
        }finally{
            close(ps);
        }
        return vcc;
    }

}
