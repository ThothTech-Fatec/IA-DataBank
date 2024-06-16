/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImportBank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class leitor {
    public static String bankh = " ";
    public static String pastas = " ";
    public static String ankh;

    public static void processFile(File file) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                bankh = bankh + line + "\n";
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("O arquivo não foi encontrado: " + e.getMessage());
        }
    }
    
    public static void processBank(File file) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                pastas = pastas + line + "\n";
            }
            scanner.close();
   
        }catch (FileNotFoundException e) {
            System.out.println("O arquivo não foi encontrado: " + e.getMessage());
        }
    }
}



/**
 *
 * @author Flavio
 */

