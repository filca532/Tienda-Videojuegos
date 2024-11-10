package es.cheste.DAO;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.VideojuegoPlataforma;

import java.util.List;

/**
 * Interfaz para el Data Access Object (DAO) de VideojuegoPlataforma.
 * Proporciona métodos para realizar operaciones CRUD en objetos VideojuegoPlataforma.
 */
public interface VideojuegoPlataformaDAO {

    /**
     * Inserta un nuevo VideojuegoPlataforma en la base de datos.
     *
     * @param videojuegoPlataforma el objeto VideojuegoPlataforma a insertar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void insertarVideojuegoPlataforma(VideojuegoPlataforma videojuegoPlataforma) throws DAOException;

    /**
     * Recupera todos los objetos VideojuegoPlataforma de la base de datos.
     *
     * @return una lista de todos los objetos VideojuegoPlataforma
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<VideojuegoPlataforma> obtenerTodos() throws DAOException;

    /**
     * Recupera VideojuegoPlataformas por el nombre del videojuego.
     *
     * @param nombreJuego el nombre del videojuego
     * @return una lista de VideojuegoPlataformas con el nombre del videojuego especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<VideojuegoPlataforma> obtenerPorVideojuegoNombre(String nombreJuego) throws DAOException;

    /**
     * Recupera VideojuegoPlataformas por el nombre de la plataforma.
     *
     * @param nombrePlataforma el nombre de la plataforma
     * @return una lista de VideojuegoPlataformas con el nombre de la plataforma especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<VideojuegoPlataforma> obtenerPorPlataformaNombre(String nombrePlataforma) throws DAOException;

    /**
     * Actualiza un VideojuegoPlataforma existente en la base de datos.
     *
     * @param videojuegoPlataforma el objeto VideojuegoPlataforma con la información actualizada
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void actualizar(VideojuegoPlataforma videojuegoPlataforma) throws DAOException;

    /**
     * Elimina un VideojuegoPlataforma de la base de datos por su ID de producto y plataforma.
     *
     * @param idProducto el ID del producto
     * @param idPlataforma el ID de la plataforma
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void eliminar(int idProducto, int idPlataforma) throws DAOException;
}