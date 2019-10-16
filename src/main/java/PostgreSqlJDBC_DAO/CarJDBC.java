package PostgreSqlJDBC_DAO;

import dao.Dao;
import models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarJDBC implements Dao<Car,Integer> {

    private Connection connection;
    private PreparedStatement preparedStatement;

    private String SQL_createTable = "CREATE TABLE cars (id int UNIQUE NOT NULL, naming VARCHAR(50) NOT NULL, price FLOAT(10) NOT NULL)";
    private String SQL_dropTable = "DROP TABLE cars";
    private String SQL_getTable = "SELECT * FROM cars";
    private String SQL_selectByIdFromTable = "SELECT * FROM cars WHERE id=?";
    private String SQL_insertToTable = "INSERT INTO  cars (id, naming, price) VALUES(?,?,?)";
    private String SQL_updateInTable = "UPDATE cars SET naming=? price=? WHERE id=?";
    private String SQL_removeFromTable = "DELETE FROM cars WHERE id=?";

    public CarJDBC(Connection connection) { this.connection = connection; }

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
    public List<Car> getAll() {
        Statement statement = null;
        List<Car> cars = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_getTable);
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setNaming(resultSet.getString("naming"));
                car.setPrice(resultSet.getFloat("price"));
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                return cars;
            }
        }
    }

    @Override
    public Car getById(Integer id) {
        Car car = new Car();
        try {
            preparedStatement = connection.prepareStatement(SQL_selectByIdFromTable,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            car.setId(resultSet.getInt("id"));
            car.setNaming(resultSet.getString("naming"));
            car.setPrice(resultSet.getFloat("price"));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                return car;
            }
        }
    }

    @Override
    public void add(Car car) {
        try {
            preparedStatement = connection.prepareStatement(SQL_insertToTable);
            preparedStatement.setInt(1, car.getId());
            preparedStatement.setString(2, car.getNaming());
            preparedStatement.setFloat(3, car.getPrice());
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
    public void update(Car car) {
        try {
            preparedStatement = connection.prepareStatement(SQL_updateInTable);
            preparedStatement.setString(1, car.getNaming());
            preparedStatement.setFloat(2, car.getPrice());
            preparedStatement.setInt(3, car.getId());
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
    public void remove(Car car) {
        try {
            preparedStatement = connection.prepareStatement(SQL_removeFromTable);
            preparedStatement.setLong(1, car.getId());
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
