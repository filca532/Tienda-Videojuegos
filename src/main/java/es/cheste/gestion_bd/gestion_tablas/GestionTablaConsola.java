package es.cheste.gestion_bd.gestion_tablas;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Consola;
import es.cheste.servicio.ConsolaServicio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

/**
 * Clase para gestionar las operaciones de la tabla Consola.
 */
public class GestionTablaConsola {
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Muestra el menú de opciones para gestionar la tabla Consola.
     *
     * @param sc el objeto Scanner para la entrada de datos
     */
    public void menuTablaConsola(Scanner sc) {
        int opcion;

        do {
            System.out.println("1. Insertar consola");
            System.out.println("2. Consultar consola por ID");
            System.out.println("3. Consultar todas las consolas");
            System.out.println("4. Consultar consolas por marca");
            System.out.println("5. Consultar consolas por proveedor");
            System.out.println("6. Actualizar consola");
            System.out.println("7. Eliminar consola");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println();

            System.out.print("Introduce el numero de la accion que quieres realizar: ");
            opcion = sc.nextInt();

            gestionarMenuTablaConsola(opcion, sc);
        } while (opcion != 0);
    }

    /**
     * Gestiona las opciones seleccionadas en el menú de la tabla Consola.
     *
     * @param opcion la opción seleccionada
     * @param sc el objeto Scanner para la entrada de datos
     */
    private void gestionarMenuTablaConsola(int opcion, Scanner sc) {

        switch (opcion) {
            case 1:
                insertarConsola(sc);
                break;
            case 2:
                obtenerConsolaPorID(sc);
                break;
            case 3:
                obtenerTodasConsolas();
                break;
            case 4:
                obtenerConsolasPorMarca(sc);
                break;
            case 5:
                obtenerConsolasPorProveedor(sc);
                break;
            case 6:
                actualizarConsola(sc);
                break;
            case 7:
                eliminarConsola(sc);
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
     * Inserta una nueva consola en la base de datos.
     *
     * @param sc el objeto Scanner para la entrada de datos
     */
    private void insertarConsola(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID del producto: ");
        int idProducto = sc.nextInt();

        sc.nextLine();

        System.out.print("Introduce la marca de la consola: ");
        String marca = sc.nextLine();

        System.out.print("Introduce el modelo de la consola: ");
        String modelo = sc.nextLine();

        System.out.print("Introduce el almacenamiento de la consola: ");
        String almacenamiento = sc.nextLine();

        System.out.print("Introduce el color de la consola: ");
        String color = sc.nextLine();

        System.out.print("Introduce cuantos mandos estan incluidos: ");
        int mandosIncluidos = sc.nextInt();

        System.out.print("Introduce el ID del proveedor: ");
        int idProveedor = sc.nextInt();

        Consola consola = new Consola(idProducto, marca, modelo, almacenamiento, color, mandosIncluidos, idProveedor);

        try {
            ConsolaServicio consolaServicio = new ConsolaServicio();
            consolaServicio.insertarConsola(consola);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene una consola por su ID y muestra su información.
     *
     * @param sc el objeto Scanner para la entrada de datos
     */
    private void obtenerConsolaPorID(Scanner sc) {
        System.out.print("Introduce el ID de la consola: ");
        int id = sc.nextInt();

        try {
            ConsolaServicio consolaServicio = new ConsolaServicio();
            Consola consola = consolaServicio.obtenerPorID(id);
            System.out.println("ID Producto -> Marca - Modelo - Almacenamiento - Color - Mandos Incluidos - ID Proveedor");
            System.out.println("-------------------------------------------------");

            if (consola == null) {
                System.out.println("No se ha encontrado la consola con ID: " + id);
                return;
            }
            System.out.println(consola.getIdProducto() + " -> " + consola.getMarca() + " - " + consola.getModelo() + " - " + consola.getAlmacenamiento() + " - " + consola.getColor() + " - " + consola.getMandosIncluidos() + " - " + consola.getProveedor());
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene todas las consolas y muestra su información.
     */
    private void obtenerTodasConsolas() {
        List<Consola> consolas = null;

        try {
            ConsolaServicio consolaServicio = new ConsolaServicio();
            consolas = consolaServicio.obtenerTodas();

            System.out.println("ID Producto -> Marca - Modelo - Almacenamiento - Color - Mandos Incluidos - ID Proveedor");
            System.out.println("-------------------------------------------------");

            for (Consola c : consolas) {
                System.out.println(c.getIdProducto() + " -> " + c.getMarca() + " - " + c.getModelo() + " - " + c.getAlmacenamiento() + " - " + c.getColor() + " - " + c.getMandosIncluidos() + " - " + c.getProveedor());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene consolas por su marca y muestra su información.
     *
     * @param sc el objeto Scanner para la entrada de datos
     */
    private void obtenerConsolasPorMarca(Scanner sc) {
        List<Consola> consolas = null;

        System.out.print("Introduce la marca de la consola: ");
        String marca = sc.next();

        try {
            ConsolaServicio consolaServicio = new ConsolaServicio();
            consolas = consolaServicio.obtenerPorMarca(marca);

            System.out.println("ID Producto -> Marca - Modelo - Almacenamiento - Color - Mandos Incluidos - ID Proveedor");
            System.out.println("-------------------------------------------------");

            for (Consola c : consolas) {
                System.out.println(c.getIdProducto() + " -> " + c.getMarca() + " - " + c.getModelo() + " - " + c.getAlmacenamiento() + " - " + c.getColor() + " - " + c.getMandosIncluidos() + " - " + c.getProveedor());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Obtiene consolas por su proveedor y muestra su información.
     *
     * @param sc el objeto Scanner para la entrada de datos
     */
    private void obtenerConsolasPorProveedor(Scanner sc) {
        List<Consola> consolas = null;

        System.out.print("Introduce el ID del proveedor: ");
        int idProveedor = sc.nextInt();

        try {
            ConsolaServicio consolaServicio = new ConsolaServicio();
            consolas = consolaServicio.obtenerPorProveedor(idProveedor);

            System.out.println("ID Producto -> Marca - Modelo - Almacenamiento - Color - Mandos Incluidos - ID Proveedor");
            System.out.println("-------------------------------------------------");

            for (Consola c : consolas) {
                System.out.println(c.getIdProducto() + " -> " + c.getMarca() + " - " + c.getModelo() + " - " + c.getAlmacenamiento() + " - " + c.getColor() + " - " + c.getMandosIncluidos() + " - " + c.getProveedor());
                System.out.println("-------------------------------------------------");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Actualiza la información de una consola existente en la base de datos.
     *
     * @param sc el objeto Scanner para la entrada de datos
     */
    private void actualizarConsola(Scanner sc) {
        sc.nextLine();

        System.out.print("Introduce el ID del producto: ");
        int idProducto = sc.nextInt();

        sc.nextLine();

        System.out.print("Introduce la marca de la consola: ");
        String marca = sc.nextLine();

        System.out.print("Introduce el modelo de la consola: ");
        String modelo = sc.nextLine();

        System.out.print("Introduce el almacenamiento de la consola: ");
        String almacenamiento = sc.nextLine();

        System.out.print("Introduce el color de la consola: ");
        String color = sc.nextLine();

        System.out.print("Introduce cuantos mandos estan incluidos: ");
        int mandosIncluidos = sc.nextInt();

        System.out.print("Introduce el ID del proveedor: ");
        int idProveedor = sc.nextInt();

        Consola consola = new Consola(idProducto, marca, modelo, almacenamiento, color, mandosIncluidos, idProveedor);
        consola.setIdProducto(idProducto);

        try {
            ConsolaServicio consolaServicio = new ConsolaServicio();
            consolaServicio.actualizar(consola);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Elimina una consola de la base de datos por su ID.
     *
     * @param sc el objeto Scanner para la entrada de datos
     */
    private void eliminarConsola(Scanner sc) {
        System.out.print("Introduce el ID de la consola que quieres eliminar: ");
        int id = sc.nextInt();

        try {
            ConsolaServicio consolaServicio = new ConsolaServicio();
            consolaServicio.eliminar(id);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}