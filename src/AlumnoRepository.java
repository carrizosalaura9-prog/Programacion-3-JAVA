
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class AlumnoRepository {
    private final Path filePath;
    
    //accede a los datos
    public AlumnoRepository(String fileName){
        this.filePath = Path.of(fileName);
        inicializarArchivo();
    }
    
    //verificacion si el archivo existe al iniciar
    private void inicializarArchivo(){
        try {
            if (Files.notExists(filePath)){
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.err.println("Error al inicializar el archivo, lo siento: " + e.getMessage());
        }
    }
    
    //lee las lienas del archivo y las pasa a una lista
    public List<Alumno> obtenerTodos() throws IOException{
        List<String> lineas = Files.readAllLines(filePath);
        List<Alumno> alumnos = new ArrayList<>();
        for(String linea : lineas){
            if(!linea.isBlank()){
                Alumno alumno = Alumno.fromFileFormat(linea);
                if(alumno != null){
                    alumnos.add(alumno);
                }
            }
        }
        return alumnos;
    }
    
    //busca al alumno por su id
    public Alumno buscarPorId(String id) throws IOException{
        List<Alumno> alumnos = obtenerTodos();
        for(Alumno a : alumnos){
            if(a.getId().equalsIgnoreCase(id.trim())){
                return a;
            }
        }
        return null;
    }
    
    //valida que exista el id
    public boolean existeId(String id) throws IOException{
        return buscarPorId(id) != null;
    }
    
    //agrega el nuevo registro
    public void registrar(Alumno alumno)throws IOException{
        String linea = alumno.toFileFormat() + System.lineSeparator();
        Files.writeString(filePath,linea, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
    
    //modifca el nombre del alumno manteniendo id
    public boolean actualizarNombre(String id, String nuevoNombre) throws IOException{
    List<Alumno> alumnos = obtenerTodos();
        boolean encontrado = false;
        List<String> lineasNuevas = new ArrayList<>();

        for (Alumno a : alumnos) {
            if (a.getId().equalsIgnoreCase(id.trim())) {
                a.setNombre(nuevoNombre);
                encontrado = true;
            }
            lineasNuevas.add(a.toFileFormat());
        }

        if (encontrado) {
            Files.write(filePath, lineasNuevas);
        }
        return encontrado;    
    }
    
    //elimina
    public boolean eliminar(String id) throws IOException {
        List<Alumno> alumnos = obtenerTodos();
        List<String> lineasNuevas = new ArrayList<>();
        boolean encontrado = false;

        for (Alumno a : alumnos) {
            if (a.getId().equalsIgnoreCase(id.trim())) {
                encontrado = true;
            } else {
                lineasNuevas.add(a.toFileFormat());
            }
        }

        if (encontrado) {
            Files.write(filePath, lineasNuevas);
        }
        return encontrado;
    }    
    
}
