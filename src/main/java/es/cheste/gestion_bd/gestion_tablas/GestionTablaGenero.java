package es.cheste.gestion_bd.gestion_tablas;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Genero;
import es.cheste.servicio.GeneroServicio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

/**
 * Clase para gestionar las operaciones relacionadas con la tabla de géneros.
 */
public class GestionTablaGenero {
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Muestra el menú de opciones para gestionar los géneros y procesa la opción seleccionada.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    public static void menuTablaGenero(Scanner sc) {
        int opcion;

        do {
            System.out.println("1. Insertar genero");
            System.out.println("2. Consultar genero por ID");
            System.out.println("3. Consultar todos los generos");
            System.out.println("4. Actualizar genero");
            System.out.println("5. Eliminar genero");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println();

            System.out.print("Introduce el numero de la accion que quieres realizar: ");
            opcion = sc.nextInt();

            gestionarMenuTablaGenero(opcion, sc);
        } while (opcion != 0);
    }

    /**
     * Gestiona la opción seleccionada del menú de géneros.
     *
     * @param opcion Opción seleccionada por el usuario.
     * @param sc Scanner para leer la entrada del usuario.
     */
    private static void gestionarMenuTablaGenero(int opcion, Scanner sc) {
        GeneroServicio generoServicio = new GeneroServicio();

        switch (opcion) {
            case 1:
                insertarGenero(sc);
                break;
            case 2:
                obtenerGeneroPorID(sc);
                break;
            case 3:
                obtenerTodosGeneros();
                break;
            case 4:
                actualizarGenero(sc);
                break;
            case 5:
                eliminarGenero(sc);
                break;
            case 0:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    /**
     * Inserta un nuevo género en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private static void insertarGenero(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el nombre del genero: ");
        String nombre = sc.nextLine();

        Genero genero = new Genero(nombre);

        try {
            GeneroServicio generoServicio = new GeneroServicio();
            generoServicio.insertarGenero(genero);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene un género por su ID y lo muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private static void obtenerGeneroPorID(Scanner sc) {
        System.out.print("Introduce el ID del genero: ");
        int id = sc.nextInt();

        try {
            GeneroServicio generoServicio = new GeneroServicio();
            Genero genero = generoServicio.obtenerPorID(id);
            System.out.println("ID -> Nombre");
            System.out.println("-------------------------------------------------");

            if (genero == null) {
                System.out.println("No se ha encontrado el genero con ID: " + id);
                return;
            }
            System.out.println(genero.getIdGenero() + " -> " + genero.getNombre());
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene todos los géneros y los muestra.
     */
    private static void obtenerTodosGeneros() {
        List<Genero> generos = null;

        try {
            GeneroServicio generoServicio = new GeneroServicio();
            generos = generoServicio.obtenerTodos();

            System.out.println("ID -> Nombre");
            System.out.println("-------------------------------------------------");

            for (Genero g : generos) {
                System.out.println(g.getIdGenero() + " -> " + g.getNombre());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Actualiza un género existente en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private static void actualizarGenero(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID del genero que quieres actualizar: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Introduce el nombre del genero: ");
        String nombre = sc.nextLine();

        Genero genero = new Genero(nombre);
        genero.setIdGenero(id);

        try {
            GeneroServicio generoServicio = new GeneroServicio();
            generoServicio.actualizar(genero);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Elimina un género de la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private static void eliminarGenero(Scanner sc) {
        System.out.print("Introduce el ID del genero que quieres eliminar: ");
        int id = sc.nextInt();

        try {
            GeneroServicio generoServicio = new GeneroServicio();
            generoServicio.eliminar(id);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}