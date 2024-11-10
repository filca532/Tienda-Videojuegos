package es.cheste.gestion_bd.gestion_tablas;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.VideojuegoGenero;
import es.cheste.servicio.VideojuegoGeneroServicio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

/**
 * Clase para gestionar las operaciones relacionadas con la tabla de videojuegos y géneros.
 */
public class GestionTablaVideojuegoGenero {
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Muestra el menú de opciones para gestionar los géneros de videojuegos y procesa la opción seleccionada.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    public static void menuTablaVideojuegoGenero(Scanner sc) {
        int opcion;

        do {
            System.out.println("1. Insertar videojuego-genero");
            System.out.println("2. Consultar todos los generos de videojuegos");
            System.out.println("3. Consultar generos de videojuegos por nombre de videojuego");
            System.out.println("4. Consultar generos de videojuegos por nombre de genero");
            System.out.println("5. Actualizar videojuego-genero");
            System.out.println("6. Eliminar videojuego-genero");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println();

            System.out.print("Introduce el numero de la accion que quieres realizar: ");
            opcion = sc.nextInt();

            gestionarMenuTablaVideojuegoGenero(opcion, sc);
        } while (opcion != 0);
    }

    /**
     * Gestiona la opción seleccionada del menú de géneros de videojuegos.
     *
     * @param opcion Opción seleccionada por el usuario.
     * @param sc Scanner para leer la entrada del usuario.
     */
    private static void gestionarMenuTablaVideojuegoGenero(int opcion, Scanner sc) {
        VideojuegoGeneroServicio videojuegoGeneroServicio = new VideojuegoGeneroServicio();

        switch (opcion) {
            case 1:
                insertarVideojuegoGenero(sc);
                break;
            case 2:
                obtenerTodosGenerosVideojuegos();
                break;
            case 3:
                obtenerGenerosDeVideojuegoPorNombreVideojuego(sc);
                break;
            case 4:
                obtenerGenerosDeVideojuegoPorNombreGenero(sc);
                break;
            case 5:
                actualizarVideojuegoGenero(sc);
                break;
            case 6:
                eliminarVideojuegoGenero(sc);
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
     * Inserta una nueva relación videojuego-género en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private static void insertarVideojuegoGenero(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID del videojuego: ");
        int idVideojuego = sc.nextInt();

        System.out.print("Introduce el ID del genero: ");
        int idGenero = sc.nextInt();

        VideojuegoGenero videojuegoGenero = new VideojuegoGenero(idVideojuego, idGenero);

        try {
            VideojuegoGeneroServicio videojuegoGeneroServicio = new VideojuegoGeneroServicio();
            videojuegoGeneroServicio.insertarVideojuegoGenero(videojuegoGenero);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene todos los géneros de videojuegos y los muestra.
     */
    private static void obtenerTodosGenerosVideojuegos() {
        List<VideojuegoGenero> generos = null;

        try {
            VideojuegoGeneroServicio videojuegoGeneroServicio = new VideojuegoGeneroServicio();
            generos = videojuegoGeneroServicio.obtenerTodos();

            System.out.println("ID Videojuego - ID Genero -> Videojuego - Genero");
            System.out.println("-------------------------------------------------");

            for (VideojuegoGenero g : generos) {
                System.out.println(g.getIdProducto() + " - " + g.getIdGenero() + " -> " + g.getNombreProducto() + " - " + g.getNombreGenero());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene los géneros de videojuegos por nombre de videojuego y los muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private static void obtenerGenerosDeVideojuegoPorNombreVideojuego(Scanner sc) {
        List<VideojuegoGenero> generos = null;

        sc.nextLine();

        System.out.print("Introduce el nombre del videojuego: ");
        String nombreVideojuego = sc.nextLine();

        try {
            VideojuegoGeneroServicio videojuegoGeneroServicio = new VideojuegoGeneroServicio();
            generos = videojuegoGeneroServicio.obtenerPorVideojuegoNombre(nombreVideojuego);

            System.out.println("Videojuego -> Genero");
            System.out.println("-------------------------------------------------");

            for (VideojuegoGenero g : generos) {
                System.out.println(g.getNombreProducto() + " -> " + g.getNombreGenero());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene los géneros de videojuegos por nombre de género y los muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private static void obtenerGenerosDeVideojuegoPorNombreGenero(Scanner sc) {
        List<VideojuegoGenero> generos = null;

        sc.nextLine();

        System.out.print("Introduce el nombre del genero: ");
        String nombreGenero = sc.nextLine();

        try {
            VideojuegoGeneroServicio videojuegoGeneroServicio = new VideojuegoGeneroServicio();
            generos = videojuegoGeneroServicio.obtenerPorGeneroNombre(nombreGenero);

            System.out.println("Genero -> Videojuego");
            System.out.println("-------------------------------------------------");

            for (VideojuegoGenero g : generos) {
                System.out.println(g.getNombreGenero() + " -> " + g.getNombreProducto());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Actualiza una relación videojuego-género existente en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private static void actualizarVideojuegoGenero(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID del videojuego a actualizar: ");
        int idVideojuego = sc.nextInt();

        System.out.print("Introduce el ID del genero a actualizar: ");
        int idGenero = sc.nextInt();

        System.out.println();

        System.out.print("Introduce el nuevo ID del videojuego: ");
        int idNuevoVideojuego = sc.nextInt();

        System.out.print("Introduce el nuevo ID del genero: ");
        int idNuevoGenero = sc.nextInt();

        VideojuegoGenero videojuegoGenero = new VideojuegoGenero(idVideojuego, idGenero);
        videojuegoGenero.setIdNuevoProducto(idNuevoVideojuego);
        videojuegoGenero.setIdNuevoGenero(idNuevoGenero);

        try {
            VideojuegoGeneroServicio videojuegoGeneroServicio = new VideojuegoGeneroServicio();
            videojuegoGeneroServicio.actualizar(videojuegoGenero);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Elimina una relación videojuego-género de la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private static void eliminarVideojuegoGenero(Scanner sc) {
        System.out.print("Introduce el ID del videojuego: ");
        int idVideojuego = sc.nextInt();

        System.out.print("Introduce el ID del genero: ");
        int idGenero = sc.nextInt();

        try {
            VideojuegoGeneroServicio videojuegoGeneroServicio = new VideojuegoGeneroServicio();
            videojuegoGeneroServicio.eliminar(idVideojuego, idGenero);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}