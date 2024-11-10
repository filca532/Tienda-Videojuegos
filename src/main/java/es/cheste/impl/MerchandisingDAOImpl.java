package es.cheste.impl;

import es.cheste.DAO.MerchandisingDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.gestion_bd.ConexionBD;
import es.cheste.objetos.Merchandising;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz MerchandisingDAO para gestionar las operaciones CRUD de Merchandising.
 */
public class MerchandisingDAOImpl implements MerchandisingDAO {
    private static final Logger LOGGER = LogManager.getRootLogger();

    // Consultas SQL
    private static final String INSERTAR = "INSERT INTO MERCHANDISING (ID_PRODUCTO, TIPO_PRODUCTO, CATEGORIA, MATERIAL, DIMENSIONES, PESO, ID_PROVEEDOR) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String OBTENER_POR_ID = "SELECT * FROM MERCHANDISING WHERE ID_PRODUCTO = ?";
    private static final String OBTENER_TODOS = "SELECT * FROM MERCHANDISING";
    private static final String OBTENER_POR_CATEGORIA = "SELECT * FROM MERCHANDISING WHERE CATEGORIA = ?";
    private static final String ACTUALIZAR = "UPDATE MERCHANDISING SET CATEGORIA = ?, MATERIAL = ?, DIMENSIONES = ?, PESO = ?, ID_PROVEEDOR = ? WHERE ID_MERCHANDISING = ?";
    private static final String ELIMINAR = "DELETE FROM MERCHANDISING WHERE ID_PRODUCTO = ?";

    ConexionBD conexionBD = new ConexionBD();

    /**
     * Inserta un nuevo Merchandising en la base de datos.
     *
     * @param merchandising El Merchandising a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertarMerchandising(Merchandising merchandising) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, merchandising.getIdProducto());
            ps.setString(2, "merchandising");
            ps.setString(3, merchandising.getCategoria());
            ps.setString(4, merchandising.getMaterial());
            ps.setString(5, merchandising.getDimensiones());
            ps.setDouble(6, merchandising.getPeso());
            ps.setInt(7, merchandising.getProveedor());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de merchandising fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al insertar el merchandising.", e);
        }
    }

    /**
     * Obtiene un Merchandising por su ID.
     *
     * @param id El ID del merchandising.
     * @return El Merchandising obtenido.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public Merchandising obtenerPorID(int id) throws DAOException {
        Merchandising merchandising = null;

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_ID)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    merchandising = mapearMerchandising(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener el merchandising por ID.", e);
        }

        return merchandising;
    }

    /**
     * Obtiene todos los Merchandising de la base de datos.
     *
     * @return Una lista de todos los Merchandising.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Merchandising> obtenerTodos() throws DAOException {
        List<Merchandising> merchandisings = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_TODOS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                merchandisings.add(mapearMerchandising(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todos los merchandisings.", e);
        }

        return merchandisings;
    }

    /**
     * Obtiene los Merchandising por categoría.
     *
     * @param categoria La categoría del merchandising.
     * @return Una lista de Merchandising de la categoría especificada.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Merchandising> obtenerPorCategoria(String categoria) throws DAOException {
        List<Merchandising> merchandisings = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_CATEGORIA)) {
            ps.setString(1, categoria);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    merchandisings.add(mapearMerchandising(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener merchandisings por categoría.", e);
        }

        return merchandisings;
    }

    /**
     * Actualiza un Merchandising en la base de datos.
     *
     * @param merchandising El Merchandising a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(Merchandising merchandising) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ACTUALIZAR)) {
            ps.setString(1, merchandising.getCategoria());
            ps.setString(2, merchandising.getMaterial());
            ps.setString(3, merchandising.getDimensiones());
            ps.setDouble(4, merchandising.getPeso());
            ps.setInt(5, merchandising.getProveedor());
            ps.setInt(6, merchandising.getIdProducto());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de merchandising fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el merchandising.", e);
        }
    }

    /**
     * Elimina un Merchandising de la base de datos.
     *
     * @param id El ID del merchandising.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int id) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ELIMINAR)) {
            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación de merchandising fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el merchandising.", e);
        }
    }

    /**
     * Mapea un ResultSet a un objeto Merchandising.
     *
     * @param rs El ResultSet a mapear.
     * @return El objeto Merchandising mapeado.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    private Merchandising mapearMerchandising(ResultSet rs) throws SQLException {
        int idMerchandising = rs.getInt("ID_PRODUCTO");
        String categoria = rs.getString("CATEGORIA");
        String material = rs.getString("MATERIAL");
        String dimensiones = rs.getString("DIMENSIONES");
        double peso = rs.getDouble("PESO");
        int id_proveedor = rs.getInt("ID_PROVEEDOR");

        return new Merchandising(idMerchandising, categoria, material, dimensiones, peso, id_proveedor);
    }
}