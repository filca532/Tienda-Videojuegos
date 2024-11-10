package es.cheste.DAO;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Merchandising;

import java.util.List;

/**
 * Interfaz para el Data Access Object (DAO) de Merchandising.
 * Proporciona métodos para realizar operaciones CRUD en objetos Merchandising.
 */
public interface MerchandisingDAO {

    /**
     * Inserta un nuevo Merchandising en la base de datos.
     *
     * @param merchandising el objeto Merchandising a insertar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void insertarMerchandising(Merchandising merchandising) throws DAOException;

    /**
     * Recupera un Merchandising por su ID.
     *
     * @param id el ID del Merchandising a recuperar
     * @return el objeto Merchandising con el ID especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    Merchandising obtenerPorID(int id) throws DAOException;

    /**
     * Recupera todos los objetos Merchandising de la base de datos.
     *
     * @return una lista de todos los objetos Merchandising
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Merchandising> obtenerTodos() throws DAOException;

    /**
     * Recupera Merchandising por su categoría.
     *
     * @param categoria la categoría del Merchandising a recuperar
     * @return una lista de Merchandising con la categoría especificada
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Merchandising> obtenerPorCategoria(String categoria) throws DAOException;

    /**
     * Actualiza un Merchandising existente en la base de datos.
     *
     * @param merchandising el objeto Merchandising con la información actualizada
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void actualizar(Merchandising merchandising) throws DAOException;

    /**
     * Elimina un Merchandising de la base de datos por su ID.
     *
     * @param id el ID del Merchandising a eliminar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void eliminar(int id) throws DAOException;
}