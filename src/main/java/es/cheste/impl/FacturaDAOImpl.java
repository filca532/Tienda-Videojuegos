package es.cheste.impl;

import es.cheste.gestion_bd.ConexionBD;
import es.cheste.DAO.FacturaDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Factura;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz FacturaDAO para gestionar las operaciones CRUD de las facturas.
 */
public class FacturaDAOImpl implements FacturaDAO {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String INSERTAR = "INSERT INTO FACTURA (ID_CLIENTE, ID_EMPLEADO, FECHA_PEDIDO, TOTAL_PEDIDO, METODO_PAGO) VALUES (?, ?, ?, ?, ?)";
    private static final String OBTENER_POR_ID = "SELECT * FROM FACTURA WHERE ID_FACTURA = ?";
    private static final String OBTENER_TODAS = "SELECT * FROM FACTURA";
    private static final String OBTENER_POR_CLIENTE = "SELECT * FROM FACTURA WHERE ID_CLIENTE = ?";
    private static final String OBTENER_RANGO_FECHA = "SELECT * FROM FACTURA WHERE FECHA_PEDIDO BETWEEN ? AND ?";
    private static final String OBTENER_POR_METODO_PAGO = "SELECT * FROM FACTURA WHERE METODO_PAGO = ?";
    private static final String OBTENER_POR_MONTO = "SELECT * FROM FACTURA WHERE TOTAL_PEDIDO BETWEEN ? AND ?";
    private static final String ACTUALIZAR = "UPDATE FACTURA SET ID_CLIENTE = ?, ID_EMPLEADO = ?, FECHA_PEDIDO = ?, TOTAL_PEDIDO = ?, METODO_PAGO = ? WHERE ID_FACTURA = ?";
    private static final String ELIMINAR = "DELETE FROM FACTURA WHERE ID_FACTURA = ?";

    ConexionBD conexionBD = new ConexionBD();

    /**
     * Inserta una nueva factura en la base de datos.
     *
     * @param factura La factura a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertarFactura(Factura factura) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, factura.getIdCliente());
            ps.setInt(2, factura.getIdEmpleado());
            ps.setDate(3, new Date(factura.getFechaPedido().getTime()));
            ps.setDouble(4, factura.getTotalPedido());
            ps.setString(5, factura.getMetodoPago());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de factura fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al insertar la factura.", e);
        }
    }

    /**
     * Obtiene una factura por su ID.
     *
     * @param id El ID de la factura a obtener.
     * @return La factura obtenida.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public Factura obtenerPorID(int id) throws DAOException {
        Factura factura = null;

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_ID)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    factura = mapearFactura(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener la factura por ID.", e);
        }

        return factura;
    }

    /**
     * Obtiene todas las facturas.
     *
     * @return Una lista de todas las facturas.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Factura> obtenerTodas() throws DAOException {
        List<Factura> facturas = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_TODAS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                facturas.add(mapearFactura(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todas las facturas.", e);
        }

        return facturas;
    }

    /**
     * Obtiene las facturas por cliente.
     *
     * @param clienteId El ID del cliente cuyas facturas se desean obtener.
     * @return Una lista de facturas del cliente especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Factura> obtenerFacturasPorCliente(int clienteId) throws DAOException {
        List<Factura> facturas = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_CLIENTE)) {
            ps.setInt(1, clienteId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    facturas.add(mapearFactura(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener las facturas por cliente.", e);
        }

        return facturas;
    }

    /**
     * Obtiene las facturas por rango de fecha.
     *
     * @param fechaInicio La fecha de inicio del rango.
     * @param fechaFin La fecha de fin del rango.
     * @return Una lista de facturas dentro del rango de fechas especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Factura> obtenerFacturasPorFecha(Date fechaInicio, Date fechaFin) throws DAOException {
        List<Factura> facturas = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_RANGO_FECHA)) {
            ps.setDate(1, fechaInicio);
            ps.setDate(2, fechaFin);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    facturas.add(mapearFactura(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener las facturas por fecha.", e);
        }

        return facturas;
    }

    /**
     * Obtiene las facturas por método de pago.
     *
     * @param metodoPago El método de pago de las facturas a obtener.
     * @return Una lista de facturas con el método de pago especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Factura> obtenerFacturasPorMetodoPago(String metodoPago) throws DAOException {
        List<Factura> facturas = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_METODO_PAGO)) {
            ps.setString(1, metodoPago);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    facturas.add(mapearFactura(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener las facturas por método de pago.", e);
        }

        return facturas;
    }

    /**
     * Obtiene las facturas por monto.
     *
     * @param montoMin El monto mínimo del rango.
     * @param montoMax El monto máximo del rango.
     * @return Una lista de facturas dentro del rango de monto especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Factura> obtenerFacturasPorMonto(double montoMin, double montoMax) throws DAOException {
        List<Factura> facturas = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_MONTO)) {
            ps.setDouble(1, montoMin);
            ps.setDouble(2, montoMax);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    facturas.add(mapearFactura(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener las facturas por monto.", e);
        }

        return facturas;
    }

    /**
     * Actualiza una factura existente en la base de datos.
     *
     * @param factura La factura a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(Factura factura) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ACTUALIZAR)) {
            ps.setInt(1, factura.getIdCliente());
            ps.setInt(2, factura.getIdEmpleado());
            ps.setDate(3, new Date(factura.getFechaPedido().getTime()));
            ps.setDouble(4, factura.getTotalPedido());
            ps.setString(5, factura.getMetodoPago());
            ps.setInt(6, factura.getIdFactura());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de factura fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar la factura.", e);
        }
    }

    /**
     * Elimina una factura de la base de datos.
     *
     * @param id El ID de la factura a eliminar.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int id) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ELIMINAR)) {
            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación de factura fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar la factura.", e);
        }
    }

    /**
     * Mapea un ResultSet a un objeto Factura.
     *
     * @param rs El ResultSet a mapear.
     * @return El objeto Factura mapeado.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    private Factura mapearFactura(ResultSet rs) throws SQLException {
        Factura factura = new Factura();

        factura.setIdFactura(rs.getInt("ID_FACTURA"));
        factura.setIdCliente(rs.getInt("ID_CLIENTE"));
        factura.setIdEmpleado(rs.getInt("ID_EMPLEADO"));
        factura.setFechaPedido(rs.getDate("FECHA_PEDIDO"));
        factura.setTotalPedido(rs.getDouble("TOTAL_PEDIDO"));
        factura.setMetodoPago(rs.getString("METODO_PAGO"));

        return factura;
    }
}