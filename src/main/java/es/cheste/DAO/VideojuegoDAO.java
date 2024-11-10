package es.cheste.DAO;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Videojuego;

import java.sql.Date;
import java.util.List;

/**
 * Interfaz para el Data Access Object (DAO) de Videojuego.
 * Proporciona métodos para realizar operaciones CRUD en objetos Videojuego.
 */
public interface VideojuegoDAO {

    /**
     * Inserta un nuevo Videojuego en la base de datos.
     *
     * @param videojuego el objeto Videojuego a insertar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void insertarVideojuego(Videojuego videojuego) throws DAOException;

    /**
     * Recupera un Videojuego por su ID.
     *
     * @param id el ID del Videojuego a recuperar
     * @return el objeto Videojuego con el ID especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    Videojuego obtenerPorID(int id) throws DAOException;

    /**
     * Recupera todos los objetos Videojuego de la base de datos.
     *
     * @return una lista de todos los objetos Videojuego
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Videojuego> obtenerTodos() throws DAOException;

    /**
     * Recupera Videojuegos por su clasificación.
     *
     * @param clasificacion la clasificación de los Videojuegos a recuperar
     * @return una lista de Videojuegos con la clasificación especificada
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Videojuego> obtenerPorClasificacion(String clasificacion) throws DAOException;

    /**
     * Recupera Videojuegos dentro de un rango de fechas especificado.
     *
     * @param fechaInicio la fecha de inicio del rango
     * @param fechaFin la fecha de fin del rango
     * @return una lista de Videojuegos dentro del rango de fechas especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Videojuego> obtenerPorRangoFecha(Date fechaInicio, Date fechaFin) throws DAOException;

    /**
     * Actualiza un Videojuego existente en la base de datos.
     *
     * @param videojuego el objeto Videojuego con la información actualizada
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void actualizar(Videojuego videojuego) throws DAOException;

    /**
     * Elimina un Videojuego de la base de datos por su ID.
     *
     * @param id el ID del Videojuego a eliminar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void eliminar(int id) throws DAOException;
}