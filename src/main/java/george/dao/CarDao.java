package george.dao;

import george.dao.Dao;
import george.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class CarDao implements Dao<Car,Integer> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SQL_getTable = "SELECT * FROM cars";
    private final String SQL_selectByIdFromTable = "SELECT * FROM cars WHERE id=?";
    private final String SQL_updateInTable = "UPDATE cars SET naming=?, price=? WHERE id=?";
    private final String SQL_removeFromTable = "DELETE FROM cars WHERE id=?";
    private final String SQL_insertToTable = "INSERT INTO  cars (id, naming, price) VALUES(?,?,?)";

    private static Car mapRow(ResultSet resultSet, int iterator) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt("id"));
        car.setNaming(resultSet.getString("naming"));
        car.setPrice(resultSet.getFloat("price"));
        return car;
    }

    @Override
    public List<Car> getAll() {
        return jdbcTemplate.query(SQL_getTable, CarDao::mapRow);
    }

    @Override
    public Car getEntityById(Integer id) {
        return (Car)jdbcTemplate.queryForObject(SQL_selectByIdFromTable, new Object[]{id}, CarDao::mapRow);
    }

    @Override
    public void update(Car entity) {
        jdbcTemplate.update(SQL_updateInTable, entity.getNaming(), entity.getPrice(), entity.getId());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_removeFromTable, id);
    }

    @Override
    public void insert(Car entity) {
        jdbcTemplate.update(SQL_insertToTable, entity.getId(), entity.getNaming(), entity.getPrice());
    }

}
