package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/rixio";
    public Connection getConnection() {
        Connection connection = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, "root", "mysqlapesta123");
            System.out.println("Connection success");
        }
        catch(ClassNotFoundException | SQLException e) {
            System.out.println("Connection error");
        }
        return connection;
    }
}
