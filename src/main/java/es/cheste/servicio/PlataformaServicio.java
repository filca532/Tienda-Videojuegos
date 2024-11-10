package es.cheste.servicio;

import es.cheste.DAO.PlataformaDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.impl.PlataformaDAOImpl;
import es.cheste.objetos.Plataforma;

import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de Plataforma.
 */
public class PlataformaServicio {
    private PlataformaDAO plataformaDAO;

    /**
     * Constructor que inicializa el DAO de Plataforma.
     */
    public PlataformaServicio() {
        this.plataformaDAO = new PlataformaDAOImpl();
    }

    /**
     * Inserta una nueva Plataforma en la base de datos.
     *
     * @param plataforma La Plataforma a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    public void insertarPlataforma(Plataforma plataforma) throws DAOException {
        plataformaDAO.insertarPlataforma(plataforma);
    }

    /**
     * Obtiene una Plataforma por su ID.
     *
     * @param id El ID de la plataforma.
     * @return La Plataforma obtenida.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public Plataforma obtenerPorID(int id) throws DAOException {
        return plataformaDAO.obtenerPorID(id);
    }

    /**
     * Obtiene todas las Plataformas de la base de datos.
     *
     * @return Una lista de todas las Plataformas.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Plataforma> obtenerTodas() throws DAOException {
        return plataformaDAO.obtenerTodas();
    }

    /**
     * Actualiza una Plataforma en la base de datos.
     *
     * @param plataforma La Plataforma a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    public void actualizar(Plataforma plataforma) throws DAOException {
        plataformaDAO.actualizar(plataforma);
    }

    /**
     * Elimina una Plataforma de la base de datos.
     *
     * @param id El ID de la plataforma.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    public void eliminar(int id) throws DAOException {
        plataformaDAO.eliminar(id);
    }
}