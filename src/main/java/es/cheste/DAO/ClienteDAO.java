package es.cheste.DAO;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Cliente;

import java.sql.Date;
import java.util.List;

/**
 * Interfaz para el Data Access Object (DAO) de Cliente.
 * Proporciona métodos para realizar operaciones CRUD en objetos Cliente.
 */
public interface ClienteDAO {

    /**
     * Inserta un nuevo Cliente en la base de datos.
     *
     * @param cliente el objeto Cliente a insertar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void insertarCliente(Cliente cliente) throws DAOException;

    /**
     * Recupera un Cliente por su ID.
     *
     * @param id el ID del Cliente a recuperar
     * @return el objeto Cliente con el ID especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    Cliente obtenerPorID(int id) throws DAOException;

    /**
     * Recupera todos los objetos Cliente de la base de datos.
     *
     * @return una lista de todos los objetos Cliente
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Cliente> obtenerTodos() throws DAOException;

    /**
     * Recupera objetos Cliente dentro de un rango de fechas especificado.
     *
     * @param fechaInicio la fecha de inicio del rango
     * @param fechaFin la fecha de fin del rango
     * @return una lista de objetos Cliente dentro del rango de fechas especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Cliente> obtenerClientesRangoFecha(Date fechaInicio, Date fechaFin) throws DAOException;

    /**
     * Actualiza un Cliente existente en la base de datos.
     *
     * @param cliente el objeto Cliente con la información actualizada
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void actualizar(Cliente cliente) throws DAOException;

    /**
     * Elimina un Cliente de la base de datos por su ID.
     *
     * @param id el ID del Cliente a eliminar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void eliminar(int id) throws DAOException;
}