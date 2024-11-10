package es.cheste.impl;

import es.cheste.gestion_bd.ConexionBD;
import es.cheste.DAO.ProveedorDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Proveedor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz ProveedorDAO para gestionar las operaciones CRUD de Proveedor.
 */
public class ProveedorDAOImpl implements ProveedorDAO {
    private static final Logger LOGGER = LogManager.getRootLogger();

    // Consultas SQL
    private static final String INSERTAR = "INSERT INTO PROVEEDOR (NOMBRE, DIRECCION, TELEFONO, EMAIL, PAIS) VALUES (?, ?, ?, ?, ?)";
    private static final String OBTENER_POR_ID = "SELECT * FROM PROVEEDOR WHERE ID_PROVEEDOR = ?";
    private static final String OBTENER_TODOS = "SELECT * FROM PROVEEDOR";
    private static final String ACTUALIZAR = "UPDATE PROVEEDOR SET NOMBRE = ?, DIRECCION = ?, TELEFONO = ?, EMAIL = ?, PAIS = ? WHERE ID_PROVEEDOR = ?";
    private static final String ELIMINAR = "DELETE FROM PROVEEDOR WHERE ID_PROVEEDOR = ?";

    ConexionBD conexionBD = new ConexionBD();

    /**
     * Inserta un nuevo Proveedor en la base de datos.
     *
     * @param proveedor El Proveedor a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertarProveedor(Proveedor proveedor) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getDireccion());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getEmail());
            ps.setString(5, proveedor.getPais());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de proveedor fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al insertar el proveedor.", e);
        }
    }

    /**
     * Obtiene un Proveedor por su ID.
     *
     * @param id El ID del proveedor.
     * @return El Proveedor obtenido.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public Proveedor obtenerPorID(int id) throws DAOException {
        Proveedor proveedor = null;

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_ID)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    proveedor = mapearProveedor(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener el proveedor por ID.", e);
        }

        return proveedor;
    }

    /**
     * Obtiene todos los Proveedor de la base de datos.
     *
     * @return Una lista de todos los Proveedor.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Proveedor> obtenerTodos() throws DAOException {
        List<Proveedor> proveedores = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_TODOS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                proveedores.add(mapearProveedor(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todos los proveedores.", e);
        }

        return proveedores;
    }

    /**
     * Actualiza un Proveedor en la base de datos.
     *
     * @param proveedor El Proveedor a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(Proveedor proveedor) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ACTUALIZAR)) {
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getDireccion());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getEmail());
            ps.setString(5, proveedor.getPais());
            ps.setInt(6, proveedor.getIdProveedor());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de proveedor fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el proveedor.", e);
        }
    }

    /**
     * Elimina un Proveedor de la base de datos.
     *
     * @param id El ID del proveedor.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int id) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ELIMINAR)) {
            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación de proveedor fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el proveedor.", e);
        }
    }

    /**
     * Mapea un ResultSet a un objeto Proveedor.
     *
     * @param rs El ResultSet a mapear.
     * @return El objeto Proveedor mapeado.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    private Proveedor mapearProveedor(ResultSet rs) throws SQLException {
        int idProveedor = rs.getInt("ID_PROVEEDOR");
        String nombre = rs.getString("NOMBRE");
        String direccion = rs.getString("DIRECCION");
        String telefono = rs.getString("TELEFONO");
        String email = rs.getString("EMAIL");
        String pais = rs.getString("PAIS");

        return new Proveedor(idProveedor, nombre, direccion, telefono, email, pais);
    }
}