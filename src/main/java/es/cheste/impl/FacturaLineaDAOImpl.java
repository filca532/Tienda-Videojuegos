package es.cheste.impl;

import es.cheste.DAO.FacturaLineaDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.gestion_bd.ConexionBD;
import es.cheste.objetos.FacturaLinea;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz FacturaLineaDAO para gestionar las operaciones CRUD de FacturaLinea.
 */
public class FacturaLineaDAOImpl implements FacturaLineaDAO {
    private static final Logger LOGGER = LogManager.getRootLogger();

    // Consultas SQL
    private static final String INSERTAR = "INSERT INTO FACTURA_LINEA (ID_FACTURA, ID_FACTURA_LINEA, ID_PRODUCTO, TIPO_PRODUCTO, CANTIDAD, PRECIO_UNITARIO) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String OBTENER_POR_ID = "SELECT * FROM FACTURA_LINEA WHERE ID_FACTURA = ? AND ID_FACTURA_LINEA = ?";
    private static final String OBTENER_TODAS = "SELECT * FROM FACTURA_LINEA";
    private static final String OBTENER_POR_FACTURA = "SELECT * FROM FACTURA_LINEA WHERE ID_FACTURA = ?";
    private static final String OBTENER_POR_PRODUCTO = "SELECT * FROM FACTURA_LINEA WHERE ID_PRODUCTO = ?";
    private static final String OBTENER_POR_CANTIDAD = "SELECT * FROM FACTURA_LINEA WHERE CANTIDAD BETWEEN ? AND ?";
    private static final String OBTENER_POR_PRECIO = "SELECT * FROM FACTURA_LINEA WHERE PRECIO BETWEEN ? AND ?";
    private static final String ACTUALIZAR = "UPDATE FACTURA_LINEA SET ID_PRODUCTO = ?, TIPO_PRODUCTO = ?, CANTIDAD = ?, PRECIO_UNITARIO = ? WHERE ID_FACTURA = ? AND ID_FACTURA_LINEA = ?";
    private static final String ELIMINAR = "DELETE FROM FACTURA_LINEA WHERE ID_FACTURA = ? AND ID_FACTURA_LINEA = ?";

    ConexionBD conexionBD = new ConexionBD();

