package es.cheste.impl;

import es.cheste.gestion_bd.ConexionBD;
import es.cheste.DAO.GeneroDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Genero;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz GeneroDAO para gestionar las operaciones CRUD de Genero.
 */
public class GeneroDAOImpl implements GeneroDAO {
    private static final Logger LOGGER = LogManager.getRootLogger();

    // Consultas SQL
    private static final String INSERTAR = "INSERT INTO GENERO (NOMBRE) VALUES (?)";
    private static final String OBTENER_POR_ID = "SELECT * FROM GENERO WHERE ID_GENERO = ?";
    private static final String OBTENER_TODOS = "SELECT * FROM GENERO";
    private static final String ACTUALIZAR = "UPDATE GENERO SET NOMBRE = ? WHERE ID_GENERO = ?";
    private static final String ELIMINAR = "DELETE FROM GENERO WHERE ID_GENERO = ?";

    ConexionBD conexionBD = new ConexionBD();

    /**
     * Inserta un nuevo Genero en la base de datos.
     *
     * @param genero El Genero a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertarGenero(Genero genero) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, genero.getNombre());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de género fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al insertar el género.", e);
        }
    }

    /**
     * Obtiene un Genero por su ID.
     *
     * @param id El ID del género.
     * @return El Genero obtenido.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public Genero obtenerPorID(int id) throws DAOException {
        Genero genero = null;

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_ID)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    genero = mapearGenero(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener el género por ID.", e);
        }

        return genero;
    }

    /**
     * Obtiene todos los Genero de la base de datos.
     *
     * @return Una lista de todos los Genero.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Genero> obtenerTodos() throws DAOException {
        List<Genero> generos = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_TODOS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                generos.add(mapearGenero(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todos los géneros.", e);
        }

        return generos;
    }

    /**
     * Actualiza un Genero en la base de datos.
     *
     * @param genero El Genero a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(Genero genero) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ACTUALIZAR)) {
            ps.setString(1, genero.getNombre());
            ps.setInt(2, genero.getIdGenero());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de género fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el género.", e);
        }
    }

    /**
     * Elimina un Genero de la base de datos.
     *
     * @param id El ID del género.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int id) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ELIMINAR)) {
            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación de género fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el género.", e);
        }
    }

    /**
     * Mapea un ResultSet a un objeto Genero.
     *
     * @param rs El ResultSet a mapear.
     * @return El objeto Genero mapeado.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    private Genero mapearGenero(ResultSet rs) throws SQLException {
        int idGenero = rs.getInt("ID_GENERO");
        String nombre = rs.getString("NOMBRE");

        return new Genero(idGenero, nombre);
    }
}