package es.cheste.gestion_bd.gestion_tablas;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Merchandising;
import es.cheste.servicio.MerchandisingServicio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

/**
 * Clase para gestionar las operaciones relacionadas con la tabla de merchandising.
 */
public class GestionTablaMerchandising {
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Muestra el menú de opciones para gestionar el merchandising y procesa la opción seleccionada.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    public void menuTablaMerchandising(Scanner sc) {
        int opcion;

        do {
            System.out.println("1. Insertar merchandising");
            System.out.println("2. Consultar merchandising por ID");
            System.out.println("3. Consultar todo el merchandising");
            System.out.println("4. Consultar merchandising por categoria");
            System.out.println("5. Actualizar merchandising");
            System.out.println("6. Eliminar merchandising");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println();

            System.out.print("Introduce el numero de la accion que quieres realizar: ");
            opcion = sc.nextInt();

            gestionarMenuTablaMerchandising(opcion, sc);
        } while (opcion != 0);
    }

    /**
     * Gestiona la opción seleccionada del menú de merchandising.
     *
     * @param opcion Opción seleccionada por el usuario.
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void gestionarMenuTablaMerchandising(int opcion, Scanner sc) {
        MerchandisingServicio merchandisingServicio = new MerchandisingServicio();

        switch (opcion) {
            case 1:
                insertarMerchandising(sc);
                break;
            case 2:
                obtenerMerchandisingPorID(sc);
                break;
            case 3:
                obtenerTodoMerchandising();
                break;
            case 4:
                obtenerMerchandisingPorCategoria(sc);
                break;
            case 5:
                actualizarMerchandising(sc);
                break;
            case 6:
                eliminarMerchandising(sc);
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
     * Inserta un nuevo merchandising en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void insertarMerchandising(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID del producto: ");
        int idProducto = sc.nextInt();

        sc.nextLine();

        System.out.print("Introduce la categoria del merchandising: ");
        String categoria = sc.nextLine();

        System.out.print("Introduce el material del merchandising: ");
        String material = sc.nextLine();

        System.out.print("Introduce las dimensiones del merchandising: ");
        String dimensiones = sc.nextLine();

        System.out.print("Introduce el peso del merchandising: ");
        double peso = sc.nextDouble();

        System.out.print("Introduce el ID del proveedor: ");
        int idProveedor = sc.nextInt();

        Merchandising merchandising = new Merchandising(idProducto, categoria, material, dimensiones, peso, idProveedor);

        try {
            MerchandisingServicio merchandisingServicio = new MerchandisingServicio();
            merchandisingServicio.insertarMerchandising(merchandising);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene un merchandising por su ID y lo muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerMerchandisingPorID(Scanner sc) {
        System.out.print("Introduce el ID del merchandising: ");
        int id = sc.nextInt();

        try {
            MerchandisingServicio merchandisingServicio = new MerchandisingServicio();
            Merchandising merchandising = merchandisingServicio.obtenerPorID(id);
            System.out.println("ID Producto -> Categoria - Material - Dimensiones - Peso - ID Proveedor");
            System.out.println("-------------------------------------------------");

            if (merchandising == null) {
                System.out.println("No se ha encontrado el merchandising con ID: " + id);
                return;
            }
            System.out.println(merchandising.getIdProducto() + " -> " + merchandising.getCategoria() + " - " + merchandising.getMaterial() + " - " + merchandising.getDimensiones() + " - " + merchandising.getPeso() + " - " + merchandising.getProveedor());
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene todo el merchandising y lo muestra.
     */
    private void obtenerTodoMerchandising() {
        List<Merchandising> merchandising = null;

        try {
            MerchandisingServicio merchandisingServicio = new MerchandisingServicio();
            merchandising = merchandisingServicio.obtenerTodos();

            System.out.println("ID Producto -> Categoria - Material - Dimensiones - Peso - ID Proveedor");
            System.out.println("-------------------------------------------------");

            for (Merchandising m : merchandising) {
                System.out.println(m.getIdProducto() + " -> " + m.getCategoria() + " - " + m.getMaterial() + " - " + m.getDimensiones() + " - " + m.getPeso() + " - " + m.getProveedor());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene el merchandising por categoría y lo muestra.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void obtenerMerchandisingPorCategoria(Scanner sc) {
        List<Merchandising> merchandising = null;

        System.out.print("Introduce la categoria del merchandising: ");
        String categoria = sc.next();

        try {
            MerchandisingServicio merchandisingServicio = new MerchandisingServicio();
            merchandising = merchandisingServicio.obtenerPorCategoria(categoria);

            System.out.println("ID Producto - Categoria - Material - Dimensiones - Peso - ID Proveedor");
            System.out.println("-------------------------------------------------");

            for (Merchandising m : merchandising) {
                System.out.println(m.getIdProducto() + " -> " + m.getCategoria() + " - " + m.getMaterial() + " - " + m.getDimensiones() + " - " + m.getPeso() + " - " + m.getProveedor());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Actualiza un merchandising existente en la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void actualizarMerchandising(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID del merchandising que quieres actualizar: ");
        int id = sc.nextInt();

        System.out.print("Introduce el ID del producto: ");
        int idProducto = sc.nextInt();

        System.out.println("Introduce la categoria del merchandising: ");
        String categoria = sc.nextLine();

        System.out.println("Introduce el material del merchandising: ");
        String material = sc.nextLine();

        System.out.println("Introduce las dimensiones del merchandising: ");
        String dimensiones = sc.nextLine();

        System.out.println("Introduce el peso del merchandising: ");
        double peso = sc.nextDouble();

        System.out.println("Introduce el ID del proveedor: ");
        int idProveedor = sc.nextInt();

        Merchandising merchandising = new Merchandising(idProducto, categoria, material, dimensiones, peso, idProveedor);
        merchandising.setIdProducto(id);

        try {
            MerchandisingServicio merchandisingServicio = new MerchandisingServicio();
            merchandisingServicio.actualizar(merchandising);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Elimina un merchandising de la base de datos.
     *
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void eliminarMerchandising(Scanner sc) {
        System.out.print("Introduce el ID del merchandising que quieres eliminar: ");
        int id = sc.nextInt();

        try {
            MerchandisingServicio merchandisingServicio = new MerchandisingServicio();
            merchandisingServicio.eliminar(id);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

}