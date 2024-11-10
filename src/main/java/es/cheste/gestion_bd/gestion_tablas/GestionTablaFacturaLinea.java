package es.cheste.gestion_bd.gestion_tablas;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.FacturaLinea;
import es.cheste.objetos.Producto;
import es.cheste.servicio.FacturaLineaServicio;
import es.cheste.servicio.ProductoServicio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

/**
 * Clase para gestionar las operaciones relacionadas con la tabla de líneas de facturas.
 */
public class GestionTablaFacturaLinea {
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Muestra el menú de opciones para gestionar las líneas de facturas y procesa la opción seleccionada.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    public void menuTablaFacturaLinea(Scanner sc) {
        int opcion;

        do {
            System.out.println("1. Insertar linea de factura");
            System.out.println("2. Consultar linea de factura por ID");
            System.out.println("3. Consultar todas las lineas de factura");
            System.out.println("4. Consultar lineas de factura por ID de factura");
            System.out.println("5. Consultar lineas de factura por ID de producto");
            System.out.println("6. Consultar lineas de factura por cantidad");
            System.out.println("7. Actualizar linea de factura");
            System.out.println("8. Eliminar linea de factura");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println();

            System.out.print("Introduce el numero de la accion que quieres realizar: ");
            opcion = sc.nextInt();

            gestionarMenuTablaFacturaLinea(opcion, sc);
        } while (opcion != 0);
    }

