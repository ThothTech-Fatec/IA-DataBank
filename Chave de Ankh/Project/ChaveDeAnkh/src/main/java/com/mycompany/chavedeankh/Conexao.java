    package com.mycompany.chavedeankh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private Connection connection;
    
    public Conexao(Usuario usuario) {
        try {
            // Registering the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Opening database connection to MySQL server
            String url = "jdbc:mysql://localhost:3306/" + usuario.getBankname();
            String user = usuario.getUsuario();
            String password = usuario.getSenha();
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
