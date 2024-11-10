package es.cheste.gestion_bd.gestion_tablas;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.VideojuegoPlataforma;
import es.cheste.servicio.VideojuegoPlataformaServicio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

/**
 * Clase para gestionar las operaciones relacionadas con la tabla de videojuegos y plataformas.
 */
public class GestionTablaVideojuegoPlataforma {
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Muestra el menú de opciones para gestionar las plataformas de videojuegos y procesa la opción seleccionada.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    public static void menuTablaVideojuegoPlataforma(Scanner sc) {
        int opcion;

        do {
            System.out.println("1. Insertar videojuego-plataforma");
            System.out.println("2. Consultar todas las plataformas de videojuegos");
            System.out.println("3. Consultar plataformas de videojuegos por nombre de videojuego");
            System.out.println("4. Consultar plataformas de videojuegos por nombre de plataforma");
            System.out.println("5. Actualizar videojuego-plataforma");
            System.out.println("6. Eliminar videojuego-plataforma");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println();

            System.out.print("Introduce el numero de la accion que quieres realizar: ");
            opcion = sc.nextInt();

            gestionarMenuTablaVideojuegoPlataforma(opcion, sc);
        } while (opcion != 0);
    }

    /**
     * Gestiona la opción seleccionada del menú de plataformas de videojuegos.
     *
     * @param opcion Opción seleccionada por el usuario.
     * @param sc Scanner para leer la entrada del usuario.
     */
    private static void gestionarMenuTablaVideojuegoPlataforma(int opcion, Scanner sc) {
        VideojuegoPlataformaServicio videojuegoPlataformaServicio = new VideojuegoPlataformaServicio();

        switch (opcion) {
            case 1:
                insertarVideojuegoPlataforma(sc);
                break;
            case 2:
                obtenerTodasPlataformasVideojuegos();
                break;
            case 3:
                obtenerPlataformasDeVideojuegoPorNombreVideojuego(sc);
                break;
            case 4:
                obtenerPlataformasDeVideojuegoPorNombrePlataforma(sc);
                break;
            case 5:
                actualizarVideojuegoPlataforma(sc);
                break;
            case 6:
                eliminarVideojuegoPlataforma(sc);
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
     * Inserta una nueva relación videojuego-plataforma en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private static void insertarVideojuegoPlataforma(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID del videojuego: ");
        int idVideojuego = sc.nextInt();

        System.out.print("Introduce el ID de la plataforma: ");
        int idPlataforma = sc.nextInt();

        VideojuegoPlataforma videojuegoPlataforma = new VideojuegoPlataforma(idVideojuego, idPlataforma);

        try {
            VideojuegoPlataformaServicio videojuegoPlataformaServicio = new VideojuegoPlataformaServicio();
            videojuegoPlataformaServicio.insertarVideojuegoPlataforma(videojuegoPlataforma);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene todas las plataformas de videojuegos y las muestra.
     */
    private static void obtenerTodasPlataformasVideojuegos() {
        List<VideojuegoPlataforma> plataformas = null;

        try {
            VideojuegoPlataformaServicio videojuegoPlataformaServicio = new VideojuegoPlataformaServicio();
            plataformas = videojuegoPlataformaServicio.obtenerTodos();

            System.out.println("ID Videojuego - ID Plataforma -> Nombre Videojuego - Nombre Plataforma");
            System.out.println("-------------------------------------------------");

            for (VideojuegoPlataforma p : plataformas) {
                System.out.println(p.getIdProducto() + " - " + p.getIdPlataforma() + " -> " + p.getNombreProducto() + " - " + p.getNombrePlataforma());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene las plataformas de videojuegos por nombre de videojuego y las muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private static void obtenerPlataformasDeVideojuegoPorNombreVideojuego(Scanner sc) {
        List<VideojuegoPlataforma> plataformas = null;

        sc.nextLine();

        System.out.print("Introduce el nombre del videojuego: ");
        String nombreVideojuego = sc.nextLine();

        try {
            VideojuegoPlataformaServicio videojuegoPlataformaServicio = new VideojuegoPlataformaServicio();
            plataformas = videojuegoPlataformaServicio.obtenerPorVideojuegoNombre(nombreVideojuego);

            System.out.println("Videojuego -> Plataforma");
            System.out.println("-------------------------------------------------");

            for (VideojuegoPlataforma p : plataformas) {
                System.out.println(p.getNombreProducto() + " -> " + p.getNombrePlataforma());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene las plataformas de videojuegos por nombre de plataforma y las muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private static void obtenerPlataformasDeVideojuegoPorNombrePlataforma(Scanner sc) {
        List<VideojuegoPlataforma> plataformas = null;

        sc.nextLine();

        System.out.print("Introduce el nombre de la plataforma: ");
        String nombrePlataforma = sc.nextLine();

        try {
            VideojuegoPlataformaServicio videojuegoPlataformaServicio = new VideojuegoPlataformaServicio();
            plataformas = videojuegoPlataformaServicio.obtenerPorPlataformaNombre(nombrePlataforma);

            System.out.println("Plataforma - Videojuego");
            System.out.println("-------------------------------------------------");

            for (VideojuegoPlataforma p : plataformas) {
                System.out.println(p.getNombrePlataforma() + " -> " + p.getNombreProducto());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Actualiza una relación videojuego-plataforma existente en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private static void actualizarVideojuegoPlataforma(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID del videojuego a actualizar: ");
        int idVideojuego = sc.nextInt();

        System.out.print("Introduce el ID de la plataforma a actualizar: ");
        int idPlataforma = sc.nextInt();

        System.out.println();

        System.out.print("Introduce el nuevo ID del videojuego: ");
        int nuevoIdVideojuego = sc.nextInt();

        System.out.print("Introduce el nuevo ID de la plataforma: ");
        int nuevoIdPlataforma = sc.nextInt();

        VideojuegoPlataforma videojuegoPlataforma = new VideojuegoPlataforma(idVideojuego, idPlataforma);
        videojuegoPlataforma.setIdNuevoProducto(nuevoIdVideojuego);
        videojuegoPlataforma.setIdNuevaPlataforma(nuevoIdPlataforma);

        try {
            VideojuegoPlataformaServicio videojuegoPlataformaServicio = new VideojuegoPlataformaServicio();
            videojuegoPlataformaServicio.actualizar(videojuegoPlataforma);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Elimina una relación videojuego-plataforma de la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private static void eliminarVideojuegoPlataforma(Scanner sc) {
        System.out.print("Introduce el ID del videojuego: ");
        int idVideojuego = sc.nextInt();

        System.out.print("Introduce el ID de la plataforma: ");
        int idPlataforma = sc.nextInt();

        try {
            VideojuegoPlataformaServicio videojuegoPlataformaServicio = new VideojuegoPlataformaServicio();
            videojuegoPlataformaServicio.eliminar(idVideojuego, idPlataforma);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}