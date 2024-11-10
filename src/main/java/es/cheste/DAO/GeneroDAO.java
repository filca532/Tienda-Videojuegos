package es.cheste.DAO;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Genero;

import java.util.List;

/**
 * Interfaz para el Data Access Object (DAO) de Genero.
 * Proporciona métodos para realizar operaciones CRUD en objetos Genero.
 */
public interface GeneroDAO {

    /**
     * Inserta un nuevo Genero en la base de datos.
     *
     * @param genero el objeto Genero a insertar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void insertarGenero(Genero genero) throws DAOException;

    /**
     * Recupera un Genero por su ID.
     *
     * @param id el ID del Genero a recuperar
     * @return el objeto Genero con el ID especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    Genero obtenerPorID(int id) throws DAOException;

    /**
     * Recupera todos los objetos Genero de la base de datos.
     *
     * @return una lista de todos los objetos Genero
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Genero> obtenerTodos() throws DAOException;

    /**
     * Actualiza un Genero existente en la base de datos.
     *
     * @param genero el objeto Genero con la información actualizada
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void actualizar(Genero genero) throws DAOException;

    /**
     * Elimina un Genero de la base de datos por su ID.
     *
     * @param id el ID del Genero a eliminar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void eliminar(int id) throws DAOException;
}