package com.mycompany.chavedeankh;

import ImportBank.leitor;
import com.mycompany.chavedeankh.Usuario.AnotherClass;
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
            .modelName(AnotherClass.comboBoxValue)
            .temperature(0.8)
            .build();

    public static void processarMensagem(String message, Tela tela, Usuario usuario) {
        Conexao conexao = new Conexao(usuario);
        Connection connection = conexao.getConnection();

        if (connection == null){
            System.out.println("CONEXÃO É NULL");
            String schemaDefinition = "Analyze this database dump: " + leitor.pastas + " Nthen there will be a question with the aim of performing a query on this dump, you must provide the answer to this query, and answer only the essential without including additional details, and the answer must be in Brazilian Portuguese:\n";
            
            System.out.println(schemaDefinition);
            
            String resposta1 = model.generate(schemaDefinition + " " + message);
            
            StringBuilder resultBuilder = new StringBuilder("Chave De Ankh:\n");
            tela.exibirResultado(resposta1+"\n\n"); 
            
    
        }
        
        else {
            System.out.println("CONEXÃO NÃO É NULL");
            String schemaDefinition = """
            Please respond only using SQL queries. Do not use natural language in your responses and do not use "```".  """ + leitor.bankh;

            String resposta1 = model.generate(schemaDefinition + " " + message);
            String resposta = resposta1.replace("```", "");
            String sqlQuery = resposta;
            
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

                System.out.println("SQL Query: " + sqlQuery);

                ResultSet resultSet = preparedStatement.executeQuery();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                StringBuilder resultBuilder = new StringBuilder("Chave De Ankh:\n");

                if (!resultSet.next()) {
                    tela.exibirResultado("Não foi possível realizar a consulta. Nenhum resultado encontrado.");
                } else {
                    do {
                        for (int i = 1; i <= columnCount; i++) {
                            resultBuilder.append(metaData.getColumnName(i)).append(": ");
                            resultBuilder.append(resultSet.getString(i)).append("\n");
                        }
                        resultBuilder.append("\n");
                    } while (resultSet.next());
                    tela.exibirResultado(resultBuilder.toString()); 
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                tela.exibirResultado("Não foi possível realizar a consulta.");
                e.printStackTrace();
            } finally {
                conexao.closeConnection();
            }
        }
    }
}
