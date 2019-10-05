package dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    //Table
    void createTable();
    void deleteTable();

    //Entity
    void add(T t) throws SQLException;
    List<T> getAll() throws SQLException;
    T getById(Long id) throws SQLException;
    void update(T order) throws SQLException;
    void remove(T order) throws SQLException;

}
