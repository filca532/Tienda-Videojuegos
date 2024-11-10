package es.cheste.gestion_bd.gestion_tablas;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Videojuego;
import es.cheste.servicio.VideojuegoServicio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Clase para gestionar las operaciones relacionadas con la tabla de videojuegos.
 */
public class GestionTablaVideojuego {
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Muestra el menú de opciones para gestionar los videojuegos y procesa la opción seleccionada.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    public void menuTablaVideojuego(Scanner sc) {
        int opcion;

        do {
            System.out.println("1. Insertar videojuego");
            System.out.println("2. Consultar videojuego por ID");
            System.out.println("3. Consultar todos los videojuegos");
            System.out.println("4. Consultar videojuegos por clasificacion");
            System.out.println("5. Consultar videojuegos por rango de fecha");
            System.out.println("6. Actualizar videojuego");
            System.out.println("7. Eliminar videojuego");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println();

            System.out.print("Introduce el numero de la accion que quieres realizar: ");
            opcion = sc.nextInt();

            gestionarMenuTablaVideojuego(opcion, sc);
        } while (opcion != 0);
    }

    /**
     * Gestiona la opción seleccionada del menú de videojuegos.
     *
     * @param opcion Opción seleccionada por el usuario.
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void gestionarMenuTablaVideojuego(int opcion, Scanner sc) {
        VideojuegoServicio videojuegoServicio = new VideojuegoServicio();

        switch (opcion) {
            case 1:
                insertarVideojuego(sc);
                break;
            case 2:
                obtenerVideojuegoPorID(sc);
                break;
            case 3:
                obtenerTodosVideojuegos();
                break;
            case 4:
                obtenerVideojuegosPorClasificacion(sc);
                break;
            case 5:
                obtenerVideojuegosPorRangoFecha(sc);
                break;
            case 6:
                actualizarVideojuego(sc);
                break;
            case 7:
                eliminarVideojuego(sc);
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
     * Inserta un nuevo videojuego en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void insertarVideojuego(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID del producto: ");
        int idProducto = sc.nextInt();

        sc.nextLine();

        System.out.print("Introduce la fecha de lanzamiento del videojuego (YYYY-MM-DD): ");
        String fechaLanzamiento = sc.nextLine();

        Date fechaLanzamientoDate = Date.valueOf(fechaLanzamiento);

        System.out.print("Introduce la clasificacion por edad del videojuego (edad+): ");
        String clasificacionEdad = sc.nextLine();

        System.out.print("Introduce el ID del proveedor: ");
        int idProveedor = sc.nextInt();

        Videojuego videojuego = new Videojuego(idProducto, fechaLanzamientoDate, clasificacionEdad, idProveedor);

        try {
            VideojuegoServicio videojuegoServicio = new VideojuegoServicio();
            videojuegoServicio.insertarVideojuego(videojuego);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene un videojuego por su ID y lo muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerVideojuegoPorID(Scanner sc) {
        System.out.print("Introduce el ID del videojuego: ");
        int id = sc.nextInt();

        try {
            VideojuegoServicio videojuegoServicio = new VideojuegoServicio();
            Videojuego videojuego = videojuegoServicio.obtenerPorID(id);
            System.out.println("ID Producto -> Fecha Lanzamiento - Clasificacion Edad - ID Proveedor");
            System.out.println("-------------------------------------------------");

            if (videojuego == null) {
                System.out.println("No se ha encontrado el videojuego con ID: " + id);
                return;
            }
            System.out.println(videojuego.getIdProducto() + " -> " + videojuego.getFechaLanzamiento() + " - " + videojuego.getClasificacionEdad() + " - " + videojuego.getProveedor());
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene todos los videojuegos y los muestra.
     */
    private void obtenerTodosVideojuegos() {
        List<Videojuego> videojuegos = null;

        try {
            VideojuegoServicio videojuegoServicio = new VideojuegoServicio();
            videojuegos = videojuegoServicio.obtenerTodos();

            System.out.println("ID Producto -> Fecha Lanzamiento - Clasificacion Edad - ID Proveedor");
            System.out.println("-------------------------------------------------");

            for (Videojuego v : videojuegos) {
                System.out.println(v.getIdProducto() + " -> " + v.getFechaLanzamiento() + " - " + v.getClasificacionEdad() + " - " + v.getProveedor());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene los videojuegos por clasificación y los muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerVideojuegosPorClasificacion(Scanner sc) {
        List<Videojuego> videojuegos = null;

        System.out.print("Introduce la clasificacion por edad del videojuego: ");
        String clasificacionEdad = sc.next();

        try {
            VideojuegoServicio videojuegoServicio = new VideojuegoServicio();
            videojuegos = videojuegoServicio.obtenerPorClasificacion(clasificacionEdad);

            System.out.println("ID Producto -> Fecha Lanzamiento - Clasificacion Edad - ID Proveedor");
            System.out.println("-------------------------------------------------");

            for (Videojuego v : videojuegos) {
                System.out.println(v.getIdProducto() + " -> " + v.getFechaLanzamiento() + " - " + v.getClasificacionEdad() + " - " + v.getProveedor());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene los videojuegos por rango de fecha y los muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerVideojuegosPorRangoFecha(Scanner sc) {
        List<Videojuego> videojuegos = null;

        System.out.print("Introduce la fecha minima de lanzamiento del videojuego (YYYY-MM-DD): ");
        String fechaMin = sc.next();

        Date fechaMinDate = Date.valueOf(fechaMin);

        System.out.print("Introduce la fecha maxima de lanzamiento del videojuego (YYYY-MM-DD): ");
        String fechaMax = sc.next();

        Date fechaMaxDate = Date.valueOf(fechaMax);

        try {
            VideojuegoServicio videojuegoServicio = new VideojuegoServicio();
            videojuegos = videojuegoServicio.obtenerPorRangoFecha(fechaMinDate, fechaMaxDate);

            System.out.println("ID Producto -> Fecha Lanzamiento - Clasificacion Edad - ID Proveedor");
            System.out.println("-------------------------------------------------");

            for (Videojuego v : videojuegos) {
                System.out.println(v.getIdProducto() + " -> " + v.getFechaLanzamiento() + " - " + v.getClasificacionEdad() + " - " + v.getProveedor());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Actualiza un videojuego existente en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void actualizarVideojuego(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID del producto: ");
        int idProducto = sc.nextInt();

        sc.nextLine();

        System.out.print("Introduce la fecha de lanzamiento del videojuego (YYYY-MM-DD): ");
        String fechaLanzamiento = sc.nextLine();

        Date fechaLanzamientoDate = Date.valueOf(fechaLanzamiento);

        System.out.print("Introduce la clasificacion por edad del videojuego (edad+): ");
        String clasificacionEdad = sc.nextLine();

        System.out.print("Introduce el ID del proveedor: ");
        int idProveedor = sc.nextInt();

        Videojuego videojuego = new Videojuego(idProducto, fechaLanzamientoDate, clasificacionEdad, idProveedor);
        videojuego.setIdProducto(idProducto);

        try {
            VideojuegoServicio videojuegoServicio = new VideojuegoServicio();
            videojuegoServicio.actualizar(videojuego);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Elimina un videojuego de la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void eliminarVideojuego(Scanner sc) {
        System.out.print("Introduce el ID del videojuego que quieres eliminar: ");
        int id = sc.nextInt();

        try {
            VideojuegoServicio videojuegoServicio = new VideojuegoServicio();
            videojuegoServicio.eliminar(id);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}