package PostgreSqlJDBC_DAO;

import dao.Dao;
import models.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientJDBC implements Dao<Client, Integer> {

    private Connection connection;
    private PreparedStatement preparedStatement;

    private String SQL_createTable = "CREATE TABLE clients (id int UNIQUE NOT NULL, name VARCHAR(50) NOT NULL, phoneNumber VARCHAR(20) NOT NULL)";
    private String SQL_dropTable = "DROP TABLE clients";
    private String SQL_getTable = "SELECT * FROM clients";
    private String SQL_selectByIdFromTable = "SELECT * FROM clients WHERE id=?";
    private String SQL_insertToTable = "INSERT INTO  clients (id, name, phoneNumber) VALUES(?,?,?)";
    private String SQL_updateInTable = "UPDATE clients SET name=? phoneNumber=? WHERE id=?";
    private String SQL_removeFromTable = "DELETE FROM clients WHERE id=?";

    public ClientJDBC(Connection connection) { this.connection = connection; }

    @Override
    public void createTable() {
        try {
            preparedStatement = connection.prepareStatement(this.SQL_createTable);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                return;
            }
        }
    }

    @Override
    public void deleteTable() {
        try {
            preparedStatement = connection.prepareStatement(this.SQL_dropTable);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                return;
            }
        }
    }

    @Override
    public List<Client> getAll() {
        Statement statement = null;
        List<Client> clients = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_getTable);
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setPhoneNumber(resultSet.getString("phoneNumber"));
                clients.add(client);
            }
            return clients;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                return clients;
            }
        }
    }

    @Override
    public Client getById(Integer id) {
        Client client = new Client();
        try {
            preparedStatement = connection.prepareStatement(SQL_selectByIdFromTable,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            client.setId(resultSet.getInt("id"));
            client.setName(resultSet.getString("name"));
            client.setPhoneNumber(resultSet.getString("phoneNumber"));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                return client;
            }
        }
    }

    @Override
    public void add(Client client) {
        try {
            preparedStatement = connection.prepareStatement(SQL_insertToTable);
            preparedStatement.setInt(1, client.getId());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                return;
            }
        }
    }

    @Override
    public void update(Client client) {
        try {
            preparedStatement = connection.prepareStatement(SQL_updateInTable);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getPhoneNumber());
            preparedStatement.setInt(3, client.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                return;
            }
        }
    }

    @Override
    public void remove(Client client) {
        try {
            preparedStatement = connection.prepareStatement(SQL_removeFromTable);
            preparedStatement.setLong(1, client.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                return;
            }
        }
    }

}
