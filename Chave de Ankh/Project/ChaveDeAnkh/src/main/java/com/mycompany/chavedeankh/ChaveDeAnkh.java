package com.mycompany.chavedeankh;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

/**
 *
 * @author T-Gamer
 */
public class ChaveDeAnkh {
    public static void main(String[] args) { 
    String schemaDefinition = "Provided this schema:\n\n"
                         + "CREATE TABLE taxi ("
                         + "VendorID bigint,"
                         + "tpep_pickup_datetime timestamp,"
                         + "tpep_dropoff_datetime timestamp,"
                         + "passenger_count double,"
                         + "trip_distance double,"
                         + "fare_amount double,"
                         + "extra double,"
                         + "tip_amount double,"
                         + "tolls_amount double,"
                         + "improvement_surcharge double,"
                         + "total_amount double"
                         + ");";

    
    ChatLanguageModel model = OllamaChatModel.builder()
            .baseUrl("http://localhost:11434/")
            .modelName("duckdb-nsql")
            .temperature(0.8)
            .build();
    String resposta = model.generate(schemaDefinition +" How many vendors we have?");
    System.out.println(resposta);
    }
}
