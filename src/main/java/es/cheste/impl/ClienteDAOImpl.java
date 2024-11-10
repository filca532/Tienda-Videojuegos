package es.cheste.impl;

import es.cheste.gestion_bd.ConexionBD;
import es.cheste.DAO.ClienteDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Cliente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz ClienteDAO para gestionar las operaciones CRUD de los clientes.
 */
public class ClienteDAOImpl implements ClienteDAO {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String INSERTAR = "INSERT INTO CLIENTE (NOMBRE, APELLIDOS, EMAIL, TELEFONO, DIRECCION, FECHA_REGISTRO) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String OBTENER_POR_ID = "SELECT * FROM CLIENTE WHERE ID_CLIENTE = ?";
    private static final String OBTENER_TODOS = "SELECT * FROM CLIENTE";
    private static final String OBTENER_RANGO_FECHA = "SELECT * FROM CLIENTE WHERE FECHA_REGISTRO BETWEEN ? AND ?";
    private static final String ACTUALIZAR = "UPDATE CLIENTE SET NOMBRE = ?, APELLIDOS = ?, EMAIL = ?, TELEFONO = ?, DIRECCION = ?, FECHA_REGISTRO = ? WHERE ID_CLIENTE = ?";
    private static final String ELIMINAR = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ?";

    ConexionBD conexionBD = new ConexionBD();

    /**
     * Inserta un nuevo cliente en la base de datos.
     *
     * @param cliente El cliente a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertarCliente(Cliente cliente) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            ps.setDate(6, new Date(cliente.getFechaRegistro().getTime()));

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de cliente fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al insertar el cliente.", e);
        }
    }

    /**
     * Obtiene un cliente por su ID.
     *
     * @param id El ID del cliente a obtener.
     * @return El cliente obtenido.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public Cliente obtenerPorID(int id) throws DAOException {
        Cliente cliente = null;

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_ID)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = mapearCliente(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener el cliente por ID.", e);
        }

        return cliente;
    }

    /**
     * Obtiene todos los clientes.
     *
     * @return Una lista de todos los clientes.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Cliente> obtenerTodos() throws DAOException {
        List<Cliente> clientes = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_TODOS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                clientes.add(mapearCliente(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todos los clientes.", e);
        }

        return clientes;
    }

    /**
     * Obtiene los clientes registrados en un rango de fechas.
     *
     * @param fechaInicio La fecha de inicio del rango.
     * @param fechaFin La fecha de fin del rango.
     * @return Una lista de clientes registrados en el rango de fechas.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Cliente> obtenerClientesRangoFecha(Date fechaInicio, Date fechaFin) throws DAOException {
        List<Cliente> clientes = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_RANGO_FECHA)) {
            ps.setDate(1, new Date(fechaInicio.getTime()));
            ps.setDate(2, new Date(fechaFin.getTime()));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    clientes.add(mapearCliente(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener clientes por rango de fecha.", e);
        }

        return clientes;
    }

    /**
     * Actualiza un cliente existente en la base de datos.
     *
     * @param cliente El cliente a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(Cliente cliente) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ACTUALIZAR)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            ps.setDate(6, new Date(cliente.getFechaRegistro().getTime()));
            ps.setInt(7, cliente.getIdCliente());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de cliente fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el cliente.", e);
        }
    }

    /**
     * Elimina un cliente de la base de datos.
     *
     * @param id El ID del cliente a eliminar.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int id) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ELIMINAR)) {
            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación de cliente fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el cliente.", e);
        }
    }

    /**
     * Mapea un ResultSet a un objeto Cliente.
     *
     * @param rs El ResultSet a mapear.
     * @return El objeto Cliente mapeado.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    private Cliente mapearCliente(ResultSet rs) throws SQLException {
        int idCliente = rs.getInt("ID_CLIENTE");
        String nombre = rs.getString("NOMBRE");
        String apellidos = rs.getString("APELLIDOS");
        String email = rs.getString("EMAIL");
        String telefono = rs.getString("TELEFONO");
        String direccion = rs.getString("DIRECCION");
        Date fechaRegistro = rs.getDate("FECHA_REGISTRO");

        return new Cliente(idCliente, nombre, apellidos, email, telefono, direccion, fechaRegistro);
    }
}