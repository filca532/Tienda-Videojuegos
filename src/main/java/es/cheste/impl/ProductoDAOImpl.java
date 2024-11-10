package es.cheste.impl;

import es.cheste.gestion_bd.ConexionBD;
import es.cheste.DAO.ProductoDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Producto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz ProductoDAO para gestionar las operaciones CRUD de Producto.
 */
public class ProductoDAOImpl implements ProductoDAO {
    private static final Logger LOGGER = LogManager.getRootLogger();

    // Consultas SQL
    private static final String INSERTAR = "INSERT INTO PRODUCTO (TIPO_PRODUCTO, NOMBRE, PRECIO, STOCK) VALUES (?, ?, ?, ?)";
    private static final String OBTENER_POR_ID = "SELECT * FROM PRODUCTO WHERE ID_PRODUCTO = ?";
    private static final String OBTENER_TODOS = "SELECT * FROM PRODUCTO";
    private static final String OBTENER_POR_RANGO_STOCK = "SELECT * FROM PRODUCTO WHERE STOCK BETWEEN ? AND ?";
    private static final String OBTENER_POR_RANGO_PRECIO = "SELECT * FROM PRODUCTO WHERE PRECIO BETWEEN ? AND ?";
    private static final String OBTENER_POR_TIPO = "SELECT * FROM PRODUCTO WHERE TIPO_PRODUCTO = ?";
    private static final String ACTUALIZAR = "UPDATE PRODUCTO SET TIPO_PRODUCTO = ?, NOMBRE = ?, PRECIO = ?, STOCK = ? WHERE ID_PRODUCTO = ?";
    private static final String ELIMINAR = "DELETE FROM PRODUCTO WHERE ID_PRODUCTO = ?";

    ConexionBD conexionBD = new ConexionBD();

    /**
     * Inserta un nuevo Producto en la base de datos.
     *
     * @param producto El Producto a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertarProducto(Producto producto) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, producto.getTipoProducto());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de producto fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al insertar el producto.", e);
        }
    }

    /**
     * Obtiene un Producto por su ID.
     *
     * @param id El ID del producto.
     * @return El Producto obtenido.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public Producto obtenerPorID(int id) throws DAOException {
        Producto producto = null;

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_ID)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto = mapearProducto(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener el producto por ID.", e);
        }

        return producto;
    }

    /**
     * Obtiene todos los Producto de la base de datos.
     *
     * @return Una lista de todos los Producto.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Producto> obtenerTodos() throws DAOException {
        List<Producto> productos = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_TODOS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todos los productos.", e);
        }

        return productos;
    }

    /**
     * Obtiene los Producto por rango de stock.
     *
     * @param stockInicial El stock inicial.
     * @param stockFinal El stock final.
     * @return Una lista de Producto dentro del rango de stock.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Producto> obtenerProductosPorRangoStock(int stockInicial, int stockFinal) throws DAOException {
        List<Producto> productos = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_RANGO_STOCK)) {
            ps.setInt(1, stockInicial);
            ps.setInt(2, stockFinal);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapearProducto(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener los productos por rango de stock.", e);
        }

        return productos;
    }

    /**
     * Obtiene los Producto por rango de precio.
     *
     * @param precioMin El precio mínimo.
     * @param precioMax El precio máximo.
     * @return Una lista de Producto dentro del rango de precio.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Producto> obtenerProductosPorRangoPrecio(double precioMin, double precioMax) throws DAOException {
        List<Producto> productos = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_RANGO_PRECIO)) {
            ps.setDouble(1, precioMin);
            ps.setDouble(2, precioMax);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapearProducto(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener los productos por rango de precio.", e);
        }

        return productos;
    }

    /**
     * Obtiene los Producto por tipo.
     *
     * @param tipoProducto El tipo de producto.
     * @return Una lista de Producto del tipo especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Producto> obtenerProductosPorTipo(String tipoProducto) throws DAOException {
        List<Producto> productos = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_TIPO)) {
            ps.setString(1, tipoProducto);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapearProducto(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener los productos por tipo.", e);
        }

        return productos;
    }

    /**
     * Actualiza un Producto en la base de datos.
     *
     * @param producto El Producto a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(Producto producto) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ACTUALIZAR)) {
            ps.setString(1, producto.getTipoProducto());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getIdProducto());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de producto fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el producto.", e);
        }
    }

    /**
     * Elimina un Producto de la base de datos.
     *
     * @param id El ID del producto.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int id) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ELIMINAR)) {
            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación de producto fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el producto.", e);
        }
    }

    /**
     * Mapea un ResultSet a un objeto Producto.
     *
     * @param rs El ResultSet a mapear.
     * @return El objeto Producto mapeado.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    private Producto mapearProducto(ResultSet rs) throws SQLException {
        int idProducto = rs.getInt("ID_PRODUCTO");
        String tipoProducto = rs.getString("TIPO_PRODUCTO");
        String nombre = rs.getString("NOMBRE");
        double precio = rs.getDouble("PRECIO");
        int stock = rs.getInt("STOCK");

        return new Producto(idProducto, tipoProducto, nombre, precio, stock);
    }
}