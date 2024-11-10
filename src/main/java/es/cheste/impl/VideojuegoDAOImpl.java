package es.cheste.impl;

import es.cheste.DAO.VideojuegoDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.gestion_bd.ConexionBD;
import es.cheste.objetos.Videojuego;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz VideojuegoDAO para gestionar las operaciones CRUD de Videojuego.
 */
public class VideojuegoDAOImpl implements VideojuegoDAO {
    // Consultas SQL
    private static final String INSERTAR = "INSERT INTO VIDEOJUEGO (ID_PRODUCTO, TIPO_PRODUCTO, FECHA_LANZAMIENTO, CLASIFICACION_EDAD, ID_PROVEEDOR) VALUES (?, ?, ?, ?, ?)";
    private static final String OBTENER_POR_ID = "SELECT * FROM VIDEOJUEGO WHERE ID_PRODUCTO = ?";
    private static final String OBTENER_TODOS = "SELECT * FROM VIDEOJUEGO";
    private static final String OBTENER_POR_CLASIFICACION = "SELECT * FROM VIDEOJUEGO WHERE CLASIFICACION_EDAD = ?";
    private static final String OBTENER_POR_RANGO_FECHA = "SELECT * FROM VIDEOJUEGO WHERE FECHA_LANZAMIENTO BETWEEN ? AND ?";
    private static final String ACTUALIZAR = "UPDATE VIDEOJUEGO SET FECHA_LANZAMIENTO = ?, CLASIFICACION_EDAD = ?, ID_PROVEEDOR = ? WHERE ID_PRODUCTO = ?";
    private static final String ELIMINAR = "DELETE FROM VIDEOJUEGO WHERE ID_PRODUCTO = ?";

    private final ConexionBD conexionBD = new ConexionBD();

    /**
     * Inserta un nuevo Videojuego en la base de datos.
     *
     * @param videojuego El Videojuego a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertarVideojuego(Videojuego videojuego) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(INSERTAR)) {
            ps.setInt(1, videojuego.getIdProducto());
            ps.setString(2, "videojuego");
            ps.setDate(3, videojuego.getFechaLanzamiento());
            ps.setString(4, videojuego.getClasificacionEdad());
            ps.setInt(5, videojuego.getProveedor());

            if (ps.executeUpdate() == 0) {
                throw new DAOException("Inserción fallida.", null);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al insertar.", e);
        }
    }

    /**
     * Obtiene un Videojuego por su ID.
     *
     * @param id El ID del videojuego.
     * @return El Videojuego obtenido.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public Videojuego obtenerPorID(int id) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearVideojuego(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener por ID.", e);
        }
        return null;
    }

    /**
     * Obtiene todos los Videojuegos de la base de datos.
     *
     * @return Una lista de todos los Videojuegos.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Videojuego> obtenerTodos() throws DAOException {
        List<Videojuego> lista = new ArrayList<>();
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_TODOS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearVideojuego(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener todos.", e);
        }
        return lista;
    }

    /**
     * Obtiene los Videojuegos por clasificación de edad.
     *
     * @param clasificacion La clasificación de edad.
     * @return Una lista de Videojuegos con la clasificación especificada.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Videojuego> obtenerPorClasificacion(String clasificacion) throws DAOException {
        List<Videojuego> lista = new ArrayList<>();
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_CLASIFICACION)) {
            ps.setString(1, clasificacion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearVideojuego(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener por clasificación.", e);
        }
        return lista;
    }

    /**
     * Obtiene los Videojuegos por rango de fecha de lanzamiento.
     *
     * @param fechaInicio La fecha de inicio del rango.
     * @param fechaFin La fecha de fin del rango.
     * @return Una lista de Videojuegos dentro del rango de fecha especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Videojuego> obtenerPorRangoFecha(Date fechaInicio, Date fechaFin) throws DAOException {
        List<Videojuego> lista = new ArrayList<>();
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_RANGO_FECHA)) {
            ps.setDate(1, fechaInicio);
            ps.setDate(2, fechaFin);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearVideojuego(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener por rango de fecha.", e);
        }
        return lista;
    }

    /**
     * Actualiza un Videojuego en la base de datos.
     *
     * @param videojuego El Videojuego a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(Videojuego videojuego) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ACTUALIZAR)) {
            ps.setDate(1, videojuego.getFechaLanzamiento());
            ps.setString(2, videojuego.getClasificacionEdad());
            ps.setInt(3, videojuego.getProveedor());
            ps.setInt(4, videojuego.getIdProducto());

            if (ps.executeUpdate() == 0) {
                throw new DAOException("Actualización fallida.", null);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al actualizar.", e);
        }
    }

    /**
     * Elimina un Videojuego de la base de datos.
     *
     * @param id El ID del videojuego.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int id) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ELIMINAR)) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 0) {
                throw new DAOException("Eliminación fallida.", null);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar.", e);
        }
    }

    /**
     * Mapea un ResultSet a un objeto Videojuego.
     *
     * @param rs El ResultSet a mapear.
     * @return El objeto Videojuego mapeado.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    private Videojuego mapearVideojuego(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID_PRODUCTO");
        Date fechaLanzamiento = rs.getDate("FECHA_LANZAMIENTO");
        String clasificacion = rs.getString("CLASIFICACION_EDAD");
        int idProveedor = rs.getInt("ID_PROVEEDOR");

        return new Videojuego(id, fechaLanzamiento, clasificacion, idProveedor);
    }
}