/**
 *
 * @author Dell
 */
public class Alumno {
    private String nombre;
    private String id;
    
    
    //creamos alumno
    public Alumno(String id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }
    
    //regresamos id
    public String getId(){
        return id;
    }
    
    //pasamos id
    public void setId(String id){
        this.id = id;
    }
    
    //regresamos nombre
    public String getNombre(){
        return nombre;
    }
    
    //pasamos nombre
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    //pasamos el formato solicitado
    public String toFileFormat(){
        return id + " - " + nombre;
    }
    
    //genera al alumno
    public static Alumno fromFileFormat(String line){
        if (line == null || !line.contains(" - ")){
            return null;
        }
        
        String[] parts = line.split(" - ", 2);
        if (parts.length == 2){
            return new Alumno(parts[0].trim(), parts[1].trim());
        }
        return null;
    }
    
    //proteje al compilador
    @Override
    public String toString(){
        return "ID: " +id+"| Nombre: "+ nombre;
    }
}
