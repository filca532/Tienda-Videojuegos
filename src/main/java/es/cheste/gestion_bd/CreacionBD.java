package es.cheste;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreacionBD {
    private static final Logger LOGGER = LogManager.getRootLogger();

    public CreacionBD() {
        super();
    }

    public void crearTablas(ConexionBD conexionBD) {
        crearTablaCliente(conexionBD);
        crearTablaMiembros(conexionBD);
        crearTablaEmpleados(conexionBD);
        crearTablaFactura(conexionBD);
        crearTablaProducto(conexionBD);
        crearTablaFacturaLinea(conexionBD);
        crearTablaProveedor(conexionBD);
        crearTablaMerchandising(conexionBD);
        crearTablaVideojuegos(conexionBD);
        crearTablaConsolas(conexionBD);
        crearTablaPlataforma(conexionBD);
        crearTablaGenero(conexionBD);
        crearTablaVideojuegoPlataforma(conexionBD);
        crearTablaVideojuegoGenero(conexionBD);
    }

    public void insertarDatos(ConexionBD conexionBD) {
        insertarClientes(conexionBD);
        insertarMiembros(conexionBD);
        insertarEmpleados(conexionBD);
        insertarProveedores(conexionBD);
        insertarProductos(conexionBD);
        insertarFacturas(conexionBD);
        insertarFacturaLineas(conexionBD);
        insertarMerchandising(conexionBD);
        insertarVideojuegos(conexionBD);
        insertarConsolas(conexionBD);
        insertarPlataformas(conexionBD);
        insertarGeneros(conexionBD);
        insertarVideojuegoPlataformas(conexionBD);
        insertarVideojuegoGeneros(conexionBD);
    }

    private int comprobarTablaIsVacia(ConexionBD conexionBD, String nombreTabla) {
        String sql = "SELECT COUNT(*) AS total FROM " + nombreTabla;
        int count = 0;
        try (Statement statement = conexionBD.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return count;
    }

    private void insertarClientes(ConexionBD conexionBD) {
        String sql = "INSERT INTO CLIENTE (NOMBRE, APELLIDOS, EMAIL, TELEFONO, DIRECCION, FECHA_REGISTRO) VALUES " +
                "('Juan', 'Pérez', 'juan.perez@example.com', '123456789', 'Calle Falsa 123, Madrid', '2023-10-01'), " +
                "('Ana', 'García', 'ana.garcia@example.com', '987654321', 'Avda. de la Luz 45, Barcelona', '2023-09-15'), " +
                "('Luis', 'Martínez', 'luis.martinez@example.com', '555888999', 'Gran Vía 12, Valencia', '2023-08-20'), " +
                "('Laura', 'Torres', 'laura.torres@example.com', '123123123', 'Calle Real 10, Sevilla', '2023-07-15'), " +
                "('Miguel', 'Santos', 'miguel.santos@example.com', '456456456', 'Paseo del Prado 42, Madrid', '2023-06-30'), " +
                "('Sara', 'Mendez', 'sara.mendez@example.com', '789789789', 'Calle Libertad 15, Valencia', '2023-05-20'), " +
                "('Alberto', 'Gomez', 'alberto.gomez@example.com', '1011121314', 'Calle Mayor 8, Sevilla', '2023-04-12'), " +
                "('Elena', 'Fernández', 'elena.fernandez@example.com', '555555555', 'Avenida Central 22, Málaga', '2023-03-01'), " +
                "('Pablo', 'Rodríguez', 'pablo.rodriguez@example.com', '666666666', 'Paseo de Gracia 5, Barcelona', '2023-02-18'), " +
                "('Carla', 'Lopez', 'carla.lopez@example.com', '777777777', 'Calle Sol 45, Bilbao', '2023-01-25'), " +
                "('Pedro', 'Moreno', 'pedro.moreno@example.com', '888888888', 'Avenida del Mar 7, Valencia', '2022-12-10'), " +
                "('Marta', 'Jiménez', 'marta.jimenez@example.com', '999999999', 'Calle Norte 15, Madrid', '2022-11-21'), " +
                "('Daniel', 'Ruiz', 'daniel.ruiz@example.com', '101010101', 'Calle Este 18, Sevilla', '2022-10-15'), " +
                "('Sandra', 'Muñoz', 'sandra.munoz@example.com', '121212121', 'Avenida Sur 12, Zaragoza', '2022-09-30'), " +
                "('David', 'Ortiz', 'david.ortiz@example.com', '131313131', 'Calle Oeste 3, Barcelona', '2022-08-20'), " +
                "('Cristina', 'Ramírez', 'cristina.ramirez@example.com', '141414141', 'Calle del Río 9, Bilbao', '2022-07-11'), " +
                "('Carlos', 'Gil', 'carlos.gil@example.com', '151515151', 'Avenida del Lago 4, Valencia', '2022-06-05'), " +
                "('Isabel', 'Herrera', 'isabel.herrera@example.com', '161616161', 'Calle Colina 14, Madrid', '2022-05-02');";
        try {
            if (comprobarTablaIsVacia(conexionBD, "CLIENTE") == 0) {
                Statement statement = conexionBD.getConnection().createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void insertarMiembros(ConexionBD conexionBD) {
        String sql = "INSERT INTO MIEMBRO (ID_CLIENTE, TIPO_MIEMBRO, NIVEL_MEMBRESIA, FECHA_INGRESO) VALUES " +
                "(1, 'Premium', 'Oro', '2023-10-05'), " +
                "(2, 'Estándar', 'Plata', '2023-09-20'), " +
                "(3, 'VIP', 'Platino', '2023-08-25'), " +
                "(4, 'Premium', 'Oro', '2023-07-20'), " +
                "(5, 'Estándar', 'Plata', '2023-06-18'), " +
                "(6, 'VIP', 'Platino', '2023-05-22'), " +
                "(8, 'Estándar', 'Bronce', '2023-03-10'), " +
                "(10, 'VIP', 'Platino', '2023-01-15'), " +
                "(12, 'Estándar', 'Plata', '2022-12-05'), " +
                "(15, 'Premium', 'Oro', '2022-08-30');";
        try {
            if (comprobarTablaIsVacia(conexionBD, "MIEMBRO") == 0) {
                Statement statement = conexionBD.getConnection().createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void insertarEmpleados(ConexionBD conexionBD) {
        String sql = "INSERT INTO EMPLEADO (NOMBRE, APELLIDOS, EMAIL, TELEFONO, PUESTO, SALARIO, FECHA_CONTRATACION, DESCUENTO_EMPLEADO) VALUES " +
                "('Carlos', 'Lopez', 'carlos.lopez@empresa.com', '666777888', 'Gerente', 3000.50, '2021-01-15', 10.0), " +
                "('Maria', 'Suarez', 'maria.suarez@empresa.com', '654321987', 'Vendedor', 1500.75, '2022-05-10', 5.0), " +
                "('Laura', 'Fernandez', 'laura.fernandez@empresa.com', '222333444', 'Atención al cliente', 1300.25, '2022-04-14', 4.5), " +
                "('Javier', 'Lozano', 'javier.lozano@empresa.com', '444555666', 'Administrador', 2000.75, '2020-12-11', 8.0), " +
                "('Sara', 'Rodriguez', 'sara.rodriguez@empresa.com', '777888999', 'Supervisor', 2500.00, '2019-09-08', 7.5), " +
                "('Miguel', 'Hernandez', 'miguel.hernandez@empresa.com', '111222333', 'Vendedor', 1500.75, '2022-07-18', 5.0), " +
                "('Luis', 'Martinez', 'luis.martinez@empresa.com', '333444555', 'Desarrollador', 2800.00, '2021-03-25', 6.5), " +
                "('Ana', 'Vega', 'ana.vega@empresa.com', '555666777', 'Recepcionista', 1400.50, '2020-06-01', 4.0), " +
                "('David', 'Morales', 'david.morales@empresa.com', '888999000', 'Técnico de soporte', 1600.00, '2023-02-15', 3.5), " +
                "('Isabel', 'Ruiz', 'isabel.ruiz@empresa.com', '222111333', 'Encargada de logística', 2200.30, '2021-11-10', 6.0), " +
                "('Fernando', 'Gutierrez', 'fernando.gutierrez@empresa.com', '999000111', 'Analista', 2750.45, '2021-08-23', 7.0);";
        try {
            if (comprobarTablaIsVacia(conexionBD, "EMPLEADO") == 0) {
                Statement statement = conexionBD.getConnection().createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void insertarProveedores(ConexionBD conexionBD) {
        String sql = "INSERT INTO PROVEEDOR (NOMBRE, DIRECCION, TELEFONO, EMAIL, PAIS) VALUES " +
                "('Tech Corp', 'Calle Informática 45, Madrid', '555123456', 'contact@techcorp.com', 'España'), " +
                "('GamerWorld', 'Avenida Juegos 21, Barcelona', '555789012', 'info@gamerworld.com', 'España'), " +
                "('Tech Solutions', 'Calle Tecnología 78, Bilbao', '654321234', 'contact@techsolutions.com', 'España'), " +
                "('WorldGames', 'Avenida Jugones 30, Valencia', '765432123', 'support@worldgames.com', 'España'), " +
                "('GameHouse', 'Calle Consolas 12, Sevilla', '555333444', 'ventas@gamehouse.com', 'España'), " +
                "('Gaming Zone', 'Paseo Digital 56, Málaga', '666111222', 'info@gamingzone.com', 'España'), " +
                "('PlayStore', 'Calle Principal 44, Zaragoza', '777888999', 'contacto@playstore.com', 'España'), " +
                "('MediaTech', 'Avenida Media 32, Murcia', '888777666', 'media@mediatech.com', 'España'), " +
                "('Future Gaming', 'Calle Futuro 11, Madrid', '999555444', 'future@futuregaming.com', 'España'), " +
                "('Gamer Factory', 'Calle Juegos 23, Granada', '333222111', 'support@gamerfactory.com', 'España');";
        try {
            if (comprobarTablaIsVacia(conexionBD, "PROVEEDOR") == 0) {
                Statement statement = conexionBD.getConnection().createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void insertarProductos(ConexionBD conexionBD) {
        String sql = "INSERT INTO PRODUCTO (TIPO_PRODUCTO, NOMBRE, PRECIO, STOCK) VALUES " +
                "('videojuego', 'FIFA 23', 59.99, 100), " +
                "('videojuego', 'Call of Duty: Modern Warfare', 69.99, 75), " +
                "('videojuego', 'Minecraft', 29.99, 150), " +
                "('videojuego', 'The Legend of Zelda: Breath of the Wild', 59.99, 60), " +
                "('videojuego', 'Cyberpunk 2077', 49.99, 80), " +
                "('consola', 'PlayStation 5', 499.99, 50), " +
                "('consola', 'Xbox Series X', 499.99, 45), " +
                "('consola', 'Nintendo Switch', 299.99, 70), " +
                "('consola', 'PlayStation 4', 299.99, 30), " +
                "('merchandising', 'Camiseta Call of Duty', 19.99, 200), " +
                "('merchandising', 'Taza Mario Bros', 9.99, 150), " +
                "('merchandising', 'Póster The Witcher', 14.99, 100), " +
                "('merchandising', 'Gorra Fortnite', 12.99, 90), " +
                "('merchandising', 'Mochila Assassin’s Creed', 24.99, 75);";
        try {
            if (comprobarTablaIsVacia(conexionBD, "PRODUCTO") == 0) {
                Statement statement = conexionBD.getConnection().createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void insertarFacturas(ConexionBD conexionBD) {
        String sql = "INSERT INTO FACTURA (ID_CLIENTE, ID_EMPLEADO, FECHA_PEDIDO, TOTAL_PEDIDO, METODO_PAGO) VALUES " +
                "(1, 1, '2023-10-10', 120.75, 'Tarjeta'), " +
                "(2, 2, '2023-09-20', 80.50, 'Efectivo'), " +
                "(3, 3, '2023-08-15', 150.00, 'Tarjeta'), " +
                "(4, 4, '2023-07-30', 60.00, 'Tarjeta'), " +
                "(5, 1, '2023-06-12', 200.00, 'Transferencia'), " +
                "(6, 2, '2023-05-18', 175.50, 'Tarjeta'), " +
                "(7, 3, '2023-04-22', 220.75, 'Efectivo'), " +
                "(1, 2, '2023-03-05', 99.99, 'Transferencia'), " +
                "(2, 4, '2023-02-15', 180.30, 'Tarjeta'), " +
                "(3, 1, '2023-01-28', 250.40, 'Efectivo'), " +
                "(4, 2, '2023-10-14', 95.20, 'Tarjeta'), " +
                "(5, 3, '2023-09-11', 105.00, 'Transferencia'), " +
                "(6, 4, '2023-08-02', 300.75, 'Efectivo'), " +
                "(7, 1, '2023-07-22', 60.50, 'Tarjeta'), " +
                "(1, 3, '2023-06-18', 120.20, 'Transferencia'), " +
                "(3, 2, '2023-05-13', 45.00, 'Tarjeta'), " +
                "(4, 4, '2023-04-10', 210.99, 'Efectivo'), " +
                "(5, 1, '2023-03-27', 99.50, 'Transferencia'), " +
                "(6, 3, '2023-02-14', 150.60, 'Tarjeta'), " +
                "(7, 2, '2023-01-25', 135.75, 'Efectivo');";
        try {
            if (comprobarTablaIsVacia(conexionBD, "FACTURA") == 0) {
                Statement statement = conexionBD.getConnection().createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void insertarFacturaLineas(ConexionBD conexionBD) {
        String sql = "INSERT INTO FACTURA_LINEA (ID_FACTURA, ID_PRODUCTO, TIPO_PRODUCTO, CANTIDAD, PRECIO_UNITARIO) VALUES " +
                "(1, 1, 'videojuego', 1, 59.99), " +
                "(1, 10, 'merchandising', 2, 19.99), " +
                "(1, 2, 'videojuego', 1, 69.99), " +
                "(2, 6, 'consola', 1, 499.99), " +
                "(2, 10, 'merchandising', 3, 19.99), " +
                "(3, 5, 'videojuego', 2, 49.99), " +
                "(3, 3, 'videojuego', 1, 29.99), " +
                "(4, 8, 'consola', 1, 299.99), " +
                "(4, 12, 'merchandising', 4, 12.99), " +
                "(5, 2, 'videojuego', 1, 69.99), " +
                "(5, 11, 'merchandising', 1, 9.99), " +
                "(6, 5, 'videojuego', 1, 49.99), " +
                "(6, 1, 'videojuego', 1, 59.99), " +
                "(7, 3, 'videojuego', 1, 29.99), " +
                "(7, 6, 'consola', 1, 499.99), " +
                "(8, 13, 'merchandising', 5, 24.99), " +
                "(9, 7, 'consola', 2, 299.99), " +
                "(10, 4, 'videojuego', 1, 59.99), " +
                "(10, 5, 'videojuego', 2, 49.99), " +
                "(11, 9, 'consola', 1, 299.99), " +
                "(11, 10, 'merchandising', 1, 15.99), " +
                "(12, 6, 'consola', 1, 499.99), " +
                "(13, 3, 'videojuego', 3, 29.99), " +
                "(14, 1, 'videojuego', 1, 59.99), " +
                "(15, 13, 'merchandising', 4, 24.99), " +
                "(16, 2, 'videojuego', 2, 69.99), " +
                "(17, 5, 'videojuego', 1, 49.99), " +
                "(18, 7, 'consola', 2, 299.99), " +
                "(19, 9, 'consola', 1, 299.99), " +
                "(20, 10, 'merchandising', 3, 15.99);";

        try {
            if (comprobarTablaIsVacia(conexionBD, "FACTURA_LINEA") == 0) {
                Statement statement = conexionBD.getConnection().createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void insertarMerchandising(ConexionBD conexionBD) {
        String sql = "INSERT INTO MERCHANDISING (ID_PRODUCTO, TIPO_PRODUCTO, CATEGORIA, MATERIAL, DIMENSIONES, PESO, ID_PROVEEDOR) VALUES " +
                "(10, 'merchandising', 'Ropa', 'Algodón', 'L', 0.2, 1), " +
                "(11, 'merchandising', 'Accesorio', 'Cerámica', '10x10 cm', 0.3, 2), " +
                "(12, 'merchandising', 'Póster', 'Papel', '60x90 cm', 0.1, 3), " +
                "(13, 'merchandising', 'Accesorio', 'Algodón', 'Talla Única', 0.1, 4), " +
                "(14, 'merchandising', 'Accesorio', 'Poliéster', '40x30 cm', 0.4, 1);";
        try {
            if (comprobarTablaIsVacia(conexionBD, "MERCHANDISING") == 0) {
                Statement statement = conexionBD.getConnection().createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void insertarVideojuegos(ConexionBD conexionBD) {
        String sql = "INSERT INTO VIDEOJUEGO (ID_PRODUCTO, TIPO_PRODUCTO, FECHA_LANZAMIENTO, CLASIFICACION_EDAD, ID_PROVEEDOR) VALUES " +
                "(1, 'videojuego', '2022-09-30', '3+', 2), " +
                "(2, 'videojuego', '2021-11-05', '18+', 3), " +
                "(3, 'videojuego', '2011-11-18', '7+', 1), " +
                "(4, 'videojuego', '2017-03-03', '12+', 4), " +
                "(5, 'videojuego', '2020-12-10', '18+', 2);";
        try {
            if (comprobarTablaIsVacia(conexionBD, "VIDEOJUEGO") == 0) {
                Statement statement = conexionBD.getConnection().createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void insertarConsolas(ConexionBD conexionBD) {
        String sql = "INSERT INTO CONSOLA (ID_PRODUCTO, TIPO_PRODUCTO, MARCA, MODELO, CAPACIDAD_ALMACENAMIENTO, COLOR, NUM_MANDOS_INCLUIDOS, ID_PROVEEDOR) VALUES " +
                "(6, 'consola', 'Sony', 'PlayStation 5', '825GB', 'Blanco', 1, 1), " +
                "(7, 'consola', 'Microsoft', 'Xbox Series X', '1TB', 'Negro', 1, 2), " +
                "(8, 'consola', 'Nintendo', 'Switch', '32GB', 'Azul/Rojo', 2, 3), " +
                "(9, 'consola', 'Sony', 'PlayStation 4', '500GB', 'Negro', 1, 1);";
        try {
            if (comprobarTablaIsVacia(conexionBD, "CONSOLA") == 0) {
                Statement statement = conexionBD.getConnection().createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void insertarPlataformas(ConexionBD conexionBD) {
        String sql = "INSERT INTO PLATAFORMA (NOMBRE) VALUES " +
                "('PC'), " +
                "('Nintendo Switch'), " +
                "('PlayStation 5'), " +
                "('PlayStation 4'), " +
                "('Xbox Series X'), " +
                "('Xbox One'), " +
                "('Nintendo Wii'), " +
                "('Nintendo DS'), " +
                "('Nintendo 3DS'), " +
                "('PlayStation 3'), " +
                "('Xbox 360'), " +
                "('PlayStation 2'), " +
                "('Nintendo GameCube'), " +
                "('Sega Dreamcast');";
        try {
            if (comprobarTablaIsVacia(conexionBD, "PLATAFORMA") == 0) {
                Statement statement = conexionBD.getConnection().createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void insertarGeneros(ConexionBD conexionBD) {
        String sql = "INSERT INTO GENERO (NOMBRE) VALUES " +
                "('Acción'), " +
                "('Aventura'), " +
                "('Deporte'), " +
                "('Rol'), " +
                "('Simulación'), " +
                "('Estrategia'), " +
                "('Carreras'), " +
                "('Puzzle'), " +
                "('Terror'), " +
                "('Arcade'), " +
                "('Educativo'), " +
                "('Musical'), " +
                "('Plataformas'), " +
                "('Survival'), " +
                "('Ciencia Ficción'), " +
                "('Multijugador Masivo'), " +
                "('Sandbox'), " +
                "('Shooters'), " +
                "('Batalla Real'), " +
                "('Realidad Virtual');";
        try {
            if (comprobarTablaIsVacia(conexionBD, "GENERO") == 0) {
                Statement statement = conexionBD.getConnection().createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void insertarVideojuegoPlataformas(ConexionBD conexionBD) {
        String sql = "INSERT INTO VIDEOJUEGO_PLATAFORMA (ID_PRODUCTO, TIPO_PRODUCTO, ID_PLATAFORMA) VALUES " +
                "(1, 'videojuego', 3), " +
                "(1, 'videojuego', 4), " +
                "(2, 'videojuego', 5), " +
                "(2, 'videojuego', 6), " +
                "(2, 'videojuego', 3), " +
                "(3, 'videojuego', 1), " +
                "(3, 'videojuego', 2), " +
                "(3, 'videojuego', 4), " +
                "(4, 'videojuego', 2), " +
                "(5, 'videojuego', 3), " +
                "(5, 'videojuego', 5), " +
                "(5, 'videojuego', 1);";
        try {
            if (comprobarTablaIsVacia(conexionBD, "VIDEOJUEGO_PLATAFORMA") == 0) {
                Statement statement = conexionBD.getConnection().createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void insertarVideojuegoGeneros(ConexionBD conexionBD) {
        String sql = "INSERT INTO VIDEOJUEGO_GENERO (ID_PRODUCTO, TIPO_PRODUCTO, ID_GENERO) VALUES " +
                "(1, 'videojuego', 3), " +
                "(2, 'videojuego', 1), " +
                "(2, 'videojuego', 18), " +
                "(3, 'videojuego', 2), " +
                "(3, 'videojuego', 17), " +
                "(4, 'videojuego', 2), " +
                "(4, 'videojuego', 4), " +
                "(5, 'videojuego', 1), " +
                "(5, 'videojuego', 4), " +
                "(5, 'videojuego', 15);";
        try {
            if (comprobarTablaIsVacia(conexionBD, "VIDEOJUEGO_GENERO") == 0) {
                Statement statement = conexionBD.getConnection().createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaCliente(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXISTS CLIENTE (" +
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
        String sql = "CREATE TABLE IF NOT EXISTS MIEMBRO (" +
                "ID_MIEMBRO SERIAL PRIMARY KEY, " +
                "ID_CLIENTE INTEGER NOT NULL UNIQUE, " +
                "TIPO_MIEMBRO VARCHAR(50) NOT NULL, " +
                "NIVEL_MEMBRESIA VARCHAR(50), " +
                "FECHA_INGRESO DATE NOT NULL, " +
                "FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE)" +
                ")";

        try {
            Statement statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaEmpleados(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXISTS EMPLEADO (" +
                "ID_EMPLEADO SERIAL PRIMARY KEY, " +
                "NOMBRE VARCHAR(100) NOT NULL, " +
                "APELLIDOS VARCHAR(100) NOT NULL, " +
                "EMAIL VARCHAR(100) NOT NULL UNIQUE, " +
                "TELEFONO VARCHAR(15) NOT NULL UNIQUE, " +
                "PUESTO VARCHAR(100) NOT NULL, " +
                "SALARIO DECIMAL(8, 2) NOT NULL, " +
                "FECHA_CONTRATACION DATE NOT NULL, " +
                "DESCUENTO_EMPLEADO DECIMAL(5,2))";

        try {
            Statement statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaFactura(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXISTS FACTURA (" +
                "ID_FACTURA SERIAL PRIMARY KEY, " +
                "ID_CLIENTE INTEGER NOT NULL, " +
                "ID_EMPLEADO INTEGER NOT NULL, " +
                "FECHA_PEDIDO DATE NOT NULL, " +
                "TOTAL_PEDIDO DECIMAL(10, 2) NOT NULL, " +
                "METODO_PAGO VARCHAR(50), " +
                "FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE), " +
                "FOREIGN KEY (ID_EMPLEADO) REFERENCES EMPLEADO(ID_EMPLEADO)" +
                ")";

        try {
            Statement statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaProducto(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXISTS PRODUCTO (" +
                "ID_PRODUCTO SERIAL, " +
                "TIPO_PRODUCTO VARCHAR(50) NOT NULL, " +
                "NOMBRE VARCHAR(100) NOT NULL, " +
                "PRECIO DECIMAL(10, 2) NOT NULL, " +
                "STOCK INTEGER NOT NULL, " +
                "PRIMARY KEY (ID_PRODUCTO, TIPO_PRODUCTO)" +
                ")";

        try {
            Statement statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaFacturaLinea(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXISTS FACTURA_LINEA (" +
                "ID_FACTURA INTEGER NOT NULL, " +
                "ID_FACTURA_LINEA SERIAL, " +
                "ID_PRODUCTO INTEGER NOT NULL, " +
                "TIPO_PRODUCTO VARCHAR(50) NOT NULL, " +
                "CANTIDAD INTEGER NOT NULL, " +
                "PRECIO_UNITARIO DECIMAL(10, 2) NOT NULL, " +
                "PRIMARY KEY (ID_FACTURA, ID_FACTURA_LINEA), " +
                "FOREIGN KEY (ID_FACTURA) REFERENCES FACTURA(ID_FACTURA), " +
                "FOREIGN KEY (ID_PRODUCTO, TIPO_PRODUCTO) REFERENCES PRODUCTO(ID_PRODUCTO, TIPO_PRODUCTO)" +
                ")";

        try {
            Statement statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaProveedor(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXISTS PROVEEDOR (" +
                "ID_PROVEEDOR SERIAL PRIMARY KEY, " +
                "NOMBRE VARCHAR(100) NOT NULL, " +
                "DIRECCION VARCHAR(200), " +
                "TELEFONO VARCHAR(15), " +
                "EMAIL VARCHAR(100), " +
                "PAIS VARCHAR(50)" +
                ")";

        try {
            Statement statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaMerchandising(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXISTS MERCHANDISING (" +
                "ID_PRODUCTO INTEGER, " +
                "TIPO_PRODUCTO VARCHAR(50) NOT NULL, " +
                "CATEGORIA VARCHAR(100) NOT NULL, " +
                "MATERIAL VARCHAR(100), " +
                "DIMENSIONES VARCHAR(50), " +
                "PESO DECIMAL(5, 2), " +
                "ID_PROVEEDOR INTEGER, " +
                "PRIMARY KEY (ID_PRODUCTO, TIPO_PRODUCTO), " +
                "FOREIGN KEY (ID_PROVEEDOR) REFERENCES PROVEEDOR(ID_PROVEEDOR), " +
                "FOREIGN KEY (ID_PRODUCTO, TIPO_PRODUCTO) REFERENCES PRODUCTO(ID_PRODUCTO, TIPO_PRODUCTO)" +
                ")";

        try {
            Statement statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaVideojuegos(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXISTS VIDEOJUEGO (" +
                "ID_PRODUCTO INTEGER NOT NULL, " +
                "TIPO_PRODUCTO VARCHAR(50) NOT NULL, " +
                "FECHA_LANZAMIENTO DATE, " +
                "CLASIFICACION_EDAD VARCHAR(10), " +
                "ID_PROVEEDOR INTEGER, " +
                "PRIMARY KEY (ID_PRODUCTO, TIPO_PRODUCTO), " +
                "FOREIGN KEY (ID_PROVEEDOR) REFERENCES PROVEEDOR(ID_PROVEEDOR), " +
                "FOREIGN KEY (ID_PRODUCTO, TIPO_PRODUCTO) REFERENCES PRODUCTO(ID_PRODUCTO, TIPO_PRODUCTO)" +
                ")";

        try {
            Statement statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaConsolas(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXISTS CONSOLA (" +
                "ID_PRODUCTO INTEGER, " +
                "TIPO_PRODUCTO VARCHAR(50) NOT NULL, " +
                "MARCA VARCHAR(100) NOT NULL, " +
                "MODELO VARCHAR(100) NOT NULL, " +
                "CAPACIDAD_ALMACENAMIENTO VARCHAR(50), " +
                "COLOR VARCHAR(50), " +
                "NUM_MANDOS_INCLUIDOS INTEGER, " +
                "ID_PROVEEDOR INTEGER, " +
                "PRIMARY KEY (ID_PRODUCTO, TIPO_PRODUCTO), " +
                "FOREIGN KEY (ID_PROVEEDOR) REFERENCES PROVEEDOR(ID_PROVEEDOR), " +
                "FOREIGN KEY (ID_PRODUCTO, TIPO_PRODUCTO) REFERENCES PRODUCTO(ID_PRODUCTO, TIPO_PRODUCTO)" +
                ")";

        try {
            Statement statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaPlataforma(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXISTS PLATAFORMA (" +
                "ID_PLATAFORMA SERIAL PRIMARY KEY, " +
                "NOMBRE VARCHAR(50) NOT NULL UNIQUE" +
                ")";

        try {
            Statement statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaGenero(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXISTS GENERO (" +
                "ID_GENERO SERIAL PRIMARY KEY, " +
                "NOMBRE VARCHAR(50) NOT NULL UNIQUE" +
                ")";

        try {
            Statement statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaVideojuegoPlataforma(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXISTS VIDEOJUEGO_PLATAFORMA (" +
                "ID_PRODUCTO INTEGER NOT NULL, " +
                "TIPO_PRODUCTO VARCHAR(50) NOT NULL, " +
                "ID_PLATAFORMA INTEGER NOT NULL, " +
                "PRIMARY KEY (ID_PRODUCTO, TIPO_PRODUCTO, ID_PLATAFORMA), " +
                "FOREIGN KEY (ID_PRODUCTO, TIPO_PRODUCTO) REFERENCES VIDEOJUEGO(ID_PRODUCTO, TIPO_PRODUCTO), " +
                "FOREIGN KEY (ID_PLATAFORMA) REFERENCES PLATAFORMA(ID_PLATAFORMA)" +
                ")";

        try {
            Statement statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void crearTablaVideojuegoGenero(ConexionBD conexionBD) {
        String sql = "CREATE TABLE IF NOT EXISTS VIDEOJUEGO_GENERO (" +
                "ID_PRODUCTO INTEGER NOT NULL, " +
                "TIPO_PRODUCTO VARCHAR(50) NOT NULL, " +
                "ID_GENERO INTEGER NOT NULL, " +
                "PRIMARY KEY (ID_PRODUCTO, TIPO_PRODUCTO, ID_GENERO), " +
                "FOREIGN KEY (ID_PRODUCTO, TIPO_PRODUCTO) REFERENCES VIDEOJUEGO(ID_PRODUCTO, TIPO_PRODUCTO), " +
                "FOREIGN KEY (ID_GENERO) REFERENCES GENERO(ID_GENERO)" +
                ")";

        try {
            Statement statement = conexionBD.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}