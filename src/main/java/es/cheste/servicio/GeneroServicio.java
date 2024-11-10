package es.cheste.servicio;

import es.cheste.DAO.GeneroDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.impl.GeneroDAOImpl;
import es.cheste.objetos.Genero;

import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de Genero.
 */
public class GeneroServicio {
    private GeneroDAO generoDAO;

    /**
     * Constructor que inicializa el DAO de Genero.
     */
    public GeneroServicio() {
        this.generoDAO = new GeneroDAOImpl();
    }

    /**
     * Inserta un nuevo Genero en la base de datos.
     *
     * @param genero El Genero a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    public void insertarGenero(Genero genero) throws DAOException {
        generoDAO.insertarGenero(genero);
    }

    /**
     * Obtiene un Genero por su ID.
     *
     * @param id El ID del genero.
     * @return El Genero obtenido.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public Genero obtenerPorID(int id) throws DAOException {
        return generoDAO.obtenerPorID(id);
    }

    /**
     * Obtiene todos los Generos de la base de datos.
     *
     * @return Una lista de todos los Generos.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Genero> obtenerTodos() throws DAOException {
        return generoDAO.obtenerTodos();
    }

    /**
     * Actualiza un Genero en la base de datos.
     *
     * @param genero El Genero a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    public void actualizar(Genero genero) throws DAOException {
        generoDAO.actualizar(genero);
    }

    /**
     * Elimina un Genero de la base de datos.
     *
     * @param id El ID del genero.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    public void eliminar(int id) throws DAOException {
        generoDAO.eliminar(id);
    }
}