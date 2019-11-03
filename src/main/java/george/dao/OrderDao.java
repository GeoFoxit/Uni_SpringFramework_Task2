package george.dao;

import george.dao.Dao;
import george.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class OrderDao implements Dao<Order, Integer> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private String SQL_getTable = "SELECT * FROM orders";
    private String SQL_selectByIdFromTable = "SELECT * FROM orders WHERE id=?";
    private String SQL_insertToTable = "INSERT INTO  orders (id, carId, clientId) VALUES(?,?,?)";
    private String SQL_updateInTable = "UPDATE orders SET carId=?, clientId=? WHERE id=?";
    private String SQL_removeFromTable = "DELETE FROM orders WHERE id=?";

    private static Order mapRow(ResultSet resultSet, int iterator) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setCarId(resultSet.getInt("carId"));
        order.setClientId(resultSet.getInt("clientId"));
        return order;
    }

    @Override
    public List<Order> getAll() {
        return jdbcTemplate.query(SQL_getTable, OrderDao::mapRow);
    }

    @Override
    public Order getEntityById(Integer id) {
        return (Order)jdbcTemplate.queryForObject(SQL_selectByIdFromTable, new Object[]{id}, OrderDao::mapRow);
    }

    @Override
    public void update(Order entity) {
        jdbcTemplate.update(SQL_updateInTable, entity.getCarId(), entity.getClientId(), entity.getId());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_removeFromTable, id);
    }

    @Override
    public void insert(Order entity) {
        jdbcTemplate.update(SQL_insertToTable, entity.getId(), entity.getCarId(), entity.getClientId());
    }

}
