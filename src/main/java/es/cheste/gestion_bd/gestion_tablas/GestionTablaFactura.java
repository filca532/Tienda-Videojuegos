package es.cheste.gestion_bd.gestion_tablas;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Factura;
import es.cheste.servicio.FacturaServicio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Clase para gestionar las operaciones relacionadas con la tabla de facturas.
 */
public class GestionTablaFactura {
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Muestra el menú de opciones para gestionar las facturas y procesa la opción seleccionada.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    public void menuTablaFactura(Scanner sc) {
        int opcion;

        do {
            System.out.println("1. Insertar factura");
            System.out.println("2. Consultar factura por ID");
            System.out.println("3. Consultar todas las facturas");
            System.out.println("4. Consultar facturas por cliente");
            System.out.println("5. Consultar facturas por rango de fecha");
            System.out.println("6. Consultar facturas por metodo de pago");
            System.out.println("7. Consultar facturas por rango de monto");
            System.out.println("8. Actualizar factura");
            System.out.println("9. Eliminar factura");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println();

            System.out.print("Introduce el numero de la accion que quieres realizar: ");
            opcion = sc.nextInt();

            gestionarMenuTablaFactura(opcion, sc);
        } while (opcion != 0);
    }

    /**
     * Gestiona la opción seleccionada del menú de facturas.
     *
     * @param opcion Opción seleccionada por el usuario.
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void gestionarMenuTablaFactura(int opcion, Scanner sc) {
        switch (opcion) {
            case 1:
                insertarFactura(sc);
                break;
            case 2:
                obtenerFacturaPorID(sc);
                break;
            case 3:
                obtenerTodasFacturas();
                break;
            case 4:
                obtenerFacturasPorCliente(sc);
                break;
            case 5:
                obtenerFacturasPorRangoFecha(sc);
                break;
            case 6:
                obtenerFacturasPorMetodoPago(sc);
                break;
            case 7:
                obtenerFacturasPorRangoMonto(sc);
                break;
            case 8:
                actualizarFactura(sc);
                break;
            case 9:
                eliminarFactura(sc);
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
     * Inserta una nueva factura en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void insertarFactura(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID del cliente: ");
        int idCliente = sc.nextInt();

        System.out.print("Introduce el ID del empleado: ");
        int idEmpleado = sc.nextInt();

        sc.nextLine();

        System.out.print("Introduce la fecha de la factura (Formato YYYY-MM-DD): ");
        String fechaFacturaStr = sc.nextLine();

        Date fechaFactura = Date.valueOf(fechaFacturaStr);

        System.out.print("Introduce el monto de la factura: ");
        double monto = sc.nextDouble();

        System.out.println("Introduce el metodo de pago: ");
        String metodoPago = escogerMetodoPago(sc);

        Factura factura = new Factura(idCliente, idEmpleado, fechaFactura, monto, metodoPago);

        try {
            FacturaServicio facturaServicio = new FacturaServicio();
            facturaServicio.insertarFactura(factura);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene una factura por su ID y la muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerFacturaPorID(Scanner sc) {
        System.out.print("Introduce el ID de la factura: ");
        int id = sc.nextInt();

        try {
            FacturaServicio facturaServicio = new FacturaServicio();
            Factura factura = facturaServicio.obtenerPorID(id);
            System.out.println("ID -> ID Cliente - ID Empleado - Fecha Factura - Monto - Metodo Pago");
            System.out.println("-------------------------------------------------");

            if (factura == null) {
                System.out.println("No se ha encontrado la factura con ID: " + id);
                return;
            }
            System.out.println(factura.getIdFactura() + " -> " + factura.getIdCliente() + " - " + factura.getIdEmpleado() + " - " + factura.getFechaPedido() + " - " + factura.getTotalPedido() + " - " + factura.getMetodoPago());
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene todas las facturas y las muestra.
     */
    private void obtenerTodasFacturas() {
        List<Factura> facturas = null;

        try {
            FacturaServicio facturaServicio = new FacturaServicio();
            facturas = facturaServicio.obtenerTodas();

            System.out.println("ID -> ID Cliente - ID Empleado - Fecha Factura - Monto - Metodo Pago");
            System.out.println("-------------------------------------------------");

            for (Factura f : facturas) {
                System.out.println(f.getIdFactura() + " -> " + f.getIdCliente() + " - " + f.getIdEmpleado() + " - " + f.getFechaPedido() + " - " + f.getTotalPedido() + " - " + f.getMetodoPago());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene las facturas de un cliente específico y las muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerFacturasPorCliente(Scanner sc) {
        List<Factura> facturas = null;

        System.out.print("Introduce el ID del cliente: ");
        int idCliente = sc.nextInt();

        try {
            FacturaServicio facturaServicio = new FacturaServicio();
            facturas = facturaServicio.obtenerFacturasPorCliente(idCliente);

            System.out.println("ID -> ID Cliente - ID Empleado - Fecha Factura - Monto - Metodo Pago");
            System.out.println("-------------------------------------------------");

            for (Factura f : facturas) {
                System.out.println(f.getIdFactura() + " -> " + f.getIdCliente() + " - " + f.getIdEmpleado() + " - " + f.getFechaPedido() + " - " + f.getTotalPedido() + " - " + f.getMetodoPago());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene las facturas dentro de un rango de fechas y las muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerFacturasPorRangoFecha(Scanner sc) {
        List<Factura> facturas = null;

        System.out.print("Introduce la fecha de inicio (Formato YYYY-MM-DD): ");
        String fechaInicioStr = sc.next();

        System.out.print("Introduce la fecha de fin (Formato YYYY-MM-DD): ");
        String fechaFinStr = sc.next();

        System.out.println();

        Date fechaInicio = Date.valueOf(fechaInicioStr);
        Date fechaFin = Date.valueOf(fechaFinStr);

        System.out.println();

        try {
            FacturaServicio facturaServicio = new FacturaServicio();
            facturas = facturaServicio.obtenerFacturasPorRangoFecha(fechaInicio, fechaFin);

            System.out.println("Fecha Inicio: " + fechaInicio + " - Fecha Fin: " + fechaFin);
            System.out.println("ID -> ID Cliente - ID Empleado - Fecha Factura - Monto - Metodo Pago");
            System.out.println("-------------------------------------------------");

            for (Factura f : facturas) {
                System.out.println(f.getIdFactura() + " -> " + f.getIdCliente() + " - " + f.getIdEmpleado() + " - " + f.getFechaPedido() + " - " + f.getTotalPedido() + " - " + f.getMetodoPago());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene las facturas por método de pago y las muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerFacturasPorMetodoPago(Scanner sc) {
        List<Factura> facturas = null;

        System.out.print("Introduce el metodo de pago: ");
        String metodoPago = escogerMetodoPago(sc);

        try {
            FacturaServicio facturaServicio = new FacturaServicio();
            facturas = facturaServicio.obtenerFacturasPorMetodoPago(metodoPago);

            System.out.println("ID -> ID Cliente - ID Empleado - Fecha Factura - Monto - Metodo Pago");
            System.out.println("-------------------------------------------------");

            for (Factura f : facturas) {
                System.out.println(f.getIdFactura() + " -> " + f.getIdCliente() + " - " + f.getIdEmpleado() + " - " + f.getFechaPedido() + " - " + f.getTotalPedido() + " - " + f.getMetodoPago());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene las facturas dentro de un rango de montos y las muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerFacturasPorRangoMonto(Scanner sc) {
        List<Factura> facturas = null;

        System.out.print("Introduce el rango inicial del monto: ");
        double montoInicial = sc.nextDouble();

        System.out.print("Introduce el rango final del monto: ");
        double montoFinal = sc.nextDouble();

        try {
            FacturaServicio facturaServicio = new FacturaServicio();
            facturas = facturaServicio.obtenerFacturasPorRangoMonto(montoInicial, montoFinal);

            System.out.println("ID -> ID Cliente - ID Empleado - Fecha Factura - Monto - Metodo Pago");
            System.out.println("-------------------------------------------------");

            for (Factura f : facturas) {
                System.out.println(f.getIdFactura() + " -> " + f.getIdCliente() + " - " + f.getIdEmpleado() + " - " + f.getFechaPedido() + " - " + f.getTotalPedido() + " - " + f.getMetodoPago());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Actualiza una factura existente en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void actualizarFactura(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID de la factura que quieres actualizar: ");
        int id = sc.nextInt();

        System.out.print("Introduce el ID del cliente: ");
        int idCliente = sc.nextInt();

        System.out.print("Introduce el ID del empleado: ");
        int idEmpleado = sc.nextInt();

        sc.nextLine();

        System.out.print("Introduce la fecha de la factura (Formato YYYY-MM-DD): ");
        String fechaFacturaStr = sc.nextLine();

        Date fechaFactura = Date.valueOf(fechaFacturaStr);

        System.out.print("Introduce el monto de la factura: ");
        double monto = sc.nextDouble();

        sc.nextLine();

        System.out.println("Introduce el metodo de pago: ");
        String metodoPago = escogerMetodoPago(sc);

        Factura factura = new Factura(idCliente, idEmpleado, fechaFactura, monto, metodoPago);
        factura.setIdFactura(id);

        try {
            FacturaServicio facturaServicio = new FacturaServicio();
            facturaServicio.actualizarFactura(factura);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Elimina una factura de la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void eliminarFactura(Scanner sc) {
        System.out.print("Introduce el ID de la factura que quieres eliminar: ");
        int id = sc.nextInt();

        try {
            FacturaServicio facturaServicio = new FacturaServicio();
            facturaServicio.eliminarFactura(id);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Muestra un menú para seleccionar el método de pago y devuelve el método seleccionado.
     *
     * @param sc Scanner para leer la entrada del usuario.
     * @return Método de pago seleccionado.
     */
    private String escogerMetodoPago(Scanner sc) {
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta");
        System.out.println("3. Transferencia");
        System.out.println("4. Cheque");
        System.out.println("5. Paypal");
        System.out.println("6. Bizum");
        System.out.println("7. Otra");
        System.out.println();
        System.out.print("Introduce el numero del metodo de pago: ");
        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                return "Efectivo";
            case 2:
                return "Tarjeta";
            case 3:
                return "Transferencia";
            case 4:
                return "Cheque";
            case 5:
                return "Paypal";
            case 6:
                return "Bizum";
            case 7:
                return "Otra";
            default:
                return "Otra";
        }
    }
}