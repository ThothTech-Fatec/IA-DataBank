/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImportBank;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class leitor {

    public static void main(String[] args) {
        Path filePath = toPath("C:\\Users\\Manhã\\Documents\\GitHub\\ThothTech-2\\Chave de Ankh\\Project\\ChaveDeAnkh\\Fafagamers.pdf");
        Document document = FileSystemDocumentLoader.loadDocument(filePath);
        System.out.println(document);
    }
    
    private static Path toPath(String fileName) {
        try {
            URL fileUrl = leitor.class.getResource(fileName); // Corrigindo a referência à classe
            if (fileUrl == null) {
                throw new IllegalArgumentException("O arquivo " + fileName + " não foi encontrado.");
            }
            return Paths.get(fileUrl.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}


/**
 *
 * @author Manhã
 */

