package es.cheste.servicio;

import es.cheste.DAO.VideojuegoDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.impl.VideojuegoDAOImpl;
import es.cheste.objetos.Videojuego;

import java.sql.Date;
import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de Videojuego.
 */
public class VideojuegoServicio {
    private VideojuegoDAO videojuegoDAO;

    /**
     * Constructor que inicializa el DAO de Videojuego.
     */
    public VideojuegoServicio() {
        this.videojuegoDAO = new VideojuegoDAOImpl();
    }

    /**
     * Inserta un nuevo Videojuego en la base de datos.
     *
     * @param videojuego El Videojuego a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    public void insertarVideojuego(Videojuego videojuego) throws DAOException {
        videojuegoDAO.insertarVideojuego(videojuego);
    }

    /**
     * Obtiene un Videojuego por su ID.
     *
     * @param id El ID del videojuego.
     * @return El Videojuego obtenido.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public Videojuego obtenerPorID(int id) throws DAOException {
        return videojuegoDAO.obtenerPorID(id);
    }

    /**
     * Obtiene todos los Videojuegos de la base de datos.
     *
     * @return Una lista de todos los Videojuegos.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Videojuego> obtenerTodos() throws DAOException {
        return videojuegoDAO.obtenerTodos();
    }

    /**
     * Obtiene los Videojuegos por clasificación.
     *
     * @param clasificacion La clasificación de los videojuegos.
     * @return Una lista de Videojuegos con la clasificación especificada.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Videojuego> obtenerPorClasificacion(String clasificacion) throws DAOException {
        return videojuegoDAO.obtenerPorClasificacion(clasificacion);
    }

    /**
     * Obtiene los Videojuegos por rango de fecha.
     *
     * @param fechaInicio La fecha de inicio del rango.
     * @param fechaFin La fecha de fin del rango.
     * @return Una lista de Videojuegos dentro del rango de fecha especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Videojuego> obtenerPorRangoFecha(Date fechaInicio, Date fechaFin) throws DAOException {
        return videojuegoDAO.obtenerPorRangoFecha(fechaInicio, fechaFin);
    }

    /**
     * Actualiza un Videojuego en la base de datos.
     *
     * @param videojuego El Videojuego a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    public void actualizar(Videojuego videojuego) throws DAOException {
        videojuegoDAO.actualizar(videojuego);
    }

    /**
     * Elimina un Videojuego de la base de datos.
     *
     * @param id El ID del videojuego.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    public void eliminar(int id) throws DAOException {
        videojuegoDAO.eliminar(id);
    }
}