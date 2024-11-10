package es.cheste.impl;

import es.cheste.gestion_bd.ConexionBD;
import es.cheste.DAO.PlataformaDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Plataforma;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz PlataformaDAO para gestionar las operaciones CRUD de Plataforma.
 */
public class PlataformaDAOImpl implements PlataformaDAO {
    private static final Logger LOGGER = LogManager.getRootLogger();

    // Consultas SQL
    private static final String INSERTAR = "INSERT INTO PLATAFORMA (NOMBRE) VALUES (?)";
    private static final String OBTENER_POR_ID = "SELECT * FROM PLATAFORMA WHERE ID_PLATAFORMA = ?";
    private static final String OBTENER_TODAS = "SELECT * FROM PLATAFORMA";
    private static final String ACTUALIZAR = "UPDATE PLATAFORMA SET NOMBRE = ? WHERE ID_PLATAFORMA = ?";
    private static final String ELIMINAR = "DELETE FROM PLATAFORMA WHERE ID_PLATAFORMA = ?";

    ConexionBD conexionBD = new ConexionBD();

    /**
     * Inserta una nueva Plataforma en la base de datos.
     *
     * @param plataforma La Plataforma a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertarPlataforma(Plataforma plataforma) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, plataforma.getNombre());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de plataforma fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al insertar la plataforma.", e);
        }
    }

    /**
     * Obtiene una Plataforma por su ID.
     *
     * @param id El ID de la plataforma.
     * @return La Plataforma obtenida.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public Plataforma obtenerPorID(int id) throws DAOException {
        Plataforma plataforma = null;

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_ID)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    plataforma = mapearPlataforma(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener la plataforma por ID.", e);
        }

        return plataforma;
    }

    /**
     * Obtiene todas las Plataformas de la base de datos.
     *
     * @return Una lista de todas las Plataformas.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Plataforma> obtenerTodas() throws DAOException {
        List<Plataforma> plataformas = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_TODAS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                plataformas.add(mapearPlataforma(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todas las plataformas.", e);
        }

        return plataformas;
    }

    /**
     * Actualiza una Plataforma en la base de datos.
     *
     * @param plataforma La Plataforma a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(Plataforma plataforma) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ACTUALIZAR)) {
            ps.setString(1, plataforma.getNombre());
            ps.setInt(2, plataforma.getIdPlataforma());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de plataforma fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar la plataforma.", e);
        }
    }

    /**
     * Elimina una Plataforma de la base de datos.
     *
     * @param id El ID de la plataforma.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int id) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ELIMINAR)) {
            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación de plataforma fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar la plataforma.", e);
        }
    }

    /**
     * Mapea un ResultSet a un objeto Plataforma.
     *
     * @param rs El ResultSet a mapear.
     * @return El objeto Plataforma mapeado.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    private Plataforma mapearPlataforma(ResultSet rs) throws SQLException {
        int idPlataforma = rs.getInt("ID_PLATAFORMA");
        String nombre = rs.getString("NOMBRE");

        return new Plataforma(idPlataforma, nombre);
    }
}