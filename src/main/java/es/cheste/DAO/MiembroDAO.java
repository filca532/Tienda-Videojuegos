package es.cheste.DAO;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Miembro;

import java.util.List;

/**
 * Interfaz para el Data Access Object (DAO) de Miembro.
 * Proporciona métodos para realizar operaciones CRUD en objetos Miembro.
 */
public interface MiembroDAO {

    /**
     * Inserta un nuevo Miembro en la base de datos.
     *
     * @param miembro el objeto Miembro a insertar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void insertarMiembro(Miembro miembro) throws DAOException;

    /**
     * Recupera un Miembro por su ID.
     *
     * @param id el ID del Miembro a recuperar
     * @return el objeto Miembro con el ID especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    Miembro obtenerPorID(int id) throws DAOException;

    /**
     * Recupera todos los objetos Miembro de la base de datos.
     *
     * @return una lista de todos los objetos Miembro
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Miembro> obtenerTodos() throws DAOException;

    /**
     * Recupera Miembros por su tipo de membresía.
     *
     * @param tipoMembresia el tipo de membresía de los Miembros a recuperar
     * @return una lista de Miembros con el tipo de membresía especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Miembro> obtenerPorTipoMembresia(String tipoMembresia) throws DAOException;

    /**
     * Recupera Miembros por su nivel de membresía.
     *
     * @param nivelMembresia el nivel de membresía de los Miembros a recuperar
     * @return una lista de Miembros con el nivel de membresía especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Miembro> obtenerPorNivelMembresia(String nivelMembresia) throws DAOException;

    /**
     * Actualiza un Miembro existente en la base de datos.
     *
     * @param miembro el objeto Miembro con la información actualizada
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void actualizar(Miembro miembro) throws DAOException;

    /**
     * Elimina un Miembro de la base de datos por su ID.
     *
     * @param id el ID del Miembro a eliminar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void eliminar(int id) throws DAOException;
}