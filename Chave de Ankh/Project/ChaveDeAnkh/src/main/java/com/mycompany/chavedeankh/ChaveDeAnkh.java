package com.mycompany.chavedeankh;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ChaveDeAnkh {
    
        public static void main(String[] args) {
        // Criar uma instância da tela e torná-la visível
        ChatLanguageModel model = OllamaChatModel.builder()
                .baseUrl("http://localhost:11434/")
                .modelName("duckdb-nsql")
                .temperature(0.8)
                .build();
        
        Tela tela = new Tela(model);
        tela.setVisible(true);
    }

    private static final ChatLanguageModel model = OllamaChatModel.builder()
            .baseUrl("http://localhost:11434/")
            .modelName("duckdb-nsql")
            .temperature(0.8)
            .build();

    public static void processarMensagem(String message, Tela tela) {
        String schemaDefinition = "Provided this schema:\n\n"
                + "CREATE TABLE users ("
                + "user_id INT AUTO_INCREMENT PRIMARY KEY,"
                + "username VARCHAR(45) NOT NULL,"
                + "password VARCHAR(30) NOT NULL,"
                + "email VARCHAR(60) NOT NULL"
                + ");"
                + "CREATE TABLE pedidos ("
                + "pedido_id INT AUTO_INCREMENT PRIMARY KEY,"
                + "user_id INT NOT NULL,"
                + "data_pedido DATE NOT NULL,"
                + "FOREIGN KEY (user_id) REFERENCES users(user_id)"
                + ");"
                + "CREATE TABLE produtos ("
                + "produto_id INT AUTO_INCREMENT PRIMARY KEY,"
                + "nome VARCHAR(100) NOT NULL,"
                + "preco DECIMAL(10,2) NOT NULL"
                + ");"
                + "CREATE TABLE itens_pedido ("
                + "item_id INT AUTO_INCREMENT PRIMARY KEY,"
                + "pedido_id INT NOT NULL,"
                + "produto_id INT NOT NULL,"
                + "quantidade INT NOT NULL,"
                + "FOREIGN KEY (pedido_id) REFERENCES pedidos(pedido_id),"
                + "FOREIGN KEY (produto_id) REFERENCES produtos(produto_id)"
                + ");";

        String resposta = model.generate(schemaDefinition + " " + message);
        System.out.println("Resposta: " + resposta);

        Connection connection = Conexao.getConnection();
        if (connection != null) {
            try {
                String sqlQuery = resposta;
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

                System.out.println("SQL Query: " + sqlQuery);

                ResultSet resultSet = preparedStatement.executeQuery();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                StringBuilder resultBuilder = new StringBuilder("Chave De Ankh:\n");
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        resultBuilder.append(metaData.getColumnName(i)).append(": ");
                        resultBuilder.append(resultSet.getString(i)).append("\n");
                    }
                    resultBuilder.append("\n");
                }

                resultSet.close();
                preparedStatement.close();
                tela.exibirResultado(resultBuilder.toString()); // Exibir resultados na tela
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexao.closeConnection();
            }
        }
    }
}
