package es.cheste.impl;

import es.cheste.DAO.VideojuegoPlataformaDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.gestion_bd.ConexionBD;
import es.cheste.objetos.VideojuegoPlataforma;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz VideojuegoPlataformaDAO para gestionar las operaciones CRUD de VideojuegoPlataforma.
 */
public class VideojuegoPlataformaDAOImpl implements VideojuegoPlataformaDAO {
    private static final Logger LOGGER = LogManager.getRootLogger();

    // Consultas SQL
    private static final String INSERTAR = "INSERT INTO VIDEOJUEGO_PLATAFORMA (ID_PRODUCTO, TIPO_PRODUCTO, ID_PLATAFORMA) VALUES (?, ?, ?)";
    private static final String OBTENER_POR_ID = "SELECT VP.ID_PRODUCTO, VP.ID_PLATAFORMA, P.NOMBRE AS NOMBRE_PRODUCTO, PL.NOMBRE AS NOMBRE_PLATAFORMA FROM VIDEOJUEGO_PLATAFORMA VP JOIN PRODUCTO P ON VP.ID_PRODUCTO = P.ID_PRODUCTO JOIN PLATAFORMA PL ON VP.ID_PLATAFORMA = PL.ID_PLATAFORMA WHERE VP.ID_PRODUCTO = ? AND VP.ID_PLATAFORMA = ?";
    private static final String OBTENER_TODOS = "SELECT VP.ID_PRODUCTO, VP.ID_PLATAFORMA, P.NOMBRE AS NOMBRE_PRODUCTO, PL.NOMBRE AS NOMBRE_PLATAFORMA FROM VIDEOJUEGO_PLATAFORMA VP JOIN PRODUCTO P ON VP.ID_PRODUCTO = P.ID_PRODUCTO JOIN PLATAFORMA PL ON VP.ID_PLATAFORMA = PL.ID_PLATAFORMA";
    private static final String OBTENER_POR_NOMBRE_VIDEOJUEGO = "SELECT VP.ID_PRODUCTO, VP.ID_PLATAFORMA, P.NOMBRE AS NOMBRE_PRODUCTO, PL.NOMBRE AS NOMBRE_PLATAFORMA FROM VIDEOJUEGO_PLATAFORMA VP JOIN PRODUCTO P ON VP.ID_PRODUCTO = P.ID_PRODUCTO JOIN PLATAFORMA PL ON VP.ID_PLATAFORMA = PL.ID_PLATAFORMA WHERE P.NOMBRE = ?";
    private static final String OBTENER_POR_NOMBRE_PLATAFORMA = "SELECT VP.ID_PRODUCTO, VP.ID_PLATAFORMA, P.NOMBRE AS NOMBRE_PRODUCTO, PL.NOMBRE AS NOMBRE_PLATAFORMA FROM VIDEOJUEGO_PLATAFORMA VP JOIN PRODUCTO P ON VP.ID_PRODUCTO = P.ID_PRODUCTO JOIN PLATAFORMA PL ON VP.ID_PLATAFORMA = PL.ID_PLATAFORMA WHERE PL.NOMBRE = ?";
    private static final String ACTUALIZAR = "UPDATE VIDEOJUEGO_PLATAFORMA SET ID_PRODUCTO = ?, ID_PLATAFORMA = ? WHERE ID_PRODUCTO = ? AND ID_PLATAFORMA = ?";
    private static final String ELIMINAR = "DELETE FROM VIDEOJUEGO_PLATAFORMA WHERE ID_PRODUCTO = ? AND ID_PLATAFORMA = ?";

    ConexionBD conexionBD = new ConexionBD();

