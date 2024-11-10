package es.cheste.gestion_bd.gestion_tablas;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Proveedor;
import es.cheste.servicio.ProveedorServicio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

/**
 * Clase para gestionar las operaciones de la tabla Proveedor.
 */
public class GestionTablaProveedor {
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Muestra el menú de opciones para gestionar la tabla Proveedor.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    public void menuTablaProveedor(Scanner sc) {
        int opcion;

        do {
            System.out.println("1. Insertar proveedor");
            System.out.println("2. Consultar proveedor por ID");
            System.out.println("3. Consultar todos los proveedores");
            System.out.println("4. Actualizar proveedor");
            System.out.println("5. Eliminar proveedor");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println();

            System.out.print("Introduce el numero de la accion que quieres realizar: ");
            opcion = sc.nextInt();

            gestionarMenuTablaProveedor(opcion, sc);
        } while (opcion != 0);
    }

    /**
     * Gestiona la opción seleccionada del menú.
     *
     * @param opcion Opción seleccionada por el usuario.
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void gestionarMenuTablaProveedor(int opcion, Scanner sc) {
        ProveedorServicio proveedorServicio = new ProveedorServicio();

        switch (opcion) {
            case 1:
                insertarProveedor(sc);
                break;
            case 2:
                obtenerProveedorPorID(sc);
                break;
            case 3:
                obtenerTodosProveedores();
                break;
            case 4:
                actualizarProveedor(sc);
                break;
            case 5:
                eliminarProveedor(sc);
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
     * Inserta un nuevo proveedor en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void insertarProveedor(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el nombre del proveedor: ");
        String nombre = sc.nextLine();

        System.out.print("Introduce la direccion del proveedor: ");
        String direccion = sc.nextLine();

        System.out.print("Introduce el telefono del proveedor: ");
        String telefono = sc.nextLine();

        System.out.print("Introduce el email del proveedor: ");
        String email = sc.nextLine();

        System.out.print("Introduce el pais del proveedor: ");
        String pais = sc.nextLine();

        Proveedor proveedor = new Proveedor(nombre, direccion, telefono, email, pais);

        try {
            ProveedorServicio proveedorServicio = new ProveedorServicio();
            proveedorServicio.insertarProveedor(proveedor);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene un proveedor por su ID y lo muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerProveedorPorID(Scanner sc) {
        System.out.print("Introduce el ID del proveedor: ");
        int id = sc.nextInt();

        try {
            ProveedorServicio proveedorServicio = new ProveedorServicio();
            Proveedor proveedor = proveedorServicio.obtenerPorID(id);
            System.out.println("ID -> Nombre - Direccion - Telefono - Email - Pais");
            System.out.println("-------------------------------------------------");

            if (proveedor == null) {
                System.out.println("No se ha encontrado el proveedor con ID: " + id);
                return;
            }
            System.out.println(proveedor.getIdProveedor() + " -> " + proveedor.getNombre() + " - " + proveedor.getDireccion() + " - " + proveedor.getTelefono() + " - " + proveedor.getEmail() + " - " + proveedor.getPais());
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene y muestra todos los proveedores.
     */
    private void obtenerTodosProveedores() {
        List<Proveedor> proveedores = null;

        try {
            ProveedorServicio proveedorServicio = new ProveedorServicio();
            proveedores = proveedorServicio.obtenerTodos();

            System.out.println("ID -> Nombre - Direccion - Telefono - Email - Pais");
            System.out.println("-------------------------------------------------");

            for (Proveedor p : proveedores) {
                System.out.println(p.getIdProveedor() + " -> " + p.getNombre() + " - " + p.getDireccion() + " - " + p.getTelefono() + " - " + p.getEmail() + " - " + p.getPais());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Actualiza un proveedor existente en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void actualizarProveedor(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID del proveedor que quieres actualizar: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Introduce el nombre del proveedor: ");
        String nombre = sc.nextLine();

        System.out.print("Introduce la direccion del proveedor: ");
        String direccion = sc.nextLine();

        System.out.print("Introduce el telefono del proveedor: ");
        String telefono = sc.nextLine();

        System.out.print("Introduce el email del proveedor: ");
        String email = sc.nextLine();

        System.out.print("Introduce el pais del proveedor: ");
        String pais = sc.nextLine();

        Proveedor proveedor = new Proveedor(nombre, direccion, telefono, email, pais);
        proveedor.setIdProveedor(id);

        try {
            ProveedorServicio proveedorServicio = new ProveedorServicio();
            proveedorServicio.actualizar(proveedor);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Elimina un proveedor de la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void eliminarProveedor(Scanner sc) {
        System.out.print("Introduce el ID del proveedor que quieres eliminar: ");
        int id = sc.nextInt();

        try {
            ProveedorServicio proveedorServicio = new ProveedorServicio();
            proveedorServicio.eliminar(id);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}