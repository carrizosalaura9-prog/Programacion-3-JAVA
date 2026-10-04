
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class Main {
    private static final String ARCHIVO_DATOS = "alumnos.txt";

    public static void main(String[] args) {
        AlumnoRepository repository = new AlumnoRepository(ARCHIVO_DATOS);
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            mostrarMenu();
            System.out.print("Seleccione una opcion: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
                System.out.println();

                switch (opcion) {
                    case 1 -> registrarAlumno(repository, scanner);
                    case 2 -> listarAlumnos(repository);
                    case 3 -> buscarAlumno(repository, scanner);
                    case 4 -> actualizarAlumno(repository, scanner);
                    case 5 -> eliminarAlumno(repository, scanner);
                    case 6 -> System.out.println("Programa finalizado exitosamente!");
                    default -> System.out.println("Opcion no valida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nError: Debe ingresar un numero valido como opcion");
            } catch (IOException e) {
                System.out.println("\nError de Entrada/Salida al procesar el archivo: " + e.getMessage());
            }
            System.out.println();
        } while (opcion != 6);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("=== MENU GESTION DE ALUMNOS (JAVA NIO) ===");
        System.out.println("1. Registrar nuevo alumno");
        System.out.println("2. Ver todos los alumnos");
        System.out.println("3. Buscar alumno por ID");
        System.out.println("4. Actualizar nombre de alumno");
        System.out.println("5. Eliminar alumno");
        System.out.println("6. Salir");
    }

    private static void registrarAlumno(AlumnoRepository repo, Scanner scanner) throws IOException {
        System.out.println("--- Registrar Alumno ---");
        System.out.print("Ingrese ID: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("El ID no puede estar vacio.");
            return;
        }

        if (repo.existeId(id)) {
            System.out.println("Error: El ID " + id + " ya se encuentra registrado.");
            return;
        }

        System.out.print("Ingrese Nombre: ");
        String nombre = scanner.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacio.");
            return;
        }

        repo.registrar(new Alumno(id, nombre));
        System.out.println("Alumno registrado con exito!");
    }

    private static void listarAlumnos(AlumnoRepository repo) throws IOException {
        System.out.println("--- Lista de Alumnos ---");
        List<Alumno> alumnos = repo.obtenerTodos();

        if (alumnos.isEmpty()) {
            System.out.println("El archivo se encuentra vacio o no contiene registros validos");
            return;
        }

        for (Alumno a : alumnos) {
            System.out.println(a.getId() + " - " + a.getNombre());
        }
    }

    private static void buscarAlumno(AlumnoRepository repo, Scanner scanner) throws IOException {
        System.out.println("--- Buscar Alumno por ID ---");
        System.out.print("Ingrese el ID a buscar: ");
        String id = scanner.nextLine().trim();

        Alumno alumno = repo.buscarPorId(id);
        if (alumno != null) {
            System.out.println("Alumno encontrado:");
            System.out.println(alumno);
        } else {
            System.out.println("No se encontro ningun alumno con el ID: " + id);
        }
    }

    private static void actualizarAlumno(AlumnoRepository repo, Scanner scanner) throws IOException {
        System.out.println("--- Actualizar Nombre de Alumno ---");
        System.out.print("Ingrese ID del alumno a modificar: ");
        String id = scanner.nextLine().trim();

        if (!repo.existeId(id)) {
            System.out.println("Error: No se encontro ningun alumno con el ID " + id + ".");
            return;
        }

        System.out.print("Ingrese el nuevo Nombre Completo: ");
        String nuevoNombre = scanner.nextLine().trim();

        if (nuevoNombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacio.");
            return;
        }

        if (repo.actualizarNombre(id, nuevoNombre)) {
            System.out.println("Nombre actualizado con exito!");
        } else {
            System.out.println("Error al intentar actualizar la informacion");
        }
    }

    private static void eliminarAlumno(AlumnoRepository repo, Scanner scanner) throws IOException {
        System.out.println("--- Eliminar Alumno ---");
        System.out.print("Ingrese ID del alumno a eliminar: ");
        String id = scanner.nextLine().trim();

        if (repo.eliminar(id)) {
            System.out.println("Alumno eliminado con exito!");
        } else {
            System.out.println("Error: No existe un alumno con el ID " + id + ".");
        }
    }
}
