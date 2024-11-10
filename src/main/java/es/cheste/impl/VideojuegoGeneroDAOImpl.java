package es.cheste.impl;

import es.cheste.DAO.VideojuegoGeneroDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.gestion_bd.ConexionBD;
import es.cheste.objetos.VideojuegoGenero;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz VideojuegoGeneroDAO para gestionar las operaciones CRUD de VideojuegoGenero.
 */
public class VideojuegoGeneroDAOImpl implements VideojuegoGeneroDAO {
    private static final Logger LOGGER = LogManager.getRootLogger();

    // Consultas SQL
    private static final String INSERTAR = "INSERT INTO VIDEOJUEGO_GENERO (ID_PRODUCTO, TIPO_PRODUCTO, ID_GENERO) VALUES (?, ?, ?)";
    private static final String OBTENER_TODOS = "SELECT VG.ID_PRODUCTO, VG.ID_GENERO, P.NOMBRE AS NOMBRE_PRODUCTO, G.NOMBRE AS NOMBRE_GENERO FROM VIDEOJUEGO_GENERO VG JOIN PRODUCTO P ON VG.ID_PRODUCTO = P.ID_PRODUCTO JOIN GENERO G ON VG.ID_GENERO = G.ID_GENERO";
    private static final String OBTENER_POR_NOMBRE_VIDEOJUEGO = "SELECT VG.ID_PRODUCTO, VG.ID_GENERO, P.NOMBRE AS NOMBRE_PRODUCTO, G.NOMBRE AS NOMBRE_GENERO FROM VIDEOJUEGO_GENERO VG JOIN PRODUCTO P ON VG.ID_PRODUCTO = P.ID_PRODUCTO JOIN GENERO G ON VG.ID_GENERO = G.ID_GENERO WHERE P.NOMBRE = ?";
    private static final String OBTENER_POR_NOMBRE_GENERO = "SELECT VG.ID_PRODUCTO, VG.ID_GENERO, P.NOMBRE AS NOMBRE_PRODUCTO, G.NOMBRE AS NOMBRE_GENERO FROM VIDEOJUEGO_GENERO VG JOIN PRODUCTO P ON VG.ID_PRODUCTO = P.ID_PRODUCTO JOIN GENERO G ON VG.ID_GENERO = G.ID_GENERO WHERE G.NOMBRE = ?";
    private static final String ACTUALIZAR = "UPDATE VIDEOJUEGO_GENERO SET ID_PRODUCTO = ?, ID_GENERO = ? WHERE ID_PRODUCTO = ? AND ID_GENERO = ?";
    private static final String ELIMINAR = "DELETE FROM VIDEOJUEGO_GENERO WHERE ID_PRODUCTO = ? AND ID_GENERO = ?";

    ConexionBD conexionBD = new ConexionBD();

    /**
     * Inserta un nuevo VideojuegoGenero en la base de datos.
     *
     * @param videojuegoGenero El VideojuegoGenero a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertarVideojuegoGenero(VideojuegoGenero videojuegoGenero) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(INSERTAR)) {
            ps.setInt(1, videojuegoGenero.getIdProducto());
            ps.setString(2, "videojuego");
            ps.setInt(3, videojuegoGenero.getIdGenero());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de videojuego género fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al insertar el videojuego género.", e);
        }
    }

    /**
     * Obtiene todos los VideojuegoGenero de la base de datos.
     *
     * @return Una lista de todos los VideojuegoGenero.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<VideojuegoGenero> obtenerTodos() throws DAOException {
        List<VideojuegoGenero> videojuegoGeneros = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_TODOS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                videojuegoGeneros.add(mapearVideojuegoGenero(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todos los videojuegos género.", e);
        }

        return videojuegoGeneros;
    }

    /**
     * Obtiene los VideojuegoGenero por nombre de videojuego.
     *
     * @param nombreJuego El nombre del videojuego.
     * @return Una lista de VideojuegoGenero con el nombre de videojuego especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<VideojuegoGenero> obtenerPorVideojuegoNombre(String nombreJuego) throws DAOException {
        List<VideojuegoGenero> videojuegoGeneros = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_NOMBRE_VIDEOJUEGO)) {
            ps.setString(1, nombreJuego);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    videojuegoGeneros.add(mapearVideojuegoGenero(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener videojuegos género por nombre de videojuego.", e);
        }

        return videojuegoGeneros;
    }

    /**
     * Obtiene los VideojuegoGenero por nombre de género.
     *
     * @param nombreGenero El nombre del género.
     * @return Una lista de VideojuegoGenero con el nombre de género especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<VideojuegoGenero> obtenerPorGeneroNombre(String nombreGenero) throws DAOException {
        List<VideojuegoGenero> videojuegoGeneros = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_NOMBRE_GENERO)) {
            ps.setString(1, nombreGenero);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    videojuegoGeneros.add(mapearVideojuegoGenero(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener videojuegos género por nombre de género.", e);
        }

        return videojuegoGeneros;
    }

    /**
     * Actualiza un VideojuegoGenero en la base de datos.
     *
     * @param videojuegoGenero El VideojuegoGenero a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(VideojuegoGenero videojuegoGenero) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ACTUALIZAR)) {
            ps.setInt(1, videojuegoGenero.getIdNuevoProducto());
            ps.setInt(2, videojuegoGenero.getIdNuevoGenero());
            ps.setInt(3, videojuegoGenero.getIdProducto());
            ps.setInt(4, videojuegoGenero.getIdGenero());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de videojuego género fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el videojuego género.", e);
        }
    }

    /**
     * Elimina un VideojuegoGenero de la base de datos.
     *
     * @param idVideojuego El ID del videojuego.
     * @param idGenero El ID del género.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int idVideojuego, int idGenero) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ELIMINAR)) {
            ps.setInt(1, idVideojuego);
            ps.setInt(2, idGenero);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación de videojuego género fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el videojuego género.", e);
        }
    }

    /**
     * Mapea un ResultSet a un objeto VideojuegoGenero.
     *
     * @param rs El ResultSet a mapear.
     * @return El objeto VideojuegoGenero mapeado.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    private VideojuegoGenero mapearVideojuegoGenero(ResultSet rs) throws SQLException {
        int idVideojuego = rs.getInt("ID_PRODUCTO");
        int idGenero = rs.getInt("ID_GENERO");
        String nombreVideojuego = rs.getString("NOMBRE_PRODUCTO");
        String nombreGenero = rs.getString("NOMBRE_GENERO");

        return new VideojuegoGenero(idVideojuego, idGenero, nombreVideojuego, nombreGenero);
    }
}