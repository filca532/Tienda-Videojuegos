package es.cheste.servicio;

import es.cheste.DAO.VideojuegoGeneroDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.impl.VideojuegoGeneroDAOImpl;
import es.cheste.objetos.VideojuegoGenero;
import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de VideojuegoGenero.
 */
public class VideojuegoGeneroServicio {
    private VideojuegoGeneroDAO videojuegoGeneroDAO;

    /**
     * Constructor que inicializa el DAO de VideojuegoGenero.
     */
    public VideojuegoGeneroServicio() {
        this.videojuegoGeneroDAO = new VideojuegoGeneroDAOImpl();
    }

    /**
     * Inserta un nuevo VideojuegoGenero en la base de datos.
     *
     * @param videojuegoGenero El VideojuegoGenero a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    public void insertarVideojuegoGenero(VideojuegoGenero videojuegoGenero) throws DAOException {
        videojuegoGeneroDAO.insertarVideojuegoGenero(videojuegoGenero);
    }

    /**
     * Obtiene todos los VideojuegoGenero de la base de datos.
     *
     * @return Una lista de todos los VideojuegoGenero.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<VideojuegoGenero> obtenerTodos() throws DAOException {
        return videojuegoGeneroDAO.obtenerTodos();
    }

    /**
     * Obtiene los VideojuegoGenero por nombre del videojuego.
     *
     * @param nombreJuego El nombre del videojuego.
     * @return Una lista de VideojuegoGenero con el nombre del videojuego especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<VideojuegoGenero> obtenerPorVideojuegoNombre(String nombreJuego) throws DAOException {
        return videojuegoGeneroDAO.obtenerPorVideojuegoNombre(nombreJuego);
    }

    /**
     * Obtiene los VideojuegoGenero por nombre del género.
     *
     * @param nombreGenero El nombre del género.
     * @return Una lista de VideojuegoGenero con el nombre del género especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<VideojuegoGenero> obtenerPorGeneroNombre(String nombreGenero) throws DAOException {
        return videojuegoGeneroDAO.obtenerPorGeneroNombre(nombreGenero);
    }

    /**
     * Actualiza un VideojuegoGenero en la base de datos.
     *
     * @param videojuegoGenero El VideojuegoGenero a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    public void actualizar(VideojuegoGenero videojuegoGenero) throws DAOException {
        videojuegoGeneroDAO.actualizar(videojuegoGenero);
    }

    /**
     * Elimina un VideojuegoGenero de la base de datos.
     *
     * @param idProducto El ID del producto.
     * @param idGenero El ID del género.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    public void eliminar(int idProducto, int idGenero) throws DAOException {
        videojuegoGeneroDAO.eliminar(idProducto, idGenero);
    }
}