    /**
     * Inserta una nueva FacturaLinea en la base de datos.
     *
     * @param facturaLinea La FacturaLinea a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertarFacturaLinea(FacturaLinea facturaLinea) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, facturaLinea.getIdFactura());
            ps.setInt(2, facturaLinea.getIdFacturaLinea());
            ps.setInt(3, facturaLinea.getIdProducto());
            ps.setString(4, facturaLinea.getTipoProducto());
            ps.setInt(5, facturaLinea.getCantidad());
            ps.setDouble(6, facturaLinea.getPrecioUnitario());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de factura línea fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al insertar la factura línea.", e);
        }
    }

    /**
     * Obtiene una FacturaLinea por su ID.
     *
     * @param idFactura El ID de la factura.
     * @param idFacturaLinea El ID de la línea de factura.
     * @return La FacturaLinea obtenida.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public FacturaLinea obtenerPorID(int idFactura, int idFacturaLinea) throws DAOException {
        FacturaLinea facturaLinea = null;

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_ID)) {
            ps.setInt(1, idFactura);
            ps.setInt(2, idFacturaLinea);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    facturaLinea = mapearFacturaLinea(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener la factura línea por ID.", e);
        }

        return facturaLinea;
    }

    /**
     * Obtiene todas las FacturaLinea de la base de datos.
     *
     * @return Una lista de todas las FacturaLinea.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<FacturaLinea> obtenerTodas() throws DAOException {
        List<FacturaLinea> facturaLineas = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_TODAS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                facturaLineas.add(mapearFacturaLinea(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todas las facturas línea.", e);
        }

        return facturaLineas;
    }

    /**
     * Obtiene las FacturaLinea asociadas a una factura específica.
     *
     * @param facturaId El ID de la factura.
     * @return Una lista de FacturaLinea asociadas a la factura.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<FacturaLinea> obtenerFacturaLineaPorFactura(int facturaId) throws DAOException {
        List<FacturaLinea> facturaLineas = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_FACTURA)) {
            ps.setInt(1, facturaId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    facturaLineas.add(mapearFacturaLinea(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener facturas línea por factura.", e);
        }

        return facturaLineas;
    }

    /**
     * Obtiene las FacturaLinea asociadas a un producto específico.
     *
     * @param productoId El ID del producto.
     * @return Una lista de FacturaLinea asociadas al producto.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<FacturaLinea> obtenerFacturaLineaPorProducto(int productoId) throws DAOException {
        List<FacturaLinea> facturaLineas = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_PRODUCTO)) {
            ps.setInt(1, productoId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    facturaLineas.add(mapearFacturaLinea(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener facturas línea por producto.", e);
        }

        return facturaLineas;
    }

    /**
     * Obtiene las FacturaLinea cuya cantidad está dentro de un rango específico.
     *
     * @param cantidadMin La cantidad mínima.
     * @param cantidadMax La cantidad máxima.
     * @return Una lista de FacturaLinea dentro del rango de cantidad.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<FacturaLinea> obtenerFacturaLineaPorCantidad(int cantidadMin, int cantidadMax) throws DAOException {
        List<FacturaLinea> facturaLineas = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_CANTIDAD)) {
            ps.setInt(1, cantidadMin);
            ps.setInt(2, cantidadMax);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    facturaLineas.add(mapearFacturaLinea(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener facturas línea por cantidad.", e);
        }

        return facturaLineas;
    }

    /**
     * Obtiene las FacturaLinea cuyo precio está dentro de un rango específico.
     *
     * @param precioMin El precio mínimo.
     * @param precioMax El precio máximo.
     * @return Una lista de FacturaLinea dentro del rango de precio.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<FacturaLinea> obtenerFacturaLineaPorPrecio(double precioMin, double precioMax) throws DAOException {
        List<FacturaLinea> facturaLineas = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_PRECIO)) {
            ps.setDouble(1, precioMin);
            ps.setDouble(2, precioMax);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    facturaLineas.add(mapearFacturaLinea(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener facturas línea por precio.", e);
        }

        return facturaLineas;
    }

    /**
     * Actualiza una FacturaLinea en la base de datos.
     *
     * @param facturaLinea La FacturaLinea a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(FacturaLinea facturaLinea) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ACTUALIZAR)) {
            ps.setInt(1, facturaLinea.getIdProducto());
            ps.setString(2, facturaLinea.getTipoProducto());
            ps.setInt(3, facturaLinea.getCantidad());
            ps.setDouble(4, facturaLinea.getPrecioUnitario());
            ps.setInt(5, facturaLinea.getIdFactura());
            ps.setInt(6, facturaLinea.getIdFacturaLinea());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de factura línea fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar la factura línea.", e);
        }
    }

    /**
     * Elimina una FacturaLinea de la base de datos.
     *
     * @param idFactura El ID de la factura.
     * @param idFacturaLinea El ID de la línea de factura.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int idFactura, int idFacturaLinea) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ELIMINAR)) {
            ps.setInt(1, idFactura);
            ps.setInt(2, idFacturaLinea);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación de factura línea fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar la factura línea.", e);
        }
    }

    /**
     * Mapea un ResultSet a un objeto FacturaLinea.
     *
     * @param rs El ResultSet a mapear.
     * @return El objeto FacturaLinea mapeado.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    private FacturaLinea mapearFacturaLinea(ResultSet rs) throws SQLException {
        int idFactura = rs.getInt("ID_FACTURA");
        int idFacturaLinea = rs.getInt("ID_FACTURA_LINEA");
        int idProducto = rs.getInt("ID_PRODUCTO");
        String tipoProducto = rs.getString("TIPO_PRODUCTO");
        int cantidad = rs.getInt("CANTIDAD");
        double precioUnitario = rs.getDouble("PRECIO_UNITARIO");

        return new FacturaLinea(idFactura, idFacturaLinea, idProducto, tipoProducto, cantidad, precioUnitario);
    }
}