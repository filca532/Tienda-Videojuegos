package es.cheste.gestion_bd.gestion_tablas;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Cliente;
import es.cheste.servicio.ClienteServicio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Clase para gestionar las operaciones de la tabla Cliente.
 */
public class GestionTablaCliente {
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Muestra el menú de opciones para gestionar la tabla Cliente.
     *
     * @param sc el objeto Scanner para la entrada de datos
     */
    public void menuTablaCliente(Scanner sc) {
        int opcion;

        do {
            System.out.println("1. Insertar cliente");
            System.out.println("2. Consultar cliente por ID");
            System.out.println("3. Consultar todos los clientes");
            System.out.println("4. Consultar clientes por rango de fecha");
            System.out.println("5. Actualizar cliente");
            System.out.println("6. Eliminar cliente");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println();

            System.out.print("Introduce el numero de la accion que quieres realizar: ");

            opcion = sc.nextInt();

            gestionarMenuTablaCliente(opcion, sc);
        } while (opcion != 0);
    }

    /**
     * Gestiona las opciones seleccionadas en el menú de la tabla Cliente.
     *
     * @param opcion la opción seleccionada
     * @param sc el objeto Scanner para la entrada de datos
     */
    private void gestionarMenuTablaCliente(int opcion, Scanner sc) {
        ClienteServicio clienteServicio = new ClienteServicio();
        switch (opcion) {
            case 1:
                insertarCliente(sc);
                break;
            case 2:
                obtenerClienteporID(sc);
                break;
            case 3:
                obtenerTodosClientes();
                break;
            case 4:
                obtenerClientesRangoFecha(sc);
                break;
            case 5:
                actualizarCliente(sc);
                break;
            case 6:
                eliminarCliente(sc);
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
     * Inserta un nuevo cliente en la base de datos.
     *
     * @param sc el objeto Scanner para la entrada de datos
     */
    private void insertarCliente(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el nombre del cliente: ");
        String nombre = sc.nextLine();

        System.out.print("Introduce los apellidos del cliente: ");
        String apellidos = sc.nextLine();

        System.out.print("Introduce el email del cliente: ");
        String email = sc.nextLine();

        System.out.print("Introduce el telefono del cliente: ");
        String telefono = sc.nextLine();

        System.out.print("Introduce la direccion del cliente: ");
        String direccion = sc.nextLine();

        System.out.print("Introduce la fecha de registro del cliente (Formato YYYY-MM-DD): ");
        String fechaRegistroStr = sc.nextLine();

        Date fechaRegistro = Date.valueOf(fechaRegistroStr);

        Cliente cliente = new Cliente(nombre, apellidos, email, telefono, direccion, fechaRegistro);

        try {
            ClienteServicio clienteServicio = new ClienteServicio();
            clienteServicio.insertarCliente(cliente);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene un cliente por su ID y muestra su información.
     *
     * @param sc el objeto Scanner para la entrada de datos
     */
    private void obtenerClienteporID(Scanner sc) {
        System.out.print("Introduce el ID del cliente: ");
        int id = sc.nextInt();

        try {
            ClienteServicio clienteServicio = new ClienteServicio();
            Cliente cliente = clienteServicio.obtenerPorID(id);

            System.out.println("ID -> Nombre Apellidos - Email - Telefono - Direccion - Fecha Registro");
            System.out.println("-------------------------------------------------");

            System.out.println(cliente.getIdCliente() + " -> " + cliente.getNombre() + " " + cliente.getApellidos() + " - " + cliente.getEmail() + " - " + cliente.getTelefono() + " - " + cliente.getDireccion() + " - " + cliente.getFechaRegistro());
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene todos los clientes y muestra su información.
     */
    private void obtenerTodosClientes() {
        List<Cliente> clientes = null;

        try {
            ClienteServicio clienteServicio = new ClienteServicio();
            clientes = clienteServicio.obtenerTodos();

            System.out.println("ID -> Nombre Apellidos - Email - Telefono - Direccion - Fecha Registro");
            System.out.println("-------------------------------------------------");

            for (Cliente c : clientes) {
                System.out.println(c.getIdCliente() + " -> " + c.getNombre() + " " + c.getApellidos() + " - " + c.getEmail() + " - " + c.getTelefono() + " - " + c.getDireccion() + " - " + c.getFechaRegistro());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene clientes dentro de un rango de fechas y muestra su información.
     *
     * @param sc el objeto Scanner para la entrada de datos
     */
    private void obtenerClientesRangoFecha(Scanner sc) {
        List<Cliente> clientes = null;

        System.out.print("Introduce la fecha de inicio (Formato YYYY-MM-DD): ");
        String fechaInicioStr = sc.next();

        System.out.print("Introduce la fecha de fin (Formato YYYY-MM-DD): ");
        String fechaFinStr = sc.next();

        System.out.println();

        Date fechaInicio = Date.valueOf(fechaInicioStr);
        Date fechaFin = Date.valueOf(fechaFinStr);

        System.out.println();

        try {
            ClienteServicio clienteServicio = new ClienteServicio();
            clientes = clienteServicio.obtenerClientesRangoFecha(fechaInicio, fechaFin);

            System.out.println("Fecha Inicio: " + fechaInicio + " - Fecha Fin: " + fechaFin);
            System.out.println("ID -> Nombre Apellidos - Email - Telefono - Direccion - Fecha Registro");
            System.out.println("-------------------------------------------------");

            for (Cliente c : clientes) {
                System.out.println(c.getIdCliente() + " -> " + c.getNombre() + " " + c.getApellidos() + " - " + c.getEmail() + " - " + c.getTelefono() + " - " + c.getDireccion() + " - " + c.getFechaRegistro());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Actualiza la información de un cliente existente en la base de datos.
     *
     * @param sc el objeto Scanner para la entrada de datos
     */
    private void actualizarCliente(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID del cliente que quieres actualizar: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Introduce el nombre del cliente: ");
        String nombre = sc.nextLine();

        System.out.print("Introduce los apellidos del cliente: ");
        String apellidos = sc.nextLine();

        System.out.print("Introduce el email del cliente: ");
        String email = sc.nextLine();

        System.out.print("Introduce el telefono del cliente: ");
        String telefono = sc.nextLine();

        System.out.print("Introduce la direccion del cliente: ");
        String direccion = sc.nextLine();

        System.out.print("Introduce la fecha de registro del cliente (Formato YYYY-MM-DD): ");
        String fechaRegistroStr = sc.nextLine();

        Date fechaRegistro = Date.valueOf(fechaRegistroStr);

        Cliente cliente = new Cliente(nombre, apellidos, email, telefono, direccion, fechaRegistro);
        cliente.setIdCliente(id);

        try {
            ClienteServicio clienteServicio = new ClienteServicio();
            clienteServicio.actualizarCliente(cliente);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Elimina un cliente de la base de datos por su ID.
     *
     * @param sc el objeto Scanner para la entrada de datos
     */
    private void eliminarCliente(Scanner sc) {
        System.out.print("Introduce el ID del cliente que quieres eliminar: ");
        int id = sc.nextInt();

        try {
            ClienteServicio clienteServicio = new ClienteServicio();
            clienteServicio.eliminarCliente(id);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}