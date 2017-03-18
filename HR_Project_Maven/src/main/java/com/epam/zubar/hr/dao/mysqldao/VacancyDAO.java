package com.epam.zubar.hr.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.zubar.hr.dao.AbstractDAO;
import com.epam.zubar.hr.entity.Vacancy;
import com.epam.zubar.hr.exception.HRProjectDAOException;

/**
 * Contains methods allowing to extract information about Vacancies,
 * add new and update existent Vacancies.
 * @author Mikalay Zubar
 *
 */
public class VacancyDAO extends AbstractDAO<Vacancy>{

    private static final String FIND_ALL_VACANCIES = "SELECT * FROM vacancy";
    private static final String INSERT_VACANCY = "INSERT INTO vacancy ("
            + "vacancy_date, vacancy_name, vacancy_info, min_salary, max_salary, "
            + "vacancy_status, recruiter_id) values(?,?,?,?,?,?,?)";
    private static final String UPDATE_VACANCY = "UPDATE vacancy SET "
            + "vacancy_date=?, vacancy_name=?, vacancy_info=?, min_salary=?, "
            + "max_salary=?, vacancy_status=?, recruiter_id=? WHERE vacancy_id=?";
    private static final String SELECT_VACANCY_BY_NAME = "SELECT * FROM vacancy WHERE vacancy_name=?";
    private static final String FIND_VACANCY_BY_ID = "SELECT * FROM vacancy WHERE vacancy_id=?";
    private static final String FIND_VACANCY_BY_RECRUTER_ID = "SELECT * FROM vacancy WHERE recruiter_id=?";
    private static final String CLOSE_VACANCY = "UPDATE vacancy SET vacancy_status=? WHERE vacancy_id=?";
    private static final String STATUS = "closed";
    private static final String FIND_OPEN_VACANCY_BY_RECRUTER_ID = "SELECT * FROM vacancy WHERE recruiter_id=? AND vacancy_status='open'";
    public VacancyDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Vacancy> findAll() throws HRProjectDAOException {
        PreparedStatement ps = null;
        ArrayList<Vacancy> vacancies = new ArrayList<>();
        try{
            ps = connection.prepareStatement(FIND_ALL_VACANCIES);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                vacancies.add(new Vacancy(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getDouble(5), rs.getDouble(6), rs.getString(7),
                        rs.getInt(8)));
            }
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error", e);
        }finally{
            close(ps);
        }
        return vacancies;
    }



    @Override
    public boolean insert(Vacancy entity) throws HRProjectDAOException {
        PreparedStatement ps = null;
        boolean isInserted = false;
        try{
            ps = connection.prepareStatement(INSERT_VACANCY);
            ps.setString(1, entity.getDate());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getInfo());
            ps.setDouble(4, entity.getMinSalary());
            ps.setDouble(5, entity.getMaxSalary());
            ps.setString(6, entity.getStatus());
            ps.setInt(7, entity.getRecruterId());
            ps.executeUpdate();
            isInserted = true;
        }catch(SQLException e){
            throw new HRProjectDAOException("Database error", e);
        }finally{
            close(ps);
        }
        return isInserted;
    }

    @Override
    public boolean update(Vacancy entity, Object id) throws HRProjectDAOException {
        PreparedStatement ps = null;
        boolean isUpdated = false;
        try{
            ps = connection.prepareStatement(UPDATE_VACANCY);
            ps.setString(1, entity.getDate());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getInfo());
            ps.setDouble(4, entity.getMinSalary());
            ps.setDouble(5, entity.getMaxSalary());
            ps.setString(6, entity.getStatus());
            ps.setInt(7, entity.getRecruterId());
            ps.setInt(8, (int)id);
            ps.executeUpdate();
            isUpdated = true;
        }catch(SQLException e){
            throw new HRProjectDAOException("Database error", e);
        }finally{
            close(ps);
        }
        return isUpdated;
    }



    public List<Vacancy> selectVacancyByName(String name) throws HRProjectDAOException{
        List<Vacancy> vacancies = new ArrayList<>();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(SELECT_VACANCY_BY_NAME);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            vacancies.add( new Vacancy(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getDouble(5), rs.getDouble(6), rs.getString(7),
                    rs.getInt(8)));
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error!", e);
        }finally{
            close(ps);
        }
        return vacancies;
    }

    public Vacancy selectVacancyById(int id) throws HRProjectDAOException{
        Vacancy vacancy = null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(FIND_VACANCY_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            vacancy = new Vacancy(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getDouble(5), rs.getDouble(6), rs.getString(7),
                    rs.getInt(8));
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error!", e);
        }finally{
            close(ps);
        }
        return vacancy;
    }

    public List<Vacancy> selectVacancyByRecruterId(int id) throws HRProjectDAOException{
        List<Vacancy> vacancies = new ArrayList<>();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(FIND_VACANCY_BY_RECRUTER_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                vacancies.add(0, new Vacancy(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getDouble(5), rs.getDouble(6), rs.getString(7),
                        rs.getInt(8)));
            }
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error!", e);
        }finally{
            close(ps);
        }
        return vacancies;
    }

    public List<Vacancy> selectOpenVacancyByRecruterId(int id) throws HRProjectDAOException{
        List<Vacancy> vacancies = new ArrayList<>();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(FIND_OPEN_VACANCY_BY_RECRUTER_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                vacancies.add(0, new Vacancy(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getDouble(5), rs.getDouble(6), rs.getString(7),
                        rs.getInt(8)));
            }
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error!", e);
        }finally{
            close(ps);
        }
        return vacancies;
    }


    @Override
    public boolean delete(Vacancy entity) throws HRProjectDAOException {
        boolean isDeleted = false;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(CLOSE_VACANCY);
            ps.setString(1, STATUS);
            ps.setInt(2, entity.getId());
            ps.executeUpdate();
            isDeleted = true;
        }catch (SQLException e){
            throw new HRProjectDAOException("Database error!", e);
        }finally{
            close(ps);
        }
        return isDeleted;
    }

}
