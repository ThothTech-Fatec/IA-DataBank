package com.mycompany.chavedeankh;
import ImportBank.leitor;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ChaveDeAnkh {

    public static final ChatLanguageModel model = OllamaChatModel.builder()
            .baseUrl("http://localhost:11434/")
            .modelName("duckdb-nsql")
            .temperature(0.8)
            .build();

    public static void processarMensagem(String message, Tela tela) {
        String schemaDefinition = """
                                  Here is the database schema that the SQL query will run on: """ + leitor.bankh;


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

            // Verifica se o ResultSet está vazio
            if (!resultSet.next()) {
                tela.exibirResultado("Não foi possível realizar a consulta. Nenhum resultado encontrado.");
            } else {
                // Processa os resultados normalmente
                do {
                    for (int i = 1; i <= columnCount; i++) {
                        resultBuilder.append(metaData.getColumnName(i)).append(": ");
                        resultBuilder.append(resultSet.getString(i)).append("\n");
                    }
                    resultBuilder.append("\n");
                } while (resultSet.next());
                tela.exibirResultado(resultBuilder.toString()); // Exibir resultados na tela
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
