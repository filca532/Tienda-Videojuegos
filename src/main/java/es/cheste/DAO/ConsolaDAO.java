package es.cheste.DAO;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Consola;

import java.sql.Date;
import java.util.List;

/**
 * Interfaz para el Data Access Object (DAO) de Consola.
 * Proporciona métodos para realizar operaciones CRUD en objetos Consola.
 */
public interface ConsolaDAO {

    /**
     * Inserta una nueva Consola en la base de datos.
     *
     * @param consola el objeto Consola a insertar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void insertarConsola(Consola consola) throws DAOException;

    /**
     * Recupera una Consola por su ID.
     *
     * @param id el ID de la Consola a recuperar
     * @return el objeto Consola con el ID especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    Consola obtenerPorID(int id) throws DAOException;

    /**
     * Recupera todas las Consolas de la base de datos.
     *
     * @return una lista de todas las Consolas
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Consola> obtenerTodas() throws DAOException;

    /**
     * Recupera Consolas por su marca.
     *
     * @param marca la marca de las Consolas a recuperar
     * @return una lista de Consolas de la marca especificada
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Consola> obtenerConsolasPorMarca(String marca) throws DAOException;

    /**
     * Recupera Consolas por el ID de su proveedor.
     *
     * @param idProveedor el ID del proveedor de las Consolas a recuperar
     * @return una lista de Consolas del proveedor especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Consola> obtenerPorProveedor(int idProveedor) throws DAOException;

    /**
     * Actualiza una Consola existente en la base de datos.
     *
     * @param consola el objeto Consola con la información actualizada
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void actualizar(Consola consola) throws DAOException;

    /**
     * Elimina una Consola de la base de datos por su ID.
     *
     * @param id el ID de la Consola a eliminar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void eliminar(int id) throws DAOException;
}