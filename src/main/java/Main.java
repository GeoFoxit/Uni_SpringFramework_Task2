import models.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Client> result = new ArrayList<Client>();

        String SQL_SELECT = "Select * from client";

        // auto close connection and preparedStatement
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/autorent", "postgres", "george");
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String full_name = resultSet.getString("full_name");
                String category = resultSet.getString("license_category");
                int age = resultSet.getInt("age");

                Client obj = new Client(id,full_name,category,age);

                result.add(obj);

            }
            result.forEach(x -> System.out.format("id: %s | name: %s\n", x.id, x.full_name));

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
