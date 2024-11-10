package es.cheste.DAO;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Plataforma;

import java.util.List;

/**
 * Interfaz para el Data Access Object (DAO) de Plataforma.
 * Proporciona métodos para realizar operaciones CRUD en objetos Plataforma.
 */
public interface PlataformaDAO {

    /**
     * Inserta una nueva Plataforma en la base de datos.
     *
     * @param plataforma el objeto Plataforma a insertar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void insertarPlataforma(Plataforma plataforma) throws DAOException;

    /**
     * Recupera una Plataforma por su ID.
     *
     * @param id el ID de la Plataforma a recuperar
     * @return el objeto Plataforma con el ID especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    Plataforma obtenerPorID(int id) throws DAOException;

    /**
     * Recupera todas las Plataformas de la base de datos.
     *
     * @return una lista de todas las Plataformas
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Plataforma> obtenerTodas() throws DAOException;

    /**
     * Actualiza una Plataforma existente en la base de datos.
     *
     * @param plataforma el objeto Plataforma con la información actualizada
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void actualizar(Plataforma plataforma) throws DAOException;

    /**
     * Elimina una Plataforma de la base de datos por su ID.
     *
     * @param id el ID de la Plataforma a eliminar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void eliminar(int id) throws DAOException;
}