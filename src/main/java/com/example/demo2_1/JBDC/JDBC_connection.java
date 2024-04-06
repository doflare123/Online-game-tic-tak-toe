package com.example.demo2_1.JBDC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JDBC_connection{
    private static final String URL = "jdbc:mysql://localhost:3306/tic tac toe";
    private static final String Name = "SimpleUser";
    private static final String Password = "2587190qaZ";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, Name, Password);
    }
}
