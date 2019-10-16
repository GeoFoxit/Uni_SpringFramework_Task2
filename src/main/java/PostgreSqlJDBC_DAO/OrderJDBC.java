package PostgreSqlJDBC_DAO;

import dao.Dao;
import models.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderJDBC implements Dao<Order, Integer> {

    private Connection connection;
    private PreparedStatement preparedStatement;

    private String SQL_createTable = "CREATE TABLE orders (id int UNIQUE NOT NULL, carId int NOT NULL, clientId int NOT NULL)";
    private String SQL_dropTable = "DROP TABLE orders";
    private String SQL_getTable = "SELECT * FROM orders";
    private String SQL_selectByIdFromTable = "SELECT * FROM orders WHERE id=?";
    private String SQL_insertToTable = "INSERT INTO  orders (id, carId, clientId) VALUES(?,?,?)";
    private String SQL_updateInTable = "UPDATE orders SET carId=? clientId=? WHERE id=?";
    private String SQL_removeFromTable = "DELETE FROM orders WHERE id=?";

    public OrderJDBC(Connection connection) { this.connection = connection; }

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
    public List<Order> getAll() {
        Statement statement = null;
        List<Order> orders = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_getTable);
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setCarId(resultSet.getInt("carId"));
                order.setClientId(resultSet.getInt("clientId"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                return orders;
            }
        }
    }

    @Override
    public Order getById(Integer id) {
        Order order = new Order();
        try {
            preparedStatement = connection.prepareStatement(SQL_selectByIdFromTable,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            order.setId(resultSet.getInt("id"));
            order.setCarId(resultSet.getInt("carId"));
            order.setClientId(resultSet.getInt("clientId"));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                return order;
            }
        }
    }

    @Override
    public void add(Order order) {
        try {
            preparedStatement = connection.prepareStatement(SQL_insertToTable);
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, order.getCarId());
            preparedStatement.setFloat(3, order.getClientId());
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
    public void update(Order order) {
        try {
            preparedStatement = connection.prepareStatement(SQL_updateInTable);
            preparedStatement.setInt(1, order.getCarId());
            preparedStatement.setInt(2, order.getClientId());
            preparedStatement.setInt(3, order.getId());
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
    public void remove(Order order) {
        try {
            preparedStatement = connection.prepareStatement(SQL_removeFromTable);
            preparedStatement.setLong(1, order.getId());
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
