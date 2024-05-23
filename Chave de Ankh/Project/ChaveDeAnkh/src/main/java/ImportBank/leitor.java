/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImportBank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import com.mycompany.chavedeankh.Inicial;

public class leitor {
public static String bankh = " ";
public static String pastas = " ";
public static String ankh;


    public static void main(String[] args) {
        // Aqui você pode chamar o método processFile e passar o arquivo selecionado como parâmetro
    }

    // Este método recebe um arquivo como parâmetro e processa seu conteúdo
    public static void processFile(File file) {
        try {
            // Criando um objeto Scanner para ler o arquivo
            Scanner scanner = new Scanner(file);

            // Lendo o arquivo linha por linha e armazenando seu conteúdo na variável bankh
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                bankh = bankh + line + "\n";
            }
           
            
            // Fechando o scanner após a leitura
            scanner.close();
        } catch (FileNotFoundException e) {
            // Tratando o caso em que o arquivo não é encontrado
            System.out.println("O arquivo não foi encontrado: " + e.getMessage());
        }
    }
    public static void processBank(File file) {
         try {
            // Criando um objeto Scanner para ler o arquivo
            Scanner scanner = new Scanner(file);

            // Lendo o arquivo linha por linha e armazenando seu conteúdo na variável bankh
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                pastas = pastas + line + "\n";
            }
           
            
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