    /**
     * Gestiona la opción seleccionada del menú de líneas de facturas.
     *
     * @param opcion Opción seleccionada por el usuario.
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void gestionarMenuTablaFacturaLinea(int opcion, Scanner sc) {
        switch (opcion) {
            case 1:
                insertarFacturaLinea(sc);
                break;
            case 2:
                obtenerFacturaLineaPorID(sc);
                break;
            case 3:
                obtenerTodasFacturasLinea();
                break;
            case 4:
                obtenerFacturasLineaPorIDFactura(sc);
                break;
            case 5:
                obtenerFacturasLineaPorIDProducto(sc);
                break;
            case 6:
                obtenerFacturasLineaPorCantidad(sc);
                break;
            case 7:
                actualizarFacturaLinea(sc);
                break;
            case 8:
                eliminarFacturaLinea(sc);
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
     * Inserta una nueva línea de factura en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void insertarFacturaLinea(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID de la factura: ");
        int idFactura = sc.nextInt();

        System.out.print("Introduce la linea de la factura: ");
        int idFacturaLinea = sc.nextInt();

        System.out.print("Introduce el ID del producto: ");
        int idProducto = sc.nextInt();

        String tipo = obtenerTipoProducto(idProducto);

        if (tipo == null) {
            System.out.println("No se ha encontrado el producto");
            return;
        }

        System.out.print("Introduce la cantidad: ");
        int cantidad = sc.nextInt();

        double precioUnitario = obtenerPrecioProducto(idProducto);

        FacturaLinea facturaLinea = new FacturaLinea(idFactura, idFacturaLinea, idProducto, tipo, cantidad, precioUnitario);

        try {
            FacturaLineaServicio facturaLineaServicio = new FacturaLineaServicio();
            facturaLineaServicio.insertarFacturaLinea(facturaLinea);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene una línea de factura por su ID y la muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerFacturaLineaPorID(Scanner sc) {
        System.out.print("Introduce el ID de la factura: ");
        int idFactura = sc.nextInt();

        System.out.println("Introduce el ID de la linea de la factura: ");
        int idFacturaLinea = sc.nextInt();

        try {
            FacturaLineaServicio facturaLineaServicio = new FacturaLineaServicio();
            FacturaLinea facturaLinea = facturaLineaServicio.obtenerPorID(idFactura, idFacturaLinea);
            System.out.println("ID Factura - ID Factura Linea -> ID Producto - Tipo - Cantidad - Precio Unitario");
            System.out.println("-------------------------------------------------");

            if (facturaLinea == null) {
                System.out.println("No se ha encontrado la linea de factura");
                return;
            }
            System.out.println(facturaLinea.getIdFactura() + " - " + facturaLinea.getIdFacturaLinea() + " -> " + facturaLinea.getIdProducto() + " - " + facturaLinea.getTipoProducto() + " - " + facturaLinea.getCantidad() + " - " + facturaLinea.getPrecioUnitario());
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene todas las líneas de facturas y las muestra.
     */
    private void obtenerTodasFacturasLinea() {
        List<FacturaLinea> facturasLinea = null;

        try {
            FacturaLineaServicio facturaLineaServicio = new FacturaLineaServicio();
            facturasLinea = facturaLineaServicio.obtenerTodas();

            System.out.println("ID Factura - ID Factura Linea -> ID Producto - Tipo - Cantidad - Precio Unitario");
            System.out.println("-------------------------------------------------");

            for (FacturaLinea f : facturasLinea) {
                System.out.println(f.getIdFactura() + " - " + f.getIdFacturaLinea() + " -> " + f.getIdProducto() + " - " + f.getTipoProducto() + " - " + f.getCantidad() + " - " + f.getPrecioUnitario());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene las líneas de facturas por ID de factura y las muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerFacturasLineaPorIDFactura(Scanner sc) {
        List<FacturaLinea> facturasLinea = null;

        System.out.print("Introduce el ID de la factura: ");
        int idFactura = sc.nextInt();

        try {
            FacturaLineaServicio facturaLineaServicio = new FacturaLineaServicio();
            facturasLinea = facturaLineaServicio.obtenerFacturaLineaPorFactura(idFactura);

            System.out.println("ID Factura - ID Factura Linea -> ID Producto - Tipo - Cantidad - Precio Unitario");
            System.out.println("-------------------------------------------------");

            for (FacturaLinea f : facturasLinea) {
                System.out.println(f.getIdFactura() + " -> " + f.getIdFacturaLinea() + " - " + f.getIdProducto() + " - " + f.getTipoProducto() + " - " + f.getCantidad() + " - " + f.getPrecioUnitario());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene las líneas de facturas por ID de producto y las muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerFacturasLineaPorIDProducto(Scanner sc) {
        List<FacturaLinea> facturasLinea = null;

        System.out.print("Introduce el ID del producto: ");
        int idProducto = sc.nextInt();

        try {
            FacturaLineaServicio facturaLineaServicio = new FacturaLineaServicio();
            facturasLinea = facturaLineaServicio.obtenerFacturaLineaPorProducto(idProducto);

            System.out.println("ID Factura - ID Factura Linea -> ID Producto - Tipo - Cantidad - Precio Unitario");
            System.out.println("-------------------------------------------------");

            for (FacturaLinea f : facturasLinea) {
                System.out.println(f.getIdFactura() + " - " + f.getIdFacturaLinea() + " -> " + f.getIdProducto() + " - " + f.getTipoProducto() + " - " + f.getCantidad() + " - " + f.getPrecioUnitario());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene las líneas de facturas por cantidad y las muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerFacturasLineaPorCantidad(Scanner sc) {
        List<FacturaLinea> facturasLinea = null;

        System.out.print("Introduce la cantidad min: ");
        int cantidadMin = sc.nextInt();

        System.out.print("Introduce la cantidad max: ");
        int cantidadMax = sc.nextInt();

        try {
            FacturaLineaServicio facturaLineaServicio = new FacturaLineaServicio();
            facturasLinea = facturaLineaServicio.obtenerFacturaLineaPorRangoCantidad(cantidadMin, cantidadMax);

            System.out.println("ID Factura - ID Factura Linea -> ID Producto - Tipo - Cantidad - Precio Unitario");
            System.out.println("-------------------------------------------------");

            for (FacturaLinea f : facturasLinea) {
                System.out.println(f.getIdFactura() + " - " + f.getIdFacturaLinea() + " -> " + f.getIdProducto() + " - " + f.getTipoProducto() + " - " + f.getCantidad() + " - " + f.getPrecioUnitario());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Actualiza una línea de factura existente en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void actualizarFacturaLinea(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID de la factura: ");
        int idFactura = sc.nextInt();

        System.out.print("Introduce el ID de la linea de la factura: ");
        int idFacturaLinea = sc.nextInt();

        System.out.print("Introduce el ID del producto: ");
        int idProducto = sc.nextInt();

        String tipo = obtenerTipoProducto(idProducto);

        if (tipo == null) {
            System.out.println("No se ha encontrado el producto");
            return;
        }

        System.out.print("Introduce la cantidad: ");
        int cantidad = sc.nextInt();

        double precioUnitario = obtenerPrecioProducto(idProducto);

        FacturaLinea facturaLinea = new FacturaLinea(idFactura, idFacturaLinea, idProducto, tipo, cantidad, precioUnitario);
        facturaLinea.setIdFacturaLinea(idFacturaLinea);

        try {
            FacturaLineaServicio facturaLineaServicio = new FacturaLineaServicio();
            facturaLineaServicio.actualizar(facturaLinea);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Elimina una línea de factura de la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void eliminarFacturaLinea(Scanner sc) {
        System.out.print("Introduce el ID de la factura: ");
        int idFactura = sc.nextInt();

        System.out.print("Introduce el ID de la linea de la factura: ");
        int idFacturaLinea = sc.nextInt();

        try {
            FacturaLineaServicio facturaLineaServicio = new FacturaLineaServicio();
            facturaLineaServicio.eliminar(idFactura, idFacturaLinea);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene el tipo de producto por su ID.
     *
     * @param idProducto ID del producto.
     * @return Tipo de producto.
     */
    private String obtenerTipoProducto(int idProducto) {
        ProductoServicio productoServicio = new ProductoServicio();
        String tipo = null;
        try {
            Producto producto = productoServicio.obtenerPorID(idProducto);

            if (producto == null) {
                System.out.println("No se ha encontrado el producto");
                return tipo;
            }

            tipo = producto.getTipoProducto();

        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }

        return tipo;
    }

    /**
     * Obtiene el precio de un producto por su ID.
     *
     * @param idProducto ID del producto.
     * @return Precio del producto.
     */
    private double obtenerPrecioProducto(int idProducto) {
        ProductoServicio productoServicio = new ProductoServicio();
        double precio = 0;
        try {
            Producto producto = productoServicio.obtenerPorID(idProducto);

            if (producto == null) {
                System.out.println("No se ha encontrado el producto");
                return precio;
            }

            precio = producto.getPrecio();

        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }

        return precio;
    }
}