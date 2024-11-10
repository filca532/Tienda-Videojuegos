package es.cheste.DAO;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.VideojuegoGenero;

import java.util.List;

/**
 * Interfaz para el Data Access Object (DAO) de VideojuegoGenero.
 * Proporciona métodos para realizar operaciones CRUD en objetos VideojuegoGenero.
 */
public interface VideojuegoGeneroDAO {

    /**
     * Inserta un nuevo VideojuegoGenero en la base de datos.
     *
     * @param videojuegoGenero el objeto VideojuegoGenero a insertar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void insertarVideojuegoGenero(VideojuegoGenero videojuegoGenero) throws DAOException;

    /**
     * Recupera todos los objetos VideojuegoGenero de la base de datos.
     *
     * @return una lista de todos los objetos VideojuegoGenero
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<VideojuegoGenero> obtenerTodos() throws DAOException;

    /**
     * Recupera VideojuegoGeneros por el nombre del videojuego.
     *
     * @param nombreJuego el nombre del videojuego
     * @return una lista de VideojuegoGeneros con el nombre del videojuego especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<VideojuegoGenero> obtenerPorVideojuegoNombre(String nombreJuego) throws DAOException;

    /**
     * Recupera VideojuegoGeneros por el nombre del género.
     *
     * @param nombreGenero el nombre del género
     * @return una lista de VideojuegoGeneros con el nombre del género especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<VideojuegoGenero> obtenerPorGeneroNombre(String nombreGenero) throws DAOException;

    /**
     * Actualiza un VideojuegoGenero existente en la base de datos.
     *
     * @param videojuegoGenero el objeto VideojuegoGenero con la información actualizada
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void actualizar(VideojuegoGenero videojuegoGenero) throws DAOException;

    /**
     * Elimina un VideojuegoGenero de la base de datos por su ID de producto y género.
     *
     * @param idProducto el ID del producto
     * @param idGenero el ID del género
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void eliminar(int idProducto, int idGenero) throws DAOException;
}