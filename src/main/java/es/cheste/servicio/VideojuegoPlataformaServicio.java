package es.cheste.servicio;

import es.cheste.DAO.VideojuegoPlataformaDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.impl.VideojuegoPlataformaDAOImpl;
import es.cheste.objetos.VideojuegoPlataforma;

import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de VideojuegoPlataforma.
 */
public class VideojuegoPlataformaServicio {
    private VideojuegoPlataformaDAO videojuegoPlataformaDAO;

    /**
     * Constructor que inicializa el DAO de VideojuegoPlataforma.
     */
    public VideojuegoPlataformaServicio() {
        this.videojuegoPlataformaDAO = new VideojuegoPlataformaDAOImpl();
    }

    /**
     * Inserta un nuevo VideojuegoPlataforma en la base de datos.
     *
     * @param videojuegoPlataforma El VideojuegoPlataforma a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    public void insertarVideojuegoPlataforma(VideojuegoPlataforma videojuegoPlataforma) throws DAOException {
        videojuegoPlataformaDAO.insertarVideojuegoPlataforma(videojuegoPlataforma);
    }

    /**
     * Obtiene todos los VideojuegoPlataforma de la base de datos.
     *
     * @return Una lista de todos los VideojuegoPlataforma.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<VideojuegoPlataforma> obtenerTodos() throws DAOException {
        return videojuegoPlataformaDAO.obtenerTodos();
    }

    /**
     * Obtiene los VideojuegoPlataforma por nombre del videojuego.
     *
     * @param nombre El nombre del videojuego.
     * @return Una lista de VideojuegoPlataforma con el nombre del videojuego especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<VideojuegoPlataforma> obtenerPorVideojuegoNombre(String nombre) throws DAOException {
        return videojuegoPlataformaDAO.obtenerPorVideojuegoNombre(nombre);
    }

    /**
     * Obtiene los VideojuegoPlataforma por nombre de la plataforma.
     *
     * @param nombre El nombre de la plataforma.
     * @return Una lista de VideojuegoPlataforma con el nombre de la plataforma especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<VideojuegoPlataforma> obtenerPorPlataformaNombre(String nombre) throws DAOException {
        return videojuegoPlataformaDAO.obtenerPorPlataformaNombre(nombre);
    }

    /**
     * Actualiza un VideojuegoPlataforma en la base de datos.
     *
     * @param videojuegoPlataforma El VideojuegoPlataforma a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    public void actualizar(VideojuegoPlataforma videojuegoPlataforma) throws DAOException {
        videojuegoPlataformaDAO.actualizar(videojuegoPlataforma);
    }

    /**
     * Elimina un VideojuegoPlataforma de la base de datos.
     *
     * @param idProducto El ID del producto.
     * @param idPlataforma El ID de la plataforma.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    public void eliminar(int idProducto, int idPlataforma) throws DAOException {
        videojuegoPlataformaDAO.eliminar(idProducto, idPlataforma);
    }
}