package es.cheste.gestion_bd.gestion_tablas;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Plataforma;
import es.cheste.servicio.PlataformaServicio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

/**
 * Clase para gestionar las operaciones relacionadas con la tabla de plataformas.
 */
public class GestionTablaPlataforma {
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Muestra el menú de opciones para gestionar las plataformas y procesa la opción seleccionada.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    public void menuTablaPlataforma(Scanner sc) {
        int opcion;

        do {
            System.out.println("1. Insertar plataforma");
            System.out.println("2. Consultar plataforma por ID");
            System.out.println("3. Consultar todas las plataformas");
            System.out.println("4. Actualizar plataforma");
            System.out.println("5. Eliminar plataforma");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println();

            System.out.print("Introduce el numero de la accion que quieres realizar: ");
            opcion = sc.nextInt();

            gestionarMenuTablaPlataforma(opcion, sc);
        } while (opcion != 0);
    }

    /**
     * Gestiona la opción seleccionada del menú de plataformas.
     *
     * @param opcion Opción seleccionada por el usuario.
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void gestionarMenuTablaPlataforma(int opcion, Scanner sc) {
        PlataformaServicio plataformaServicio = new PlataformaServicio();

        switch (opcion) {
            case 1:
                insertarPlataforma(sc);
                break;
            case 2:
                obtenerPlataformaPorID(sc);
                break;
            case 3:
                obtenerTodasPlataformas();
                break;
            case 4:
                actualizarPlataforma(sc);
                break;
            case 5:
                eliminarPlataforma(sc);
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
     * Inserta una nueva plataforma en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void insertarPlataforma(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el nombre de la plataforma: ");
        String nombre = sc.nextLine();

        Plataforma plataforma = new Plataforma(nombre);

        try {
            PlataformaServicio plataformaServicio = new PlataformaServicio();
            plataformaServicio.insertarPlataforma(plataforma);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene una plataforma por su ID y la muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerPlataformaPorID(Scanner sc) {
        System.out.print("Introduce el ID de la plataforma: ");
        int id = sc.nextInt();

        try {
            PlataformaServicio plataformaServicio = new PlataformaServicio();
            Plataforma plataforma = plataformaServicio.obtenerPorID(id);
            System.out.println("ID -> Nombre");
            System.out.println("-------------------------------------------------");

            if (plataforma == null) {
                System.out.println("No se ha encontrado la plataforma con ID: " + id);
                return;
            }
            System.out.println(plataforma.getIdPlataforma() + " -> " + plataforma.getNombre());
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene todas las plataformas y las muestra.
     */
    private void obtenerTodasPlataformas() {
        List<Plataforma> plataformas = null;

        try {
            PlataformaServicio plataformaServicio = new PlataformaServicio();
            plataformas = plataformaServicio.obtenerTodas();

            System.out.println("ID -> Nombre");
            System.out.println("-------------------------------------------------");

            for (Plataforma p : plataformas) {
                System.out.println(p.getIdPlataforma() + " -> " + p.getNombre());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Actualiza una plataforma existente en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void actualizarPlataforma(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID de la plataforma que quieres actualizar: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Introduce el nombre de la plataforma: ");
        String nombre = sc.nextLine();

        Plataforma plataforma = new Plataforma(nombre);
        plataforma.setIdPlataforma(id);

        try {
            PlataformaServicio plataformaServicio = new PlataformaServicio();
            plataformaServicio.actualizar(plataforma);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Elimina una plataforma de la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void eliminarPlataforma(Scanner sc) {
        System.out.print("Introduce el ID de la plataforma que quieres eliminar: ");
        int id = sc.nextInt();

        try {
            PlataformaServicio plataformaServicio = new PlataformaServicio();
            plataformaServicio.eliminar(id);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}