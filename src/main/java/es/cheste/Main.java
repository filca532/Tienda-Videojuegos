package es.cheste;

import es.cheste.gestion_bd.ConexionBD;
import es.cheste.gestion_bd.CreacionBD;
import es.cheste.gestion_bd.gestion_tablas.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Clase principal del programa que gestiona la base de datos y el menú de opciones.
 */
public class Main {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private static Scanner sc = new Scanner(System.in);

    /**
     * Método principal que inicia la aplicación.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        ConexionBD conexionBD = new ConexionBD();

        conexionBD.getConnection();

        crearBD(conexionBD);

        menu();

        conexionBD.closeConnection();
        sc.close();
    }

    /**
     * Crea la base de datos y las tablas necesarias.
     * @param conexionBD Objeto de conexión a la base de datos.
     */
    public static void crearBD(ConexionBD conexionBD) {
        CreacionBD gestionBD = new CreacionBD();
        gestionBD.crearTablas(conexionBD);
        gestionBD.insertarDatos(conexionBD);
    }

    /**
     * Muestra el menú principal y gestiona las opciones seleccionadas por el usuario.
     */
    public static void menu() {
        int opcion;

        do {
            System.out.println(".::::::::: MENU PRINCIPAL ::::::::::.");
            System.out.println("Que tabla quieres consultar?");
            System.out.println("1. Cliente");
            System.out.println("2. Miembro");
            System.out.println("3. Empleado");
            System.out.println("4. Factura");
            System.out.println("5. Proveedor");
            System.out.println("6. Producto");
            System.out.println("7. Factura Linea");
            System.out.println("8. Merchandising");
            System.out.println("9. Videojuego");
            System.out.println("10. Consola");
            System.out.println("11. Plataforma");
            System.out.println("12. Genero");
            System.out.println("13. Videojuego Plataforma");
            System.out.println("14. Videojuego Genero");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println();

            System.out.print("Introduce el numero de la tabla que quieres consultar: ");

            opcion = sc.nextInt();

            gestionarMenu(opcion);

        } while (opcion != 0);
    }

    /**
     * Gestiona las opciones del menú principal.
     * @param opcion Opción seleccionada por el usuario.
     */
    public static void gestionarMenu(int opcion) {
        switch (opcion) {
            case 1:
                GestionTablaCliente gestionTablaCliente = new GestionTablaCliente();
                gestionTablaCliente.menuTablaCliente(sc);
                break;
            case 2:
                GestionTablaMiembro gestionTablaMiembro = new GestionTablaMiembro();
                gestionTablaMiembro.menuTablaMiembro(sc);
                break;
            case 3:
                GestionTablaEmpleado gestionTablaEmpleado = new GestionTablaEmpleado();
                gestionTablaEmpleado.menuTablaEmpleado(sc);
                break;
            case 4:
                GestionTablaFactura gestionTablaFactura = new GestionTablaFactura();
                gestionTablaFactura.menuTablaFactura(sc);
                break;
            case 5:
                GestionTablaProveedor gestionTablaProveedor = new GestionTablaProveedor();
                gestionTablaProveedor.menuTablaProveedor(sc);
                break;
            case 6:
                GestionTablaProducto gestionTablaProducto = new GestionTablaProducto();
                gestionTablaProducto.menuTablaProducto(sc);
                break;
            case 7:
                GestionTablaFacturaLinea gestionTablaFacturaLinea = new GestionTablaFacturaLinea();
                gestionTablaFacturaLinea.menuTablaFacturaLinea(sc);
                break;
            case 8:
                GestionTablaMerchandising gestionTablaMerchandising = new GestionTablaMerchandising();
                gestionTablaMerchandising.menuTablaMerchandising(sc);
                break;
            case 9:
                GestionTablaVideojuego gestionTablaVideojuego = new GestionTablaVideojuego();
                gestionTablaVideojuego.menuTablaVideojuego(sc);
                break;
            case 10:
                GestionTablaConsola gestionTablaConsola = new GestionTablaConsola();
                gestionTablaConsola.menuTablaConsola(sc);
                break;
            case 11:
                GestionTablaPlataforma gestionTablaPlataforma = new GestionTablaPlataforma();
                gestionTablaPlataforma.menuTablaPlataforma(sc);
                break;
            case 12:
                GestionTablaGenero gestionTablaGenero = new GestionTablaGenero();
                gestionTablaGenero.menuTablaGenero(sc);
                break;
            case 13:
                GestionTablaVideojuegoPlataforma gestionTablaVideojuegoPlataforma = new GestionTablaVideojuegoPlataforma();
                gestionTablaVideojuegoPlataforma.menuTablaVideojuegoPlataforma(sc);
                break;
            case 14:
                GestionTablaVideojuegoGenero gestionTablaVideojuegoGenero = new GestionTablaVideojuegoGenero();
                gestionTablaVideojuegoGenero.menuTablaVideojuegoGenero(sc);
                break;
            case 0:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }
}