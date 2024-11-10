package es.cheste.gestion_bd.gestion_tablas;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Empleado;
import es.cheste.servicio.EmpleadoServicio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 * La clase GestionTablaEmpleado permite gestionar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * en la tabla de empleados de una base de datos a través de un menú interactivo.
 */
public class GestionTablaEmpleado {
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Muestra un menú interactivo para realizar diferentes operaciones en la tabla de empleados
     * y gestiona la opción seleccionada por el usuario.
     *
     * @param sc Scanner para capturar la entrada del usuario.
     */
    public void menuTablaEmpleado(Scanner sc) {
        int opcion;

        do {
            System.out.println("1. Insertar empleado");
            System.out.println("2. Consultar empleado por ID");
            System.out.println("3. Consultar todos los empleados");
            System.out.println("4. Consultar empleados por puesto");
            System.out.println("5. Consultar empleados por rango de salario");
            System.out.println("6. Consultar empleados por fecha de contratacion");
            System.out.println("7. Actualizar empleado");
            System.out.println("8. Eliminar empleado");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println();

            System.out.print("Introduce el numero de la accion que quieres realizar: ");
            opcion = sc.nextInt();

            gestionarMenuTablaEmpleado(opcion, sc);
        } while (opcion != 0);
    }

    /**
     * Gestiona las opciones del menú seleccionadas por el usuario y llama al método correspondiente.
     *
     * @param opcion La opción seleccionada del menú.
     * @param sc Scanner para capturar la entrada del usuario.
     */
    private void gestionarMenuTablaEmpleado(int opcion, Scanner sc) {
        switch (opcion) {
            case 1:
                insertarEmpleado(sc);
                break;
            case 2:
                obtenerEmpleadoPorID(sc);
                break;
            case 3:
                obtenerTodosEmpleados();
                break;
            case 4:
                obtenerEmpleadosPorPuesto(sc);
                break;
            case 5:
                obtenerEmpleadosPorSalario(sc);
                break;
            case 6:
                obtenerEmpleadosPorFechaContratacion(sc);
                break;
            case 7:
                actualizarEmpleado(sc);
                break;
            case 8:
                eliminarEmpleado(sc);
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
     * Inserta un nuevo empleado en la base de datos con los datos introducidos por el usuario.
     *
     * @param sc Scanner para capturar la entrada del usuario.
     */
    private void insertarEmpleado(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el nombre del empleado: ");
        String nombre = sc.nextLine();

        System.out.print("Introduce los apellidos del empleado: ");
        String apellidos = sc.nextLine();

        System.out.print("Introduce el email del empleado: ");
        String email = sc.nextLine();

        System.out.print("Introduce el telefono del empleado: ");
        String telefono = sc.nextLine();

        System.out.println("Escoge el puesto del empleado: ");
        String puesto = escogerPuesto(sc);

        System.out.print("Introduce el salario del empleado: ");
        double salario = sc.nextDouble();

        sc.nextLine();

        System.out.print("Introduce la fecha de contratacion del empleado (Formato YYYY-MM-DD): ");
        String fechaContratacionStr = sc.nextLine();

        Date fechaContratacion = Date.valueOf(fechaContratacionStr);

        System.out.println("Introduce el descuento del empleado : ");
        double descuento = sc.nextDouble();

        Empleado empleado = new Empleado(nombre, apellidos, email, telefono, puesto, salario, fechaContratacion, descuento);

        try {
            EmpleadoServicio empleadoServicio = new EmpleadoServicio();
            empleadoServicio.insertarEmpleado(empleado);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Muestra una lista de opciones de puesto de trabajo y devuelve el puesto seleccionado.
     *
     * @param sc Scanner para capturar la entrada del usuario.
     * @return El puesto seleccionado como cadena de texto.
     */
    private String escogerPuesto(Scanner sc) {
        System.out.println("1. Gerente");
        System.out.println("2. Vendedor");
        System.out.println("3. Atención al cliente");
        System.out.println("4. Repartidor");
        System.out.println();
        System.out.print("Introduce el numero del puesto: ");

        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                return "Gerente";
            case 2:
                return "Vendedor";
            case 3:
                return "Atención al cliente";
            case 4:
                return "Repartidor";
            default:
                return "Vendedor";
        }
    }

    /**
     * Consulta y muestra la información de un empleado en función del ID proporcionado por el usuario.
     *
     * @param sc Scanner para capturar la entrada del usuario.
     */
    private void obtenerEmpleadoPorID(Scanner sc) {
        System.out.print("Introduce el ID del empleado: ");
        int id = sc.nextInt();

        try {
            EmpleadoServicio empleadoServicio = new EmpleadoServicio();
            Empleado empleado = empleadoServicio.obtenerPorID(id);
            System.out.println("ID -> Nombre Apellidos - Email - Telefono - Puesto - Salario - Fecha Contratacion - Descuento");
            System.out.println("-------------------------------------------------");

            if (empleado == null) {
                System.out.println("No se ha encontrado el empleado con ID: " + id);
                return;
            }
            System.out.println(empleado.getIdEmpleado() + " -> " + empleado.getNombre() + " " + empleado.getApellidos() + " - " + empleado.getEmail() + " - " + empleado.getTelefono() + " - " + empleado.getPuesto() + " - " + empleado.getSalario() + " - " + empleado.getFechaContratacion() + " - " + empleado.getDescuentoEmpleado());
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene y muestra la información de todos los empleados almacenados en la base de datos.
     */
    private void obtenerTodosEmpleados() {
        List<Empleado> empleados = null;

        try {
            EmpleadoServicio empleadoServicio = new EmpleadoServicio();
            empleados = empleadoServicio.obtenerTodos();

            System.out.println("ID -> Nombre Apellidos - Email - Telefono - Puesto - Salario - Fecha Contratacion - Descuento");
            System.out.println("-------------------------------------------------");

            for (Empleado e : empleados) {
                System.out.println(e.getIdEmpleado() + " -> " + e.getNombre() + " " + e.getApellidos() + " - " + e.getEmail() + " - " + e.getTelefono() + " - " + e.getPuesto() + " - " + e.getSalario() + " - " + e.getFechaContratacion() + " - " + e.getDescuentoEmpleado());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void obtenerEmpleadosPorPuesto(Scanner sc) {
        List<Empleado> empleados = null;

        System.out.print("Introduce el puesto del empleado: ");
        String puesto = escogerPuesto(sc);

        try {
            EmpleadoServicio empleadoServicio = new EmpleadoServicio();
            empleados = empleadoServicio.obtenerEmpleadosPorPuesto(puesto);

            System.out.println("ID -> Nombre Apellidos - Email - Telefono - Puesto - Salario - Fecha Contratacion - Descuento");
            System.out.println("-------------------------------------------------");

            for (Empleado e : empleados) {
                System.out.println(e.getIdEmpleado() + " -> " + e.getNombre() + " " + e.getApellidos() + " - " + e.getEmail() + " - " + e.getTelefono() + " - " + e.getPuesto() + " - " + e.getSalario() + " - " + e.getFechaContratacion() + " - " + e.getDescuentoEmpleado());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Consulta y muestra una lista de empleados cuyo salario se encuentra en el rango especificado por el usuario.
     *
     * @param sc Scanner para capturar la entrada del usuario.
     */
    private void obtenerEmpleadosPorSalario(Scanner sc) {
        List<Empleado> empleados = null;

        System.out.print("Introduce el rango inicial del salario: ");
        double salarioInicial = sc.nextDouble();

        System.out.print("Introduce el rango final del salario: ");
        double salarioFinal = sc.nextDouble();

        try {
            EmpleadoServicio empleadoServicio = new EmpleadoServicio();
            empleados = empleadoServicio.obtenerEmpleadosRangoSalario(salarioInicial, salarioFinal);

            System.out.println("ID -> Nombre Apellidos - Email - Telefono - Puesto - Salario - Fecha Contratacion - Descuento");
            System.out.println("-------------------------------------------------");

            for (Empleado e : empleados) {
                System.out.println(e.getIdEmpleado() + " -> " + e.getNombre() + " " + e.getApellidos() + " - " + e.getEmail() + " - " + e.getTelefono() + " - " + e.getPuesto() + " - " + e.getSalario() + " - " + e.getFechaContratacion() + " - " + e.getDescuentoEmpleado());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Consulta y muestra una lista de empleados cuya fecha de contratación está en el rango especificado por el usuario.
     *
     * @param sc Scanner para capturar la entrada del usuario.
     */
    private void obtenerEmpleadosPorFechaContratacion(Scanner sc) {
        List<Empleado> empleados = null;

        System.out.print("Introduce la fecha de inicio (Formato YYYY-MM-DD): ");
        String fechaInicioStr = sc.next();

        System.out.print("Introduce la fecha de fin (Formato YYYY-MM-DD): ");
        String fechaFinStr = sc.next();

        Date fechaInicio = Date.valueOf(fechaInicioStr);
        Date fechaFin = Date.valueOf(fechaFinStr);

        try {
            EmpleadoServicio empleadoServicio = new EmpleadoServicio();
            empleados = empleadoServicio.obtenerEmpleadosPorFechaContratacion(fechaInicio, fechaFin);

            System.out.println("Fecha Inicio: " + fechaInicio + " - Fecha Fin: " + fechaFin);
            System.out.println("ID -> Nombre Apellidos - Email - Telefono - Puesto - Salario - Fecha Contratacion - Descuento");
            System.out.println("-------------------------------------------------");

            for (Empleado e : empleados) {
                System.out.println(e.getIdEmpleado() + " -> " + e.getNombre() + " " + e.getApellidos() + " - " + e.getEmail() + " - " + e.getTelefono() + " - " + e.getPuesto() + " - " + e.getSalario() + " - " + e.getFechaContratacion() + " - " + e.getDescuentoEmpleado());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Actualiza la información de un empleado existente en la base de datos con los datos proporcionados por el usuario.
     *
     * @param sc Scanner para capturar la entrada del usuario.
     */
    private void actualizarEmpleado(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID del empleado que quieres actualizar: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Introduce el nombre del empleado: ");
        String nombre = sc.nextLine();

        System.out.print("Introduce los apellidos del empleado: ");
        String apellidos = sc.nextLine();

        System.out.print("Introduce el email del empleado: ");
        String email = sc.nextLine();

        System.out.print("Introduce el telefono del empleado: ");
        String telefono = sc.nextLine();

        System.out.println("Escoge el puesto del empleado: ");
        String puesto = escogerPuesto(sc);

        System.out.print("Introduce el salario del empleado: ");
        double salario = sc.nextDouble();

        sc.nextLine();

        System.out.print("Introduce la fecha de contratacion del empleado (Formato YYYY-MM-DD): ");
        String fechaContratacionStr = sc.nextLine();

        Date fechaContratacion = Date.valueOf(fechaContratacionStr);

        System.out.print("Introduce el descuento del empleado: ");
        double descuento = sc.nextDouble();

        Empleado empleado = new Empleado(nombre, apellidos, email, telefono, puesto, salario, fechaContratacion, descuento);
        empleado.setIdEmpleado(id);

        try {
            EmpleadoServicio empleadoServicio = new EmpleadoServicio();
            empleadoServicio.actualizarEmpleado(empleado);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Elimina un empleado de la base de datos en función del ID proporcionado por el usuario.
     *
     * @param sc Scanner para capturar la entrada del usuario.
     */
    private void eliminarEmpleado(Scanner sc) {
        System.out.print("Introduce el ID del empleado que quieres eliminar: ");
        int id = sc.nextInt();

        try {
            EmpleadoServicio empleadoServicio = new EmpleadoServicio();
            empleadoServicio.eliminarEmpleado(id);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