    /**
     * Inserta un nuevo VideojuegoPlataforma en la base de datos.
     *
     * @param videojuegoPlataforma El VideojuegoPlataforma a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertarVideojuegoPlataforma(VideojuegoPlataforma videojuegoPlataforma) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, videojuegoPlataforma.getIdProducto());
            ps.setString(2, "videojuego");
            ps.setInt(3, videojuegoPlataforma.getIdPlataforma());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de videojuego plataforma fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al insertar el videojuego plataforma.", e);
        }
    }

    /**
     * Obtiene todos los VideojuegoPlataforma de la base de datos.
     *
     * @return Una lista de todos los VideojuegoPlataforma.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<VideojuegoPlataforma> obtenerTodos() throws DAOException {
        List<VideojuegoPlataforma> videojuegoPlataformas = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_TODOS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                videojuegoPlataformas.add(mapearVideojuegoPlataforma(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todos los videojuegos plataforma.", e);
        }

        return videojuegoPlataformas;
    }

    /**
     * Obtiene los VideojuegoPlataforma por nombre de videojuego.
     *
     * @param nombreJuego El nombre del videojuego.
     * @return Una lista de VideojuegoPlataforma con el nombre de videojuego especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<VideojuegoPlataforma> obtenerPorVideojuegoNombre(String nombreJuego) throws DAOException {
        List<VideojuegoPlataforma> videojuegoPlataformas = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_NOMBRE_VIDEOJUEGO)) {
            ps.setString(1, nombreJuego);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    videojuegoPlataformas.add(mapearVideojuegoPlataforma(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener videojuegos plataforma por nombre de videojuego.", e);
        }

        return videojuegoPlataformas;
    }

    /**
     * Obtiene los VideojuegoPlataforma por nombre de plataforma.
     *
     * @param nombrePlataforma El nombre de la plataforma.
     * @return Una lista de VideojuegoPlataforma con el nombre de plataforma especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<VideojuegoPlataforma> obtenerPorPlataformaNombre(String nombrePlataforma) throws DAOException {
        List<VideojuegoPlataforma> videojuegoPlataformas = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_NOMBRE_PLATAFORMA)) {
            ps.setString(1, nombrePlataforma);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    videojuegoPlataformas.add(mapearVideojuegoPlataforma(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener videojuegos plataforma por nombre de plataforma.", e);
        }

        return videojuegoPlataformas;
    }

    /**
     * Actualiza un VideojuegoPlataforma en la base de datos.
     *
     * @param videojuegoPlataforma El VideojuegoPlataforma a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(VideojuegoPlataforma videojuegoPlataforma) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ACTUALIZAR)) {
            ps.setInt(1, videojuegoPlataforma.getIdNuevoProducto());
            ps.setInt(2, videojuegoPlataforma.getIdNuevaPlataforma());
            ps.setInt(3, videojuegoPlataforma.getIdProducto());
            ps.setInt(4, videojuegoPlataforma.getIdPlataforma());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de videojuego plataforma fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el videojuego plataforma.", e);
        }
    }

    /**
     * Elimina un VideojuegoPlataforma de la base de datos.
     *
     * @param idProducto El ID del producto.
     * @param idPlataforma El ID de la plataforma.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int idProducto, int idPlataforma) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ELIMINAR)) {
            ps.setInt(1, idProducto);
            ps.setInt(2, idPlataforma);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación de videojuego plataforma fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el videojuego plataforma.", e);
        }
    }

    /**
     * Mapea un ResultSet a un objeto VideojuegoPlataforma.
     *
     * @param rs El ResultSet a mapear.
     * @return El objeto VideojuegoPlataforma mapeado.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    private VideojuegoPlataforma mapearVideojuegoPlataforma(ResultSet rs) throws SQLException {
        int idProducto = rs.getInt("ID_PRODUCTO");
        int idPlataforma = rs.getInt("ID_PLATAFORMA");
        String nombreProducto = rs.getString("NOMBRE_PRODUCTO");
        String nombrePlataforma = rs.getString("NOMBRE_PLATAFORMA");

        return new VideojuegoPlataforma(idProducto, idPlataforma, nombreProducto, nombrePlataforma);
    }
}