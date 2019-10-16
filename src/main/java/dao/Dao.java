package dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<Entity,Key> {

    void createTable() throws SQLException;
    void deleteTable() throws SQLException;

    List<Entity> getAll() throws SQLException;
    Entity getById(Key key) throws SQLException;

    void add(Entity entity) throws SQLException;
    void update(Entity entity) throws SQLException;
    void remove(Entity entity) throws SQLException;

}
