/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImportBank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class leitor {
    static String abc = " ";
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Manhã\\Documents\\GitHub\\ThothTech-2\\Chave de Ankh\\Project\\ChaveDeAnkh\\src\\main\\java\\ImportBank\\abb.sql";

        // Criando um objeto File com o caminho do arquivo
        File file = new File(filePath);

        try {
            // Criando um objeto Scanner para ler o arquivo
            Scanner scanner = new Scanner(file);

            // Lendo o arquivo linha por linha e imprimindo no console
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                abc = abc + line + "\n";
            }
            System.out.println(abc);
            // Fechando o scanner após a leitura
            scanner.close();
        } catch (FileNotFoundException e) {
            // Tratando o caso em que o arquivo não é encontrado
            System.out.println("O arquivo não foi encontrado: " + e.getMessage());
        }
    }
}


/**
 *
 * @author Flavio
 */

