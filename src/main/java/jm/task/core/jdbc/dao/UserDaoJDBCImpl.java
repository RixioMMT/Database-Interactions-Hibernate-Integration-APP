package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS RIXIOTABLE (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(20), " +
                "lastName VARCHAR(20), " +
                "age TINYINT)";
        try (Connection connection = getConnection() ; Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Table created successfully");
        }
        catch (SQLException e) {
            System.out.println("Error at creating table");
        }
    }

    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS RIXIOTABLE";
        try (Connection connection = getConnection() ; Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Table dropped successfully");
        }
        catch (SQLException e) {
            System.out.println("Error at dropping table");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO RIXIOTABLE (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = getConnection() ; PreparedStatement prepared = connection.prepareStatement(query)) {
            prepared.setString(1, name);
            prepared.setString(2, lastName);
            prepared.setByte(3, age);
            prepared.executeUpdate();
            System.out.println("User saved successfully");
        }
        catch (SQLException e) {
            System.out.println("Error at saving user");
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM RIXIOTABLE WHERE id = ?";
        try (Connection connection = getConnection() ; PreparedStatement prepared = connection.prepareStatement(query)) {
            prepared.setLong(1, id);
            prepared.executeUpdate();
            System.out.println("User removed successfully");
        }
        catch (SQLException e) {
            System.out.println("Error at removing user");
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        String query = "SELECT id, name, lastName, age FROM RIXIOTABLE";
        try (Connection connection = getConnection() ;
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                User currentUser = new User();
                currentUser.setId(resultSet.getLong("id"));
                currentUser.setName(resultSet.getString("name"));
                currentUser.setLastName(resultSet.getString("lastName"));
                currentUser.setAge(resultSet.getByte("age"));
                usersList.add(currentUser);
            }
            System.out.println("User list shown below:");
        }
        catch (SQLException e) {
            System.out.println("Error at showing users");
        }
        return usersList;
    }

    public void cleanUsersTable() {
        String query = "DELETE FROM RIXIOTABLE";
        try (Connection connection = getConnection() ; Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Table cleaned successfully");
        }
        catch (SQLException e) {
            System.out.println("Error at cleaning table");
        }
    }
}
