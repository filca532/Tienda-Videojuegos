package es.cheste.DAO;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Proveedor;

import java.util.List;

/**
 * Interfaz para el Data Access Object (DAO) de Proveedor.
 * Proporciona métodos para realizar operaciones CRUD en objetos Proveedor.
 */
public interface ProveedorDAO {

    /**
     * Inserta un nuevo Proveedor en la base de datos.
     *
     * @param proveedor el objeto Proveedor a insertar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void insertarProveedor(Proveedor proveedor) throws DAOException;

    /**
     * Recupera un Proveedor por su ID.
     *
     * @param id el ID del Proveedor a recuperar
     * @return el objeto Proveedor con el ID especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    Proveedor obtenerPorID(int id) throws DAOException;

    /**
     * Recupera todos los objetos Proveedor de la base de datos.
     *
     * @return una lista de todos los objetos Proveedor
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Proveedor> obtenerTodos() throws DAOException;

    /**
     * Actualiza un Proveedor existente en la base de datos.
     *
     * @param proveedor el objeto Proveedor con la información actualizada
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void actualizar(Proveedor proveedor) throws DAOException;

    /**
     * Elimina un Proveedor de la base de datos por su ID.
     *
     * @param id el ID del Proveedor a eliminar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void eliminar(int id) throws DAOException;
}