package com.mycompany._gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class ArchivoIO {
    
    File archivo = new File("alumnos_io.txt");
    List<String> lineas = new ArrayList();    
    
    public void escribir(String linea){
    
    leer();    
        
    //escribir
    try (BufferedWriter escritor = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(archivo),
                                StandardCharsets.UTF_8
                    )
                )
            ){
                for (String linea_saved: lineas){
                escritor.write(linea_saved);
                escritor.newLine();
                }
                escritor.write(linea);
                escritor.newLine();
                System.out.println("Archivo guardado exitosamente");
            }catch(IOException e){
                System.out.println("Error al escribir " + e.getMessage());
                }
        
    }
    
    
    public void leer(){
        //se limpia arreglo para evitar duplicado
        lineas = new ArrayList();
        
        try(//1
            BufferedReader lector = new BufferedReader(//2
            new InputStreamReader(//3
                new FileInputStream(//4
                archivo
                ), StandardCharsets.UTF_8//4
            )//3        
        )//2
                            
        ){//1
        String linea ="";
        while((linea = lector.readLine()) != null){
        lineas.add(linea);
        }
                                
        }catch(IOException e){
        System.out.println("Error al leer " +e.getMessage());
           }
        
    }
    //aqui empieza la actividad 3 
    //metodo auxiliar para la lista
    private void guardarTodo(){
        try(BufferedWriter escritor = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(archivo),StandardCharsets.UTF_8
                )
            )
                
        ){
        for (String linea_guardada: lineas){
                escritor.write(linea_guardada);
                escritor.newLine();
            }
        }catch(IOException e){
            System.out.println("Error al escribir" +e.getMessage());
        }
    }
    
    //metodo para actualizar por id
    public boolean actualizarPorId(String id, String nuevaLinea){
        leer();
        boolean encontrado = false;
        for(int i = 0; i< lineas.size(); i++){
            String linea = lineas.get(i);
            if (linea.startsWith(id +" -")|| linea.startsWith(id + " ")|| linea.equals(id)){
                lineas.set(i, nuevaLinea);
                encontrado = true;
                break;
            }
        }
        if (encontrado){
            guardarTodo();
            System.out.println("El registro del id: " + id + " se actualizo correctamente");
            
        }else{
            System.out.println("No se encontro el registro con el id indicado");
        }
        return encontrado;
    }
    
    //metodo para eliminar por id
    public boolean eliminarPorId(String id){
        leer();
        boolean encontrado = false;
        List<String> lineasActualizadas = new ArrayList<>();
        
        for(String linea : lineas){
            if (linea.startsWith(id +" -")|| linea.startsWith(id + " ")|| linea.equals(id)){
                encontrado = true;
            }else{
                lineasActualizadas.add(linea);
            }
        }
        if (encontrado){
            lineas = lineasActualizadas;
            guardarTodo();
            System.out.println("El registro con el id indicado, fue eliminado correctamente");
        }else{
            System.out.println("No se encontro el registro con dicho id");
        }
        return encontrado;
    }
    
    public List<String> getLista(){
        return lineas;
    }
    
}
