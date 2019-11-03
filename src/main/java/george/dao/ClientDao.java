package george.dao;

import george.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class ClientDao implements Dao<Client, Integer> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private String SQL_getTable = "SELECT * FROM clients";
    private String SQL_selectByIdFromTable = "SELECT * FROM clients WHERE id=?";
    private String SQL_insertToTable = "INSERT INTO  clients (id, name, phoneNumber) VALUES(?,?,?)";
    private String SQL_updateInTable = "UPDATE clients SET name=?, phoneNumber=? WHERE id=?";
    private String SQL_removeFromTable = "DELETE FROM clients WHERE id=?";

    private static Client mapRow(ResultSet resultSet, int iterator) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getInt("id"));
        client.setName(resultSet.getString("name"));
        client.setPhoneNumber(resultSet.getString("phoneNumber"));
        return client;
    }

    @Override
    public List<Client> getAll() {
        return jdbcTemplate.query(SQL_getTable, ClientDao::mapRow);
    }

    @Override
    public Client getEntityById(Integer id) {
        return (Client)jdbcTemplate.queryForObject(SQL_selectByIdFromTable, new Object[]{id}, ClientDao::mapRow);
    }

    @Override
    public void update(Client entity) {
        jdbcTemplate.update(SQL_updateInTable, entity.getName(), entity.getPhoneNumber(), entity.getId());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_removeFromTable, id);
    }

    @Override
    public void insert(Client entity) {
        jdbcTemplate.update(SQL_insertToTable, entity.getId(), entity.getName(), entity.getPhoneNumber());
    }

}
