package com.mycompany._gui;


/**
 *
 * @author Dell
 */
public class EjemploIO {
    
    //psvm + tab
    public static void main(String[] args) {
        ArchivoIO aio = new ArchivoIO();
        aio.escribir("280677 - Laura Fernanda Lopez Carrizosa");
        aio.escribir("676767 - Laurini Fernandini Lopezini Carrizosini");
        
        //aqui hago la prueba de los nuevos metodos encargados de tarea
        aio.actualizarPorId("280677", "280677 - Laura Fernanda Lopez actualizada");
        aio.eliminarPorId("676767");
    }
}                  