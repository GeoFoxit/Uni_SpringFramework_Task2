package dao;

import models.Client;
import java.sql.SQLException;
import java.util.List;

public interface ClientDao extends Dao<Client> {

    //Table
    void createTable();
    void deleteTable();

    //Entity
    void add(Client client) throws SQLException;
    List<Client> getAll() throws SQLException;
    Client getById(Long id) throws SQLException;
    void update(Client client) throws SQLException;
    void remove(Client client) throws SQLException;

}
