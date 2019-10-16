import PostgreSqlJDBC_DAO.CarJDBC;
import PostgreSqlJDBC_DAO.ClientJDBC;
import PostgreSqlJDBC_DAO.OrderJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreFacadeJDBCautorent {

    private final String URL = "jdbc:postgresql://127.0.0.1:5432/autorent";
    private final String USER = "postgres";
    private final String PASSWORD = "george";

    private Connection connection;

    private ClientJDBC clientJDBC;
    private CarJDBC carJDBC;
    private OrderJDBC orderJDBC;

    private static PostgreFacadeJDBCautorent instance = null;
    private PostgreFacadeJDBCautorent() {}

    public static PostgreFacadeJDBCautorent getInstance() {
        if (instance == null) {
            instance =  new PostgreFacadeJDBCautorent();
        }
        return instance;
    }

    public boolean initDb() {
        try {
            //get connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //set connection
            clientJDBC = new ClientJDBC(connection);
            carJDBC = new CarJDBC(connection);
            orderJDBC = new OrderJDBC(connection);
            //create tables
            clientJDBC.createTable();
            carJDBC.createTable();
            orderJDBC.createTable();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDb() {
        clientJDBC.deleteTable();
        carJDBC.deleteTable();
        orderJDBC.deleteTable();
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void displayDb() {
        System.out.println("-------Clients-------");
        clientJDBC.getAll().forEach( client -> {
            System.out.print("ID: " + client.getId() + " ");
            System.out.print("FullName: " + client.getName() + " ");
            System.out.println("Phone: " + client.getPhoneNumber());
        });
        System.out.println("-------Cars-------");
        carJDBC.getAll().forEach( car -> {
            System.out.print("ID: " + car.getId() + " ");
            System.out.print("Naming: " + car.getNaming() + " ");
            System.out.println("Price: " + car.getPrice());
        });
        System.out.println("-------Orders-------");
        orderJDBC.getAll().forEach( order -> {
            System.out.print("ID: " + order.getId() + " ");
            System.out.print("CarId: " + order.getCarId() + " ");
            System.out.println("ClientId: " + order.getClientId());
        });
    }

    public ClientJDBC getClientDao() {
        return this.clientJDBC;
    }

    public CarJDBC getCarDao() {
        return this.carJDBC;
    }

    public OrderJDBC getOrderDao() {
        return this.orderJDBC;
    }

}
