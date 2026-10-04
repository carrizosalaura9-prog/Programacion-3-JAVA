
package com.mycompany._gui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 *
 * @author Dell
 */
public class EjemploNIO {
    
    public static void main(String[] args) {
    
        Path ruta = Path.of("Alumnos_nio.txt");
        
        
        try {// \n salto de linea \t tabulacion \r retorno carro ESCRITURA
            Files.writeString(ruta,"\n789456 - Laura Fernanda", StandardOpenOption.CREATE,StandardOpenOption.APPEND);
            System.out.println("Archivo guardado con exito");
            
            //LECTURA
            List<String> lineas = Files.readAllLines(ruta);
            lineas.forEach(System.out::println);
            
        } catch (IOException e) {
            System.err.println("Error en el archivo: "+ e.getMessage());
        }
        
    }
    
}
