package dao;

import models.License_category;
import java.sql.SQLException;
import java.util.List;

public interface License_categoryDao extends Dao<License_category> {

    //Table
    void createTable();
    void deleteTable();

    //Entity
    void add(License_category category) throws SQLException;
    List<License_category> getAll() throws SQLException;
    License_category getById(Integer id) throws SQLException;
    void update(License_category category) throws SQLException;
    void remove(License_category category) throws SQLException;

}

