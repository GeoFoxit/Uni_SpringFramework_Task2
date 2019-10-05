package dao;

import models.Driver_license;
import java.sql.SQLException;
import java.util.List;

public interface Driver_licenseDao extends Dao<Driver_license> {

    //Table
    void createTable();
    void deleteTable();

    //Entity
    void add(Driver_license license) throws SQLException;
    List<Driver_license> getAll() throws SQLException;
    Driver_license getById(Integer id) throws SQLException;
    void update(Driver_license license) throws SQLException;
    void remove(Driver_license license) throws SQLException;

}

