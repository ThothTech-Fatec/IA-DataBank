/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chavedeankh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author conta
 */
public class Conexao {
    private static final String URL = Data.URL;
    private static final String USER = Data.USER;
    private static final String PASSWORD = Data.PASSWORD;
    private static Connection connection;
    
    public static Connection getConnection() {
        try {
            // Registering the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Opening database connection to MySQL server
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}