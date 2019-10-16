import PostgreSqlJDBC_DAO.CarJDBC;
import PostgreSqlJDBC_DAO.ClientJDBC;
import PostgreSqlJDBC_DAO.OrderJDBC;
import models.Car;
import models.Client;
import models.Order;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        //Facade init
        PostgreFacadeJDBCautorent postgreFacadeJDBCautorent = PostgreFacadeJDBCautorent.getInstance();
        postgreFacadeJDBCautorent.initDb();
        //Get Dao
        ClientJDBC clientJDBC = postgreFacadeJDBCautorent.getClientDao();
        CarJDBC carJDBC = postgreFacadeJDBCautorent.getCarDao();
        OrderJDBC orderJDBC = postgreFacadeJDBCautorent.getOrderDao();
        //Create models
        //Clients
        Client client0 = new Client(0,"George Borodin", "+38(095)7857774");
        Client client1 = new Client(1,"Mike Aurelius", "+9(033)6573883");
        Client client2 = new Client(2,"Steve Jobs", "+4(038)0990909");
        //Cars
        Car car0 = new Car(0,"BMW X5",249.99f);
        Car car1 = new Car(1,"Audi V8",200.50f);
        Car car2 = new Car(2,"TOYOTA Land Cruiser 200",450.00f);
        //Orders
        Order order0 = new Order(0,0,0);
        Order order1 = new Order(1,2,2);
        //Add to db
        //Add clients
        clientJDBC.add(client0);
        clientJDBC.add(client1);
        clientJDBC.add(client2);
        //Add cars
        carJDBC.add(car0);
        carJDBC.add(car1);
        carJDBC.add(car2);
        //Add orders
        orderJDBC.add(order0);
        orderJDBC.add(order1);
        //ShowDb
        postgreFacadeJDBCautorent.displayDb();
//        //Remove
//        clientJDBC.remove(client1);
//        carJDBC.remove(car1);
//        orderJDBC.remove(order0);
//        //ShowDb
//        postgreFacadeJDBCautorent.displayDb();
        //Facade delete
        postgreFacadeJDBCautorent.deleteDb();
    }
}
