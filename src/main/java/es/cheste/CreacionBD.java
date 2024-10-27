package es.cheste;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

public class GestionBD {
    private static final Logger LOGGER = LogManager.getRootLogger();

    public GestionBD() {
        super();
    }

    public void crearTablas(ConexionBD conexionBD) {
        crearTablaCliente(conexionBD);
        crearTablaMiembros(conexionBD);
        crearTablaEmpleados(conexionBD);
        crearTablaFactura(conexionBD);
        crearTablaFacturaLinea(conexionBD);
        crearTablaProducto(conexionBD);
        crearTablaProveedor(conexionBD);
        crearTablaMerchandising(conexionBD);
        crearTablaVideojuegos(conexionBD);
        crearTablaConsolas(conexionBD);
        crearTablaPlataforma(conexionBD);
        crearTablaGenero(conexionBD);
        crearTabla
    }

    private void crearTablaCliente(ConexionBD conexionBD) {

        String sql = "CREATE TABLE IF NOT EXIST CLIENTE (" +
                "ID_CLIENTE SERIAL PRIMARY KEY, " +
                "NOMBRE VARCHAR(100) NOT NULL, " +
                "APELLIDOS VARCHAR(100) NOT NULL, " +
                "EMAIL VARCHAR(100) NOT NULL UNIQUE, " +
                "TELEFONO VARCHAR(15) NOT NULL UNIQUE, " +
                "DIRECCION VARCHAR(200) NOT NULL, " +
                "FECHA_REGISTRO DATE NOT NULL" +
                ")";

        try {
            Statement statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaMiembros(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXIST MIEMBRO (" +
                "ID_MIEMBRO SERIAL PRIMARY KEY, " +
                "ID_CLIENTE INTEGER NOT NULL UNIQUE, " +
                "TIPO_MIEMBRO VARCHAR(50) NOT NULL, " +
                "NIVEL_MEMBRESIA VARCHAR(50), " +
                "FECHA_INGRESO DATE NOT NULL, " +
                "FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE" +
                ")";

        Statement statement = null;
        try {
            statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaEmpleados(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXIST EMPLEADO (" +
                "ID_EMPLEADO SERIAL PRIMARY KEY, " +
                "NOMBRE VARCHAR(100) NOT NULL, " +
                "APELLIDOS VARCHAR(100) NOT NULL, " +
                "EMAIL VARCHAR(100) NOT NULL UNIQUE, " +
                "TELEFONO VARCHAR(15) NOT NULL UNIQUE, " +
                "PUESTO VARCHAR(100) NOT NULL, " +
                "SALARIO DECIMAL(8, 2) NOT NULL, " +
                "FECHA_CONTRATACION DATE NOT NULL, " +
                "DESCUENTO_EMPLEADO DECIMAL(5,2))";

        Statement statement = null;
        try {
            statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaFactura(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXIST FACTURA (" +
                "ID_FACTURA SERIAL PRIMARY KEY, " +
                "ID_CLIENTE INTEGER NOT NULL, " +
                "ID_EMPLEADO INTEGER NOT NULL, " +
                "FECHA_PEDIDO DATE NOT NULL, " +
                "TOTAL_PEDIDO DECIMAL(10, 2) NOT NULL, " +
                "METODO_PAGO VARCHAR(50), " +
                "FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE), " +
                "FOREIGN KEY (ID_EMPLEADO) REFERENCES EMPLEADO(ID_EMPLEADO)" +
                ")";

        Statement statement = null;
        try {
            statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaFacturaLinea(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXIST FACTURA_LINEA (" +
                "ID_FACTURA INTEGER NOT NULL, " +
                "ID_FACTURA_LINEA SERIAL, " +
                "ID_PRODUCTO INTEGER NOT NULL, " +
                "TIPO_PRODUCTO VARCHAR(20) NOT NULL, " +
                "CANTIDAD INTEGER NOT NULL, " +
                "PRECIO_UNITARIO DECIMAL(10, 2) NOT NULL, " +
                "PRIMARY KEY (ID_FACTURA, ID_FACTURA_LINEA), " +
                "FOREIGN KEY (ID_FACTURA) REFERENCES FACTURA(ID_FACTURA), " +
                "FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTOS(ID_PRODUCTO)" +
                ")";

        Statement statement = null;
        try {
            statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaProducto(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXIST PRODUCTO (" +
                "ID_PRODUCTO SERIAL, " +
                "TIPO_PRODUCTO ENUM('videojuego', 'consola', 'merchandising') NOT NULL, " +
                "NOMBRE VARCHAR(100) NOT NULL, " +
                "PRECIO DECIMAL(10, 2) NOT NULL, " +
                "STOCK INTEGER NOT NULL, " +
                "PRIMARY KEY (ID_PRODUCTO, TIPO_PRODUCTO)" +
                ")";

        Statement statement = null;
        try {
            statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaProveedor(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXIST PROVEEDOR (" +
                "ID_PROVEEDOR SERIAL PRIMARY KEY, " +
                "NOMBRE VARCHAR(100) NOT NULL, " +
                "DIRECCION VARCHAR(200), " +
                "TELEFONO VARCHAR(15), " +
                "EMAIL VARCHAR(100), " +
                "PAIS VARCHAR(50)" +
                ")";

        Statement statement = null;
        try {
            statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaMerchandising(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXIST MERCHANDISING (" +
                "ID_PRODUCTO INTEGER, " +
                "TIPO_PRODUCTO ENUM('merchandising') NOT NULL, " +  // Especifica que solo acepta merchandising
                "CATEGORIA VARCHAR(100) NOT NULL, " +
                "MATERIAL VARCHAR(100), " +
                "DIMENSIONES VARCHAR(50), " +
                "PESO DECIMAL(5, 2), " +
                "ID_PROVEEDOR INTEGER, " +
                "PRIMARY KEY (ID_PRODUCTO, TIPO_PRODUCTO), " +
                "FOREIGN KEY (ID_PRODUCTO, TIPO_PRODUCTO) REFERENCES PRODUCTO(ID_PRODUCTO, TIPO_PRODUCTO)" +
                ")";

        Statement statement = null;
        try {
            statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaVideojuegos(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXIST VIDEOJUEGO (" +
                "ID_PRODUCTO INTEGER, " +
                "TIPO_PRODUCTO ENUM('videojuego') NOT NULL, " +  // Especifica que solo acepta videojuegos
                "FECHA_LANZAMIENTO DATE, " +
                "CLASIFICACION_EDAD VARCHAR(10), " +
                "ID_PROVEEDOR INTEGER, " +
                "PRIMARY KEY (ID_PRODUCTO, TIPO_PRODUCTO), " +
                "FOREIGN KEY (ID_PRODUCTO, TIPO_PRODUCTO) REFERENCES PRODUCTO(ID_PRODUCTO, TIPO_PRODUCTO)" +
                ")";

        Statement statement = null;
        try {
            statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaConsolas(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXIST CONSOLA (" +
                "ID_PRODUCTO INTEGER, " +
                "TIPO_PRODUCTO ENUM('consola') NOT NULL, " +  // Especifica que solo acepta consolas
                "MARCA VARCHAR(100) NOT NULL, " +
                "MODELO VARCHAR(100) NOT NULL, " +
                "CAPACIDAD_ALMACENAMIENTO VARCHAR(50), " +
                "COLOR VARCHAR(50), " +
                "NUM_MANDOS_INCLUIDOS INTEGER, " +
                "ID_PROVEEDOR INTEGER, " +
                "PRIMARY KEY (ID_PRODUCTO, TIPO_PRODUCTO), " +
                "FOREIGN KEY (ID_PRODUCTO, TIPO_PRODUCTO) REFERENCES PRODUCTO(ID_PRODUCTO, TIPO_PRODUCTO)" +
                ")";

        Statement statement = null;
        try {
            statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaPlataforma(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXIST PLATAFORMA (" +
                "ID_PLATAFORMA SERIAL PRIMARY KEY, " +
                "NOMBRE VARCHAR(50) NOT NULL UNIQUE" +
                ")";

        Statement statement = null;
        try {
            statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaGenero(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXIST GENERO (" +
                "ID_GENERO SERIAL PRIMARY KEY, " +
                "NOMBRE VARCHAR(50) NOT NULL UNIQUE" +
                ")";

        Statement statement = null;
        try {
            statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaVideojuegoPlataforma(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXIST VIDEOJUEGO_PLATAFORMA (" +
                "ID_PRODUCTO INTEGER NOT NULL, " +
                "ID_PLATAFORMA INTEGER NOT NULL, " +
                "PRIMARY KEY (ID_PRODUCTO, ID_PLATAFORMA), " +
                "FOREIGN KEY (ID_PRODUCTO) REFERENCES VIDEOJUEGO(ID_PRODUCTO), " +
                "FOREIGN KEY (ID_PLATAFORMA) REFERENCES PLATAFORMA(ID_PLATAFORMA)" +
                ")";

        Statement statement = null;
        try {
            statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaVideojuegoGenero(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXIST VIDEOJUEGO_GENERO (" +
                "ID_PRODUCTO INTEGER NOT NULL, " +
                "ID_GENERO INTEGER NOT NULL, " +
                "PRIMARY KEY (ID_PRODUCTO, ID_GENERO), " +
                "FOREIGN KEY (ID_PRODUCTO) REFERENCES VIDEOJUEGO(ID_PRODUCTO), " +
                "FOREIGN KEY (ID_GENERO) REFERENCES GENERO(ID_GENERO)" +
                ")";

        Statement statement = null;
        try {
            statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
