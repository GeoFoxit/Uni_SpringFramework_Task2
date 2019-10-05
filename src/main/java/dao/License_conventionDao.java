package dao;

import models.License_convention;
import java.sql.SQLException;
import java.util.List;

public interface License_conventionDao extends Dao<License_convention> {

    //Table
    void createTable();
    void deleteTable();

    //Entity
    void add(License_convention convention) throws SQLException;
    List<License_convention> getAll() throws SQLException;
    License_convention getById(Integer id) throws SQLException;
    void update(License_convention convention) throws SQLException;
    void remove(License_convention convention) throws SQLException;

}