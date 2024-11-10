package es.cheste.gestion_bd.gestion_tablas;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Producto;
import es.cheste.servicio.ProductoServicio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

/**
 * Clase para gestionar las operaciones relacionadas con la tabla de productos.
 */
public class GestionTablaProducto {
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Muestra el menú de opciones para gestionar los productos y procesa la opción seleccionada.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    public void menuTablaProducto(Scanner sc) {
        int opcion;

        do {
            System.out.println("1. Insertar producto");
            System.out.println("2. Consultar producto por ID");
            System.out.println("3. Consultar todos los productos");
            System.out.println("4. Consultar productos por rango de stock");
            System.out.println("5. Consultar productos por rango de precio");
            System.out.println("6. Consultar productos por tipo");
            System.out.println("7. Actualizar producto");
            System.out.println("8. Eliminar producto");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println();

            System.out.print("Introduce el numero de la accion que quieres realizar: ");
            opcion = sc.nextInt();

            gestionarMenuTablaProducto(opcion, sc);
        } while (opcion != 0);
    }

    /**
     * Gestiona la opción seleccionada del menú de productos.
     *
     * @param opcion Opción seleccionada por el usuario.
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void gestionarMenuTablaProducto(int opcion, Scanner sc) {
        ProductoServicio productoServicio = new ProductoServicio();

        switch (opcion) {
            case 1:
                insertarProducto(sc);
                break;
            case 2:
                obtenerProductoPorID(sc);
                break;
            case 3:
                obtenerTodosProductos();
                break;
            case 4:
                obtenerProductosPorRangoStock(sc);
                break;
            case 5:
                obtenerProductosPorRangoPrecio(sc);
                break;
            case 6:
                obtenerProductosPorTipo(sc);
                break;
            case 7:
                actualizarProducto(sc);
                break;
            case 8:
                eliminarProducto(sc);
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
     * Inserta un nuevo producto en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void insertarProducto(Scanner sc) {
        sc.nextLine();

        System.out.println("Escoge el tipo del producto: ");
        String tipo = escogerProducto(sc);

        sc.nextLine();

        System.out.print("Introduce el nombre del producto: ");
        String nombre = sc.nextLine();

        System.out.print("Introduce el precio del producto: ");
        double precio = sc.nextDouble();

        System.out.print("Introduce el stock del producto: ");
        int stock = sc.nextInt();

        Producto producto = new Producto(tipo, nombre, precio, stock);

        try {
            ProductoServicio productoServicio = new ProductoServicio();
            productoServicio.insertarProducto(producto);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Muestra un menú para seleccionar el tipo de producto y devuelve el tipo seleccionado.
     *
     * @param sc Scanner para leer la entrada del usuario.
     * @return Tipo de producto seleccionado.
     */
    private String escogerProducto(Scanner sc) {
        System.out.println("1. Videojuego");
        System.out.println("2. Consola");
        System.out.println("3. Merchandising");
        System.out.println();
        System.out.print("Introduce el numero del tipo de producto: ");

        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                return "videojuego";
            case 2:
                return "consola";
            case 3:
                return "merchandising";
            default:
                return "videojuego";
        }
    }

    /**
     * Obtiene un producto por su ID y lo muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerProductoPorID(Scanner sc) {
        System.out.print("Introduce el ID del producto: ");
        int id = sc.nextInt();

        try {
            ProductoServicio productoServicio = new ProductoServicio();
            Producto producto = productoServicio.obtenerPorID(id);
            System.out.println("ID -> Tipo - Nombre - Precio - Stock");
            System.out.println("-------------------------------------------------");

            if (producto == null) {
                System.out.println("No se ha encontrado el producto con ID: " + id);
                return;
            }
            System.out.println(producto.getIdProducto() + " -> " + producto.getTipoProducto() + " - " + producto.getNombre() + " - " + producto.getPrecio() + " - " + producto.getStock());
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene todos los productos y los muestra.
     */
    private void obtenerTodosProductos() {
        List<Producto> productos = null;

        try {
            ProductoServicio productoServicio = new ProductoServicio();
            productos = productoServicio.obtenerTodos();

            System.out.println("ID -> Tipo - Nombre - Precio - Stock");
            System.out.println("-------------------------------------------------");

            for (Producto p : productos) {
                System.out.println(p.getIdProducto() + " -> " + p.getTipoProducto() + " - " + p.getNombre() + " - " + p.getPrecio() + " - " + p.getStock());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene los productos por rango de stock y los muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerProductosPorRangoStock(Scanner sc) {
        List<Producto> productos = null;

        System.out.print("Introduce el rango inicial del stock: ");
        int stockInicial = sc.nextInt();

        System.out.print("Introduce el rango final del stock: ");
        int stockFinal = sc.nextInt();

        try {
            ProductoServicio productoServicio = new ProductoServicio();
            productos = productoServicio.obtenerProductosPorRangoStock(stockInicial, stockFinal);

            System.out.println("ID -> Tipo - Nombre - Precio - Stock");
            System.out.println("-------------------------------------------------");

            for (Producto p : productos) {
                System.out.println(p.getIdProducto() + " -> " + p.getTipoProducto() + " - " + p.getNombre() + " - " + p.getPrecio() + " - " + p.getStock());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene los productos por rango de precio y los muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerProductosPorRangoPrecio(Scanner sc) {
        List<Producto> productos = null;

        System.out.print("Introduce el rango inicial del precio: ");
        double precioInicial = sc.nextDouble();

        System.out.print("Introduce el rango final del precio: ");
        double precioFinal = sc.nextDouble();

        try {
            ProductoServicio productoServicio = new ProductoServicio();
            productos = productoServicio.obtenerProductosPorRangoPrecio(precioInicial, precioFinal);

            System.out.println("ID -> Tipo - Nombre - Precio - Stock");
            System.out.println("-------------------------------------------------");

            for (Producto p : productos) {
                System.out.println(p.getIdProducto() + " -> " + p.getTipoProducto() + " - " + p.getNombre() + " - " + p.getPrecio() + " - " + p.getStock());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene los productos por tipo y los muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerProductosPorTipo(Scanner sc) {
        List<Producto> productos = null;

        System.out.print("Introduce el tipo del producto: ");
        String tipo = escogerProducto(sc);

        try {
            ProductoServicio productoServicio = new ProductoServicio();
            productos = productoServicio.obtenerProductosPorTipo(tipo);

            System.out.println("ID -> Tipo - Nombre - Precio - Stock");
            System.out.println("-------------------------------------------------");

            for (Producto p : productos) {
                System.out.println(p.getIdProducto() + " -> " + p.getTipoProducto() + " - " + p.getNombre() + " - " + p.getPrecio() + " - " + p.getStock());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Actualiza un producto existente en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void actualizarProducto(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID del producto que quieres actualizar: ");
        int id = sc.nextInt();

        System.out.println("Escoge el tipo del producto: ");
        String tipo = escogerProducto(sc);

        sc.nextLine();

        System.out.print("Introduce el nombre del producto: ");
        String nombre = sc.nextLine();

        System.out.print("Introduce el precio del producto: ");
        double precio = sc.nextDouble();

        System.out.print("Introduce el stock del producto: ");
        int stock = sc.nextInt();

        Producto producto = new Producto(tipo, nombre, precio, stock);
        producto.setIdProducto(id);

        try {
            ProductoServicio productoServicio = new ProductoServicio();
            productoServicio.actualizar(producto);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Elimina un producto de la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void eliminarProducto(Scanner sc) {
        System.out.print("Introduce el ID del producto que quieres eliminar: ");
        int id = sc.nextInt();

        try {
            ProductoServicio productoServicio = new ProductoServicio();
            productoServicio.eliminar(id);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}