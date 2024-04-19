/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.chavedeankh;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author Manhã
 */
public class ChaveDeAnkh {

    public static void main(String[] args) throws SQLException {

        String schemaDefinition = "Provided this schema:\n\n"
             + "CREATE TABLE users ("
             + "user_id INT AUTO_INCREMENT PRIMARY KEY,"
             + "username VARCHAR(45) NOT NULL,"
             + "password VARCHAR(30) NOT NULL,"
             + "email VARCHAR(60) NOT NULL"
             + ");\n\n"
             + "CREATE TABLE pedidos ("
             + "pedido_id INT AUTO_INCREMENT PRIMARY KEY,"
             + "user_id INT NOT NULL,"
             + "data_pedido DATE NOT NULL,"
             + "FOREIGN KEY (user_id) REFERENCES users(user_id)"
             + ");\n\n"
             + "CREATE TABLE produtos ("
             + "produto_id INT AUTO_INCREMENT PRIMARY KEY,"
             + "nome VARCHAR(100) NOT NULL,"
             + "preco DECIMAL(10,2) NOT NULL"
             + ");\n\n"
             + "CREATE TABLE itens_pedido ("
             + "item_id INT AUTO_INCREMENT PRIMARY KEY,"
             + "pedido_id INT NOT NULL,"
             + "produto_id INT NOT NULL,"
             + "quantidade INT NOT NULL,"
             + "FOREIGN KEY (pedido_id) REFERENCES pedidos(pedido_id),"
             + "FOREIGN KEY (produto_id) REFERENCES produtos(produto_id)"
             + ");";

        ChatLanguageModel model = OllamaChatModel.builder()
                .baseUrl("http://localhost:11434/")
                .modelName("duckdb-nsql")
                .temperature(0.8)
                .build();
        
        String resposta = model.generate(schemaDefinition + " quantos produtos vendemos no total");
    
        Connection connection = Conexao.getConnection();
        if (connection != null) {
            try {
                String sqlQuery = resposta;
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

                // Print the SQL query
                System.out.println("SQL Query: " + sqlQuery);

                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                // Printing column names
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metaData.getColumnName(i) + "\t");
                }
                System.out.println();

                // Printing data rows
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(resultSet.getString(i) + "\t");
                    }
                    System.out.println();
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexao.closeConnection();
            }
        }
    }
}
