package es.cheste.gestion_bd.gestion_tablas;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Miembro;
import es.cheste.servicio.MiembroServicio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Clase para gestionar las operaciones relacionadas con la tabla de miembros.
 */
public class GestionTablaMiembro {
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Muestra el menú de opciones para gestionar los miembros y procesa la opción seleccionada.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    public void menuTablaMiembro(Scanner sc) {
        int opcion;

        do {
            System.out.println("1. Insertar miembro");
            System.out.println("2. Consultar miembro por ID");
            System.out.println("3. Consultar todos los miembros");
            System.out.println("4. Consultar miembros por tipo de membresia");
            System.out.println("5. Consultar miembros por nivel de membresia");
            System.out.println("6. Actualizar miembro");
            System.out.println("7. Eliminar miembro");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println();

            System.out.print("Introduce el numero de la accion que quieres realizar: ");
            opcion = sc.nextInt();

            gestionarMenuTablaMiembro(opcion, sc);
        } while (opcion != 0);
    }

    /**
     * Gestiona la opción seleccionada del menú de miembros.
     *
     * @param opcion Opción seleccionada por el usuario.
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void gestionarMenuTablaMiembro(int opcion, Scanner sc) {
        MiembroServicio miembroServicio = new MiembroServicio();

        switch (opcion) {
            case 1:
                insertarMiembro(sc);
                break;
            case 2:
                obtenerMiembroPorID(sc);
                break;
            case 3:
                obtenerTodosMiembros();
                break;
            case 4:
                obtenerMiembrosPorTipoMembresia(sc);
                break;
            case 5:
                obtenerMiembrosPorNivelMembresia(sc);
                break;
            case 6:
                actualizarMiembro(sc);
                break;
            case 7:
                eliminarMiembro(sc);
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
     * Inserta un nuevo miembro en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void insertarMiembro(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID del cliente: ");
        int idCliente = sc.nextInt();

        System.out.println("Introduce el tipo de miembro: ");
        String tipoMiembro = escogerTipoMiembro(sc);

        System.out.println("Introduce el nivel de membresia: ");
        String nivelMembresia = escogerNivelMembresia(sc);

        sc.nextLine();

        System.out.print("Introduce la fecha de ingreso (Formato YYYY-MM-DD): ");
        String fechaIngresoStr = sc.nextLine();

        Date fechaIngreso = Date.valueOf(fechaIngresoStr);

        Miembro miembro = new Miembro(idCliente, tipoMiembro, nivelMembresia, fechaIngreso);

        try {
            MiembroServicio miembroServicio = new MiembroServicio();
            miembroServicio.insertarMiembro(miembro);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Muestra un menú para seleccionar el tipo de miembro y devuelve el tipo seleccionado.
     *
     * @param sc Scanner para leer la entrada del usuario.
     * @return Tipo de miembro seleccionado.
     */
    private String escogerTipoMiembro(Scanner sc) {
        System.out.println("1. Estándar");
        System.out.println("2. Premium");
        System.out.println("3. VIP");
        System.out.println();
        System.out.print("Introduce el numero del tipo de miembro: ");

        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                return "Estándar";
            case 2:
                return "Premium";
            case 3:
                return "VIP";
            default:
                return "Estandar";
        }
    }

    /**
     * Muestra un menú para seleccionar el nivel de membresía y devuelve el nivel seleccionado.
     *
     * @param sc Scanner para leer la entrada del usuario.
     * @return Nivel de membresía seleccionado.
     */
    private String escogerNivelMembresia(Scanner sc) {
        System.out.println("1. Bronce");
        System.out.println("2. Plata");
        System.out.println("3. Oro");
        System.out.println("4. Platino");
        System.out.println("5. Diamante");
        System.out.println();
        System.out.print("Introduce el numero del nivel de membresia: ");

        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                return "Bronce";
            case 2:
                return "Plata";
            case 3:
                return "Oro";
            case 4:
                return "Platino";
            case 5:
                return "Diamante";
            default:
                return "Bronce";
        }
    }

    /**
     * Obtiene un miembro por su ID y lo muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerMiembroPorID(Scanner sc) {
        System.out.print("Introduce el ID del miembro: ");
        int id = sc.nextInt();

        try {
            MiembroServicio miembroServicio = new MiembroServicio();
            Miembro miembro = miembroServicio.obtenerPorID(id);
            System.out.println("ID -> ID Cliente - Nombre - Tipo Miembro - Nivel Membresia - Fecha Ingreso");
            System.out.println("-------------------------------------------------");

            if (miembro == null) {
                System.out.println("No se ha encontrado el miembro con ID: " + id);
                return;
            }
            System.out.println(miembro.getIdMiembro() + " -> " + miembro.getIdCliente() + " - " + miembro.getNombreCliente() + " - " + miembro.getTipoMiembro() + " - " + miembro.getNivelMembresia() + " - " + miembro.getFechaIngreso());
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene todos los miembros y los muestra.
     */
    private void obtenerTodosMiembros() {
        List<Miembro> miembros = null;

        try {
            MiembroServicio miembroServicio = new MiembroServicio();
            miembros = miembroServicio.obtenerTodos();

            System.out.println("ID -> ID Cliente - Nombre - Tipo Miembro - Nivel Membresia - Fecha Ingreso");
            System.out.println("-------------------------------------------------");

            for (Miembro m : miembros) {
                System.out.println(m.getIdMiembro() + " -> " + m.getIdCliente() + " - " + m.getNombreCliente() + " - " + m.getTipoMiembro() + " - " + m.getNivelMembresia() + " - " + m.getFechaIngreso());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene los miembros por tipo de membresía y los muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerMiembrosPorTipoMembresia(Scanner sc) {
        List<Miembro> miembros = null;

        System.out.print("Introduce el tipo de membresia: ");
        String tipoMembresia = escogerTipoMiembro(sc);

        try {
            MiembroServicio miembroServicio = new MiembroServicio();
            miembros = miembroServicio.obtenerPorTipoMembresia(tipoMembresia);

            System.out.println("ID -> ID Cliente - Nombre - Tipo Miembro - Nivel Membresia - Fecha Ingreso");
            System.out.println("-------------------------------------------------");

            for (Miembro m : miembros) {
                System.out.println(m.getIdMiembro() + " -> " + m.getIdCliente() + " - " + m.getNombreCliente() + " - " + m.getTipoMiembro() + " - " + m.getNivelMembresia() + " - " + m.getFechaIngreso());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene los miembros por nivel de membresía y los muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerMiembrosPorNivelMembresia(Scanner sc) {
        List<Miembro> miembros = null;

        System.out.print("Introduce el nivel de membresia: ");
        String nivelMembresia = escogerNivelMembresia(sc);

        try {
            MiembroServicio miembroServicio = new MiembroServicio();
            miembros = miembroServicio.obtenerPorNivelMembresia(nivelMembresia);

            System.out.println("ID -> ID Cliente - Nombre - Tipo Miembro - Nivel Membresia - Fecha Ingreso");
            System.out.println("-------------------------------------------------");

            for (Miembro m : miembros) {
                System.out.println(m.getIdMiembro() + " -> " + m.getIdCliente() + " - " + m.getNombreCliente() + " - " + m.getTipoMiembro() + " - " + m.getNivelMembresia() + " - " + m.getFechaIngreso());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Actualiza un miembro existente en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void actualizarMiembro(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID del miembro que quieres actualizar: ");
        int id = sc.nextInt();

        System.out.print("Introduce el ID del cliente: ");
        int idCliente = sc.nextInt();

        sc.nextLine();

        System.out.println("Introduce el tipo de miembro: ");
        String tipoMiembro = escogerTipoMiembro(sc);

        System.out.println("Introduce el nivel de membresia: ");
        String nivelMembresia = escogerNivelMembresia(sc);

        sc.nextLine();

        System.out.print("Introduce la fecha de ingreso (Formato YYYY-MM-DD): ");
        String fechaIngresoStr = sc.nextLine();

        Date fechaIngreso = Date.valueOf(fechaIngresoStr);

        Miembro miembro = new Miembro(idCliente, tipoMiembro, nivelMembresia, fechaIngreso);
        miembro.setIdMiembro(id);

        try {
            MiembroServicio miembroServicio = new MiembroServicio();
            miembroServicio.actualizarMiembro(miembro);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Elimina un miembro de la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void eliminarMiembro(Scanner sc) {
        System.out.print("Introduce el ID del miembro que quieres eliminar: ");
        int id = sc.nextInt();

        try {
            MiembroServicio miembroServicio = new MiembroServicio();
            miembroServicio.eliminarMiembro(id);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